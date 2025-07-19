package com.ecommercewebsite.dao.impl;

import com.ecommercewebsite.dao.IProductImageDAO;
import com.ecommercewebsite.model.ProductImageModel;

public class ProductImageDAO extends AbstractDAO<ProductImageModel> implements IProductImageDAO {

	@Override
	public Long save(ProductImageModel productImage) {
		StringBuilder sql = new StringBuilder(
				"insert into product_image (product_id,img,createdAt,createdBy,modifiedAt,modifiedBy) ");
		sql.append("values (?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), productImage.getProduct_id(), productImage.getImg(), productImage.getCreatedAt(),
				productImage.getCreatedBy(), productImage.getModifiedAt(), productImage.getModifiedBy());
	}

	@Override
	public void update(ProductImageModel newModel) {
		StringBuilder sql = new StringBuilder("UPDATE product_image SET product_id = ?, img = ?,");
		sql.append(" createdat = ?, createdby = ?, modifiedat = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), newModel.getProduct_id(), newModel.getImg(), newModel.getCreatedAt(),
				newModel.getCreatedBy(), newModel.getModifiedAt(), newModel.getModifiedBy(), newModel.getId());
	}

}
