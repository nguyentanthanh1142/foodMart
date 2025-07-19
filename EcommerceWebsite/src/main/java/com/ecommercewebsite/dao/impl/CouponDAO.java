package com.ecommercewebsite.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.ICouponDAO;
import com.ecommercewebsite.mapper.CouponMapper;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.paging.Pageble;

public class CouponDAO extends AbstractDAO<CouponModel> implements ICouponDAO {

	@Override
	public List<CouponModel> findAll() {
		String sql = "SELECT * FROM coupon";
		List<CouponModel> list = query(sql, new CouponMapper());
		return list;
	}

	@Override
	public List<CouponModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM coupon");
		
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		} else {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " desc");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new CouponMapper());
	}

	@Override
	public CouponModel findOne(long id) {
		StringBuilder sql = new StringBuilder("SELECT c.*, GROUP_CONCAT(cp.productId) AS listCoupon ");
		sql.append("FROM coupon c ");
		sql.append("left join product_coupon cp ON c.id = cp.couponId ");
		sql.append("where c.id = ? ");
		sql.append("group by c.id");
		System.out.println(sql.toString());
		List<CouponModel> coupon = query(sql.toString(), new CouponMapper(), id);
		return coupon.isEmpty() ? null : coupon.get(0);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM coupon";
		return count(sql);
	}

	@Override
	public Long save(CouponModel coupon) {
		StringBuilder sql = new StringBuilder(
				"insert into coupon (name, description, quantity, isProductCoupon,byPercent,minValue,limitedDiscount,code ,available,pricesale,start,end,status,createdAt,createdBy,modifiedAt,modifiedBy) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), coupon.getName(), coupon.getDescription(), coupon.getQuantity(),
				coupon.isProductCoupon(), coupon.isByPercent(), coupon.getMinValue(), coupon.getLimitedDiscount(),
				coupon.getCode(), coupon.getQuantity(), coupon.getPricesale(), coupon.getStart(), coupon.getEnd(),
				coupon.getStatus(), coupon.getCreatedAt(), coupon.getCreatedBy(), coupon.getModifiedAt(),
				coupon.getModifiedBy());
	}

	@Override
	public void update(CouponModel updateCoupon) {
		StringBuilder sql = new StringBuilder(
				"UPDATE coupon SET name = ?, description = ?, code = ?, quantity = ?, isProductCoupon = ?, byPercent = ? , minValue = ?, limitedDiscount = ?,  available = ?,");
		sql.append(" pricesale = ?, start = ?, end = ?, status = ?,");
		sql.append(" createdat = ?, createdby = ?, modifiedat = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateCoupon.getName(), updateCoupon.getDescription(), updateCoupon.getCode(),
				updateCoupon.getQuantity(), updateCoupon.isProductCoupon(), updateCoupon.isByPercent(),
				updateCoupon.getMinValue(), updateCoupon.getLimitedDiscount(), updateCoupon.getAvailable(),
				updateCoupon.getPricesale(), updateCoupon.getStart(), updateCoupon.getEnd(), updateCoupon.getStatus(),
				updateCoupon.getCreatedAt(), updateCoupon.getCreatedBy(), updateCoupon.getModifiedAt(),
				updateCoupon.getModifiedBy(), updateCoupon.getId());

	}

	@Override
	public CouponModel checkAvailableCode(String code) {
		StringBuilder sql = new StringBuilder("SELECT * FROM coupon ");
		sql.append("where code = ? ");
		sql.append("AND (start IS NULL OR start <= CURRENT_TIMESTAMP) ");
		sql.append("AND (end IS NULL OR end >= CURRENT_TIMESTAMP) ");
		sql.append("AND available > 0 ");
		List<CouponModel> coupon = query(sql.toString(), new CouponMapper(), code);
		return coupon.isEmpty() ? null : coupon.get(0);
	}

	@Override
	public void subVailable(String code) {
		String sql = "UPDATE coupon set available = available - 1 WHERE code = '" + code + "'";
		update(sql);
	}

	@Override
	public CouponModel findCouponById(Long id) {
		String sql = "SELECT * FROM coupon where id = " + id;
		List<CouponModel> result = query(sql, new CouponMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void createDiscoutedProductList(List<Long> list, Long couponId) {
		for (int i = 0; i < list.size(); i++) {
			insertProductCoupon(couponId,list.get(i));
		}
	}

	public void insertProductCoupon(Long couponId, Long proId) {

		String sql = "INSERT INTO product_coupon(couponId,productId) VALUES (" + couponId + ", " + proId + ")";
		System.out.println("Inser product_coupon " + sql.toString());
		update(sql);
	}

	public void deleteProductCoupon(Long couponId, List<Long> proId) {
//		String sql = "delete from product_coupon where couponId = " + couponId + " and productId = " + proId;

		if (proId.size() > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM product_coupon where couponId = ");
			sql.append(couponId);
			sql.append(" and productId in (");
			for (int i = 0; i < proId.size(); i++) {
				sql.append(proId.get(i) + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			System.out.println("Update Product_coupon " + sql.toString());
			update(sql.toString());
		}
	}

	@Override
	public void updateProductCoupon(Long couponId, List<Long> oldL, List<Long> newL) {
		List<Long> needToInserset = newL.stream().filter(id -> !oldL.contains(id)).collect(Collectors.toList());
		List<Long> needToDelete = oldL.stream().filter(id -> !newL.contains(id)).collect(Collectors.toList());
		if(!needToDelete.isEmpty())
		{
			deleteProductCoupon(couponId, needToDelete);
		}
		if(!needToInserset.isEmpty())
		{
			createDiscoutedProductList(needToInserset, couponId);
		}
		
		
	}
	
	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM coupon where id in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append(ids[i] + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			update(sql.toString());
		}
	}
	
	@Override
	public CouponModel checkCoupon(String code) {
		StringBuilder sql = new StringBuilder("SELECT * FROM coupon ");
		sql.append("where code = ? ");
		sql.append("AND (start IS NULL OR start <= CURRENT_TIMESTAMP) ");
		sql.append("AND (end IS NULL OR end >= CURRENT_TIMESTAMP) ");
		sql.append("AND available > 0 ");
		System.out.println(sql.toString());
		List<CouponModel> coupon = query(sql.toString(), new CouponMapper(), code);
		if (coupon.isEmpty()) {
	        throw new RuntimeException("Mã khuyến mãi không hợp lệ hoặc đã hết hạn.");
	    }
		return coupon.get(0);
	}
}
