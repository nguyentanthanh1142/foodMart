package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.ProductImageModel;

public interface IProductImageService {
	public void save(List<String> imageList, Long productId);
	public void update(ProductImageModel newModel);
}
