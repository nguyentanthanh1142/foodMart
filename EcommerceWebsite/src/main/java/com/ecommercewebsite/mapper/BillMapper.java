package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.BillModel;

public class BillMapper implements RowMapper<BillModel> {

	@Override
	public BillModel mapRow(ResultSet result) {
		try {
			BillModel model = new BillModel();
			model.setId(result.getLong("id"));
			model.setCode(result.getString("code"));
			model.setEmail(result.getString("email"));
			model.setDisplay_name(result.getString("display_name"));
			model.setPhone(result.getString("phone"));
			model.setAddress(result.getString("address"));
			model.setCity(result.getString("city"));
			model.setDistrict(result.getString("district"));
			model.setProvince(result.getString("province"));
			model.setNote(result.getString("note"));
			model.setTotal(result.getDouble("total"));
			model.setQuanty(result.getInt("quanty"));
			model.setStatus(result.getInt("status"));
			model.setCreatedAt(result.getTimestamp("createdAt"));
			model.setModifiedAt(result.getTimestamp("modifiedAt"));
			model.setModifiedBy(result.getString("modifiedBy"));
			model.setCoupon(result.getInt("coupon"));
			model.setCoupon_id(result.getLong("coupon_id"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}
}
