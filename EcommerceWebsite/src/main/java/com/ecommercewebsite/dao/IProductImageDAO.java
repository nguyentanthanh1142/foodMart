package com.ecommercewebsite.dao;

import com.ecommercewebsite.model.PageModel;
import com.ecommercewebsite.model.ProductImageModel;

public interface IProductImageDAO extends GenericDAO<ProductImageModel> {

	public Long save(ProductImageModel newModel);

	void update(ProductImageModel newModel);
}
