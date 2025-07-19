package com.ecommercewebsite.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.IPromotionDAO;
import com.ecommercewebsite.mapper.CouponMapper;
import com.ecommercewebsite.mapper.PromotionMapper;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.model.PromotionModel;
import com.ecommercewebsite.paging.Pageble;

public class PromotionDAO extends AbstractDAO<PromotionModel> implements IPromotionDAO {

	@Override
	public List<PromotionModel> findAll() {
		String sql = "SELECT * FROM promotion";
		List<PromotionModel> list = query(sql, new PromotionMapper());
		return list;
	}

	@Override
	public List<PromotionModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM promotion");
		
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
		return query(sql.toString(), new PromotionMapper());
	}

	@Override
	public PromotionModel findOne(long id) {
		StringBuilder sql = new StringBuilder("SELECT c.*, GROUP_CONCAT(cp.productId) AS listCoupon ");
		sql.append("FROM promotion c ");
		sql.append("left join product_promotion cp ON c.id = cp.promotionId ");
		sql.append("where c.id = ? ");
		sql.append("group by c.id");
		System.out.println(sql.toString());
		List<PromotionModel> coupon = query(sql.toString(), new PromotionMapper(), id);
		return coupon.isEmpty() ? null : coupon.get(0);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM promotion";
		return count(sql);
	}

	@Override
	public Long save(PromotionModel coupon) {
		StringBuilder sql = new StringBuilder(
				"insert into promotion (name, description, quantity, isProductPromotion,byPercent,minValue,limitedDiscount,available,pricesale,start,end,status,createdAt,createdBy,modifiedAt,modifiedBy) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), coupon.getName(), coupon.getDescription(), coupon.getQuantity(),
				coupon.getIsProductPromotion(), coupon.isByPercent(), coupon.getMinValue(), coupon.getLimitedDiscount(),
				coupon.getQuantity(), coupon.getPricesale(), coupon.getStart(), coupon.getEnd(),
				coupon.getStatus(), coupon.getCreatedAt(), coupon.getCreatedBy(), coupon.getModifiedAt(),
				coupon.getModifiedBy());
	}

	@Override
	public void update(PromotionModel updateCoupon) {
		StringBuilder sql = new StringBuilder(
				"UPDATE promotion SET name = ?, description = ?,  quantity = ?, isProductPromotion = ?, byPercent = ? , minValue = ?, limitedDiscount = ?,  available = ?,");
		sql.append(" pricesale = ?, start = ?, end = ?, status = ?,");
		sql.append(" createdat = ?, createdby = ?, modifiedat = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateCoupon.getName(), updateCoupon.getDescription(), 
				updateCoupon.getQuantity(), updateCoupon.getIsProductPromotion(), updateCoupon.isByPercent(),
				updateCoupon.getMinValue(), updateCoupon.getLimitedDiscount(), updateCoupon.getAvailable(),
				updateCoupon.getPricesale(), updateCoupon.getStart(), updateCoupon.getEnd(), updateCoupon.getStatus(),
				updateCoupon.getCreatedAt(), updateCoupon.getCreatedBy(), updateCoupon.getModifiedAt(),
				updateCoupon.getModifiedBy(), updateCoupon.getId());

	}

	@Override
	public void subVailable(String code) {
		String sql = "UPDATE promotion set available = available - 1 WHERE id = '" + code + "'";
		update(sql);
	}

	@Override
	public PromotionModel findPromotionById(Long id) {
		String sql = "SELECT * FROM promotion where id = " + id;
		List<PromotionModel> result = query(sql, new PromotionMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void createPromoteProductList(List<Long> list, Long promotionId) {
		for (int i = 0; i < list.size(); i++) {
			insertProductPromotion(promotionId,list.get(i));
		}
	}

	public void insertProductPromotion(Long couponId, Long proId) {

		String sql = "INSERT INTO product_promotion(promotionId,productId) VALUES (" + couponId + ", " + proId + ")";
		System.out.println("Inset product_promotion " + sql.toString());
		update(sql);
	}

	public void deleteProductCoupon(Long couponId, List<Long> proId) {
//		String sql = "delete from product_coupon where couponId = " + couponId + " and productId = " + proId;

		if (proId.size() > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM product_promotion where promotionId = ");
			sql.append(couponId);
			sql.append(" and productId in (");
			for (int i = 0; i < proId.size(); i++) {
				sql.append(proId.get(i) + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			System.out.println("Update product_promotion " + sql.toString());
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
			createPromoteProductList(needToInserset, couponId);
		}
		
		
	}
	
	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM promotion where id in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append(ids[i] + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			update(sql.toString());
		}
	}

	@Override
	public PromotionModel getSpecialPromotion() {
		String sql = "SELECT * FROM promotion where hotPromotion = 1 ";
		List<PromotionModel> result = query(sql, new PromotionMapper());
		return result.isEmpty() ? null : result.get(0);
	}
	
//	@Override
//	public PromotionModel checkCoupon(int code) {
//		StringBuilder sql = new StringBuilder("SELECT * FROM promotion ");
//		sql.append("where code = ? ");
//		sql.append("AND (start IS NULL OR start <= CURRENT_TIMESTAMP) ");
//		sql.append("AND (end IS NULL OR end >= CURRENT_TIMESTAMP) ");
//		sql.append("AND available > 0 ");
//		System.out.println(sql.toString());
//		List<CouponModel> coupon = query(sql.toString(), new CouponMapper(), code);
//		if (coupon.isEmpty()) {
//	        throw new RuntimeException("Mã khuyến mãi không hợp lệ hoặc đã hết hạn.");
//	    }
//		return coupon.get(0);
//	}
}
