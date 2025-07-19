package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecommercewebsite.model.PromotionModel;

public class PromotionMapper implements RowMapper<PromotionModel> {

	@Override
	public PromotionModel mapRow(ResultSet result) {
		try {
			PromotionModel model = new PromotionModel();
			model.setId(result.getLong("id"));
			model.setName(result.getString("name"));
			model.setDescription(result.getString("description"));
			model.setAvailable(result.getInt("available"));
			model.setQuantity(result.getInt("quantity"));
			model.setMinValue(result.getInt("minValue"));
			model.setPricesale(result.getDouble("pricesale"));
			model.setMinValue(result.getDouble("minValue"));
			model.setLimitedDiscount(result.getDouble("limitedDiscount"));
			model.setStart(result.getTimestamp("start"));
			model.setEnd(result.getTimestamp("end"));
			model.setStatus(result.getInt("status"));
			model.setCreatedAt(result.getTimestamp("createdAt"));
			model.setCreatedBy(result.getString("createdBy"));
			model.setModifiedAt(result.getTimestamp("modifiedAt"));
			model.setModifiedBy(result.getString("modifiedBy"));
			model.setProductPromotion(result.getBoolean("isProductPromotion"));
			model.setByPercent(result.getBoolean("byPercent"));

			try {
				String listCouponStr = result.getString("listCoupon");
				List<Long> listCoupon = new ArrayList<>();
				if (listCouponStr != null && !listCouponStr.isEmpty()) {
					for (String s : listCouponStr.split(",")) {
						listCoupon.add((long) Integer.parseInt(s));
					}
				}
				model.setListProduct(listCoupon);
			} catch(Exception e)
			{
				
			}
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
