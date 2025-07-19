package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.ProductImageModel;

public class ProductImageMapper implements RowMapper<ProductImageModel> {

	@Override
	public ProductImageModel mapRow(ResultSet result) {
		try {
			ProductImageModel productImage = new ProductImageModel();
			productImage.setId(result.getLong("id"));
			productImage.setProduct_id(result.getLong("product_id"));
			productImage.setImg(result.getString("img"));
			productImage.setCreatedAt(result.getTimestamp("createdAt"));
			productImage.setCreatedBy(result.getString("createdBy"));
			productImage.setModifiedAt(result.getTimestamp("modifiedAt"));
			productImage.setModifiedBy(result.getString("modifiedBy"));
			return productImage;
		} catch (SQLException e) {
			return null;
		}
	}
}
