package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.BillDetailModel;

public class BillDetailMapper implements RowMapper<BillDetailModel>{

	@Override
	public BillDetailModel mapRow(ResultSet result) {
		try {
			BillDetailModel model = new BillDetailModel();
			model.setId(result.getLong("id"));
			model.setProduct_id(result.getLong("product_id"));
			model.setBills_id(result.getLong("bills_id"));
			model.setTotal(result.getDouble("total"));
			model.setQuanty(result.getInt("quanty"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}
}
