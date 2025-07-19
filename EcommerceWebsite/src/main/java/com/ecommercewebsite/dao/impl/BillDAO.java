package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.IBillDAO;
import com.ecommercewebsite.mapper.BillDetailMapper;
import com.ecommercewebsite.mapper.BillMapper;
import com.ecommercewebsite.model.BillDetailModel;
import com.ecommercewebsite.model.BillModel;
import com.ecommercewebsite.paging.Pageble;

public class BillDAO extends AbstractDAO<BillModel> implements IBillDAO {

	@Override
	public Long AddBills(BillModel bill, double priceafterpromotion) {
		StringBuilder sql = new StringBuilder(
				"insert into bills (code,email,display_name,phone,address,city,district,province,note,total,quanty,status,createdAt,modifiedAt,modifiedBy,coupon,coupon_id) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), bill.getCode(), bill.getEmail(), bill.getDisplay_name(), bill.getPhone(),
				bill.getAddress(), bill.getCity(), bill.getDistrict(), bill.getProvince(), bill.getNote(),
				(bill.getCoupon() > 0 ? priceafterpromotion : bill.getTotal()), bill.getQuanty(),
				bill.getStatus(), bill.getCreatedAt(), bill.getModifiedAt(), bill.getModifiedBy(), bill.getCoupon(),
				bill.getCoupon_id());
	}

	@Override
	public Long AddBillsDetail(BillDetailModel billdetail) {
		StringBuilder sql = new StringBuilder("insert into billdetail (product_id,bills_id,quanty,total) ");
		sql.append("values (?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), billdetail.getProduct_id(), billdetail.getBills_id(), billdetail.getQuanty(),
				billdetail.getTotal());

	}

	@Override
	public BillModel findBillsConfirm(String code) {
		String sql = "SELECT * FROM bills where code = '" + code + "'";
		List<BillModel> result = query(sql, new BillMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public List<BillDetailModel> getBillsDetail(Long id) {
		String sql = "SELECT * FROM billdetail where bills_id = " + id;
		List<BillDetailModel> result = query(sql, new BillDetailMapper());
		return result;
	}

	@Override
	public BillModel findOne(Long id) {
		String sql = "SELECT * FROM bills where id = " + id;
		List<BillModel> result = query(sql, new BillMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public List<BillModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM bills");
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
		return query(sql.toString(), new BillMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM bills";
		return count(sql);
	}

	@Override
	public List<BillModel> getCustomers(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM bills group by 'email'");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		return query(sql.toString(), new BillMapper());
	}

	@Override
	public int getTotalCustomers() {
		String sql = "SELECT COUNT(*) FROM bills group by email";
		return count(sql);
	}

	@Override
	public List<BillModel> GetDataBillByEmail(Pageble pageble, String email) {
		StringBuilder sql = new StringBuilder("SELECT * FROM bills where email = '" + email + "' order by createdAt desc ");
		System.out.println("sql: " + sql);
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		
		return query(sql.toString(), new BillMapper());
	}

	@Override
	public int countBillByEmail(String email) {
		String sql = "SELECT COUNT(*) FROM bills where email = '" + email + "' group by email";
		System.out.println("Count: " + sql);
		return count(sql);
	}

	@Override
	public List<BillModel> findAll() {
		String sql = "SELECT * FROM bills";
		return query(sql, new BillMapper());
	}

	@Override
	public void changeBillStatus(BillModel model) {
		StringBuilder sql =  new StringBuilder("UPDATE bills set status = status + 1, modifiedBy = ?" ); 
		sql.append(", modifiedAt = ? where status < 4 and id = ?");
		System.out.println(sql.toString());
		update(sql.toString(), model.getModifiedBy(), model.getModifiedAt(), model.getId());
	}
	@Override
	public void cancelBill(BillModel model) {
		StringBuilder sql =  new StringBuilder("UPDATE bills set status = -1, modifiedBy = ?" ); 
		sql.append(", modifiedAt = ? where status < 4 and id = ?");
		System.out.println(sql.toString());
		update(sql.toString(), model.getModifiedBy(), model.getModifiedAt(), model.getId());
	}
}
