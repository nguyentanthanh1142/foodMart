package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.cloudinary.utils.ObjectUtils;
import com.ecommercewebsite.cloudinary.CloudinaryUtil;
import com.ecommercewebsite.dao.ICategoryDAO;
import com.ecommercewebsite.dao.IProductDAO;
import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ICloudinaryService;
import com.ecommercewebsite.service.IProductService;

public class ProductService implements IProductService {

	@Inject
	private IProductDAO productDAO;

	@Inject
	private ICategoryDAO categoryDAO;
	
	@Inject
	private ICloudinaryService cloudinaryService;

	@Override
	public List<ProductModel> findAll() {
		return productDAO.findAll();
	}

	@Override
	public List<ProductModel> findAll(Pageble pageble) {
		return productDAO.findAll(pageble);
	}

	@Override
	public ProductModel findOne(long id) {
		ProductModel model = productDAO.findOne(id);
		CategoryModel category = categoryDAO.findOne(model.getCatid());
		model.setCategoryModel(category);
		return model;
	}

	@Override
	public ProductModel save(ProductModel model) {
		model.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		model.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		Long proId = productDAO.save(model);
		return productDAO.findOne(proId);
	}

	@Override
	public ProductModel update(ProductModel product) {
		ProductModel old = productDAO.findOne(product.getId());
		product.setCreatedAt(old.getCreatedAt());
		product.setCreatedBy(old.getCreatedBy());
		product.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		if (product.getProductimg() == null) {
			product.setProductimg(old.getProductimg());
			product.setImgPublicId(old.getImgPublicId());
		}
		if (old.getImgPublicId() != null) {
			try {
				CloudinaryUtil.getInstance().uploader().destroy(old.getImgPublicId(), ObjectUtils.emptyMap());
			} catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		productDAO.update(product);
		return productDAO.findOne(product.getId());

	}

	@Override
	public ProductModel findProductBySlug(String slug) {
		return productDAO.findProductBySlug(slug);
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids)
		{
			ProductModel product = productDAO.findOne(id);
			if(product.getImgPublicId() != null)
			{
				cloudinaryService.deleteImage(product.getImgPublicId());
			}
		}
		productDAO.delete(ids);

	}

	@Override
	public boolean isSlugExist(String slug) {
		return false;
	}

	@Override
	public int getTotalItem() {
		return productDAO.getTotalItem();
	}

	@Override
	public List<ProductModel> findByCategorySlug(Pageble page, String slug) {
		return productDAO.findByCategorySlug(page, slug);
	}

	@Override
	public List<ProductModel> searchProduct(ProductModel product, Pageble pageble) {
		String sortBy = product.getSortBy();
		String order = product.getSortName();
		if (sortBy == null || order == null) {
			product.setSortBy("createdAt");
		} else {
			switch (sortBy) {
			case "price":
				pageble.getSorter().setSortBy("pricesale");
				break;
			case "ctime":
				pageble.getSorter().setSortBy("createdAt");
				break;
			case "alpha":
				pageble.getSorter().setSortBy("name");
				break;
			default:
				pageble.getSorter().setSortBy("createdAt");
				break;
			}
		}

		return productDAO.searchProduct(product, pageble);
	}

	@Override
	public int getTotalItem(String fname) {
		return productDAO.getTotalItem(fname);
	}

	@Override
	public List<ProductModel> getListProductShow(Pageble pageble, String[] price) {
		return productDAO.getListProductShow(pageble, price);
	}

	@Override
	public int getTotalItemBySlug(String slug) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductModel> getListRelatedProduct(long cateid) {
		// TODO Auto-generated method stub
		return productDAO.getListRelatedProduct(cateid);
	}

	@Override
	public List<ProductModel> findByNameLike(String name) {
		// TODO Auto-generated method stub
		return productDAO.findByNameLike(name);
	}

	@Override
	public int getTotalProductCategory(String[] priceFilter) {
		// TODO Auto-generated method stub
		return productDAO.getTotalProductCategory(priceFilter);
	}

	@Override
	public List<ProductModel> getListProductCategoryShow(Pageble pageble, Long cateid, String[] price) {
		// TODO Auto-generated method stub
		return productDAO.getListProductCategoryShow(pageble,cateid,price);
	}

	@Override
	public int getTotalProductByCateId(Long cateId, String[] priceFilter) {
		// TODO Auto-generated method stub
		return productDAO.getTotalProductByCateId(cateId,priceFilter);
	}

	@Override
	public List<ProductModel> getSpecialPromotion() {
		return productDAO.getSpecialPromotion();
	}

	@Override
	public List<ProductModel> getListNewProducts() {
		// TODO Auto-generated method stub
		return productDAO.getListNewProducts();
	}

}
