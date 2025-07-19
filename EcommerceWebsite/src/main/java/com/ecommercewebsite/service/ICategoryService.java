package com.ecommercewebsite.service;

import java.util.List;
import java.util.Map;

import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.paging.Pageble;

public interface ICategoryService {
	public void delete(long[] ids);

	public List<CategoryModel> findAll();

	public List<CategoryModel> findAll(Pageble pageble);

	public CategoryModel findCategoryBySlug(String slug);

	public CategoryModel findOne(long id);

	int getTotalItem();

	boolean isSlugExist(String slug);

	boolean isTitleExist(String title);

	public CategoryModel save(CategoryModel category);

	public CategoryModel update(CategoryModel model);
	
	public List<CategoryModel> getListChild();
	
	public List<CategoryModel> getHotCategoryShow();
	
	public List<CategoryModel> getCategoryShow();
	public Map<Long,String> getMapCat();
	
	public List<CategoryModel> categoriesHomeShow();
	
}
