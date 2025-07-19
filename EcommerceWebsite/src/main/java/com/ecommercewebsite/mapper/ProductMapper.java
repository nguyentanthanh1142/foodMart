package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.model.ProductModel;

public class ProductMapper implements RowMapper<ProductModel>{

	@Override
	public ProductModel mapRow(ResultSet result) {
		try {
			ProductModel product = new ProductModel();
			product.setId(result.getLong("id"));
			product.setName(result.getString("name"));
			product.setSlug(result.getString("slug"));
			product.setDetail(result.getString("detail"));
			product.setPrice(result.getFloat("price"));
			product.setPricesale(result.getFloat("pricesale"));
			product.setWeight(result.getFloat("weight"));
			product.setShortdesc(result.getString("shortdesc"));
			product.setProductimg(result.getString("productimg"));
			product.setImgPublicId(result.getString("imgPublicId"));
			product.setCatid(result.getLong("catid"));
			product.setStatus(result.getInt("status"));
			product.setAvailable(result.getInt("available"));
			product.setCreatedAt(result.getTimestamp("createdAt"));
			product.setCreatedBy(result.getString("createdBy"));
			product.setModifiedAt(result.getTimestamp("modifiedAt"));
			product.setModifiedBy(result.getString("modifiedBy"));
			return product;
		} catch (SQLException e) {
			return null;
		}
	}
}
