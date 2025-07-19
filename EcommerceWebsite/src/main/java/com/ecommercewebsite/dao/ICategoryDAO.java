package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.paging.Pageble;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {

	public List<CategoryModel> findAll();
	public List<CategoryModel> findAll(Pageble pageble);
	public List<CategoryModel> getHotCategoryShow();
	public CategoryModel findOne(long id);
	
	Long  save( CategoryModel category);
	public void update (CategoryModel category);
	public CategoryModel findCategoryBySlug(String slug);
	public void delete(long[] ids);
	boolean isSlugExist(String slug);
	int getTotalItem();
	public List<CategoryModel> getCategoryShow();
	public List<CategoryModel> categoriesHomeShow();
}
