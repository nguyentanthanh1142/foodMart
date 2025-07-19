package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.paging.Pageble;

public interface IProductDAO extends GenericDAO<ProductModel>{
//	public List<NewModel> findByCategoryid(Long categoryId);
	public Long save(ProductModel newModel);
	public List<ProductModel> findAll();
	public List<ProductModel> findAll(Pageble pageble);
	public List<ProductModel> getListProductShow(Pageble pageble,String[] price);
	public List<ProductModel> getListProductCategoryShow(Pageble pageble, Long cateid, String[] price);
	public List<ProductModel> getListRelatedProduct(long cateid);
	int getTotalItem();
	public ProductModel findOne(long id);
	void update(ProductModel newModel);
	void delete(long[] ids);
	boolean isSlugExist(String slug);
	ProductModel findProductBySlug(String slug);
	List<ProductModel> findByCategorySlug(Pageble page, String slug);
	public List<ProductModel> searchProduct(ProductModel product, Pageble pageble);	
	public int getTotalItem(String fname);
	public int getTotalItemBySlug(String slug);
	public int getTotalProductCategory(String[] priceFilter);
	public int getTotalProductByCateId(Long cateId, String[] priceFilter);
	public List<ProductModel> findByNameLike(String name);
	public List<ProductModel> getSpecialPromotion();
	public List<ProductModel> getSpecialPromotion(int limit);
	public List<ProductModel> getSpecialPromotion(Long promotionId);
	public List<ProductModel> getSpecialPromotion(Long promotionId,int limit);
	public List<ProductModel> getListNewProducts();
	public List<ProductModel> findByCategoryId(Long categoryId, int limit);
	
}
