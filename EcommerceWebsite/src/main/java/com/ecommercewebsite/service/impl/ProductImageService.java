package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.IProductImageDAO;
import com.ecommercewebsite.model.ProductImageModel;
import com.ecommercewebsite.service.IProductImageService;

public class ProductImageService implements IProductImageService {

	@Inject
	private IProductImageDAO productImageDAO;

	@Override
	public void save(List<String> imageList, Long productId) {
		ProductImageModel productimage = new ProductImageModel();
		productimage.setProduct_id(productId);
		productimage.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		productimage.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		productimage.setCreatedBy("");
		productimage.setModifiedBy("");
		for (String img : imageList) {
			productimage.setImg(img);
			productImageDAO.save(productimage);
		}
	}

	@Override
	public void update(ProductImageModel newModel) {
//		ProductImageModel old = productImageDAO.findOne(newModel.getId());
//		product.setCreatedAt(old.getCreatedAt());
//		product.setCreatedBy(old.getCreatedBy());
//		product.setModifiedAt(new Timestamp(System.currentTimeMillis()));
//		if (product.getProductimg() == null) {
//			product.setProductimg(old.getProductimg());
//		}
//
////		TopicModel topic = topicDAO.findBySlug(model.getTopicCode());
////		model.setTopicId(topic.getId());
//		productDAO.update(product);
//		return productDAO.findOne(product.getId());
	}

}
