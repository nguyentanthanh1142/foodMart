package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.paging.Pageble;

public interface IProductService {
	public void delete(long[] ids);

	public boolean isSlugExist(String slug);

	public int getTotalItem();
	
	public int  getTotalProductCategory(String[] priceFilter);
	
	public int getTotalProductByCateId(Long cateId,String[] priceFilter);

	public int getTotalItem(String fname);
	
	public int getTotalItemBySlug(String slug);

	public ProductModel findOne(long id);

	public ProductModel save(ProductModel category);

	public ProductModel update(ProductModel product);

	public ProductModel findProductBySlug(String slug);

	public List<ProductModel> findAll();

	public List<ProductModel> findAll(Pageble pageble);
	
	public List<ProductModel> getListProductShow(Pageble pageble, String[] price);
	
	public List<ProductModel> getListProductCategoryShow(Pageble pageble, Long cateid,String[] price);

	public List<ProductModel> findByCategorySlug(Pageble page, String slug);

	public List<ProductModel> searchProduct(ProductModel product, Pageble pageble);
	
	public List<ProductModel> getListRelatedProduct(long cateid);
	
	public List<ProductModel> findByNameLike(String name);
	
	public List<ProductModel> getSpecialPromotion();
	public List<ProductModel> getListNewProducts();

}
