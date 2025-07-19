package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.ecommercewebsite.dao.ICategoryDAO;
import com.ecommercewebsite.dao.IProductDAO;
import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ICategoryService;
import com.ecommercewebsite.service.ICloudinaryService;

public class CategoryService implements ICategoryService{

	@Inject
	private ICategoryDAO categoryDAO;
	@Inject 
	private IProductDAO productDAO;
	@Inject
	private ICloudinaryService cloudinaryService;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public List<CategoryModel> findAll(Pageble pageble) {
		return categoryDAO.findAll(pageble);
	}

	@Override
	public CategoryModel save(CategoryModel category) {
		category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		category.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		Long cateId = categoryDAO.save(category);
		return categoryDAO.findOne(cateId);
	}

	@Override
	public CategoryModel findOne(long id) {
		return categoryDAO.findOne(id);
	}

	@Override
	public CategoryModel update(CategoryModel category) {
		CategoryModel old = categoryDAO.findOne(category.getId());
		category.setCreatedAt(old.getCreatedAt());
		category.setCreatedBy(old.getCreatedBy());
		category.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		if (category.getImg() == null) {
			category.setImg(old.getImg());
		}
		categoryDAO.update(category);
		return categoryDAO.findOne(category.getId());
	}

	@Override
	public CategoryModel findCategoryBySlug(String slug) {
		return categoryDAO.findCategoryBySlug(slug);
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids){
			CategoryModel category  = categoryDAO.findOne(id);
			if(category.getImg()!= null)
			{
				cloudinaryService.deleteImage(category.getImgPublicId());
			}
		}
		categoryDAO.delete(ids);
	}

	@Override
	public boolean isTitleExist(String title) {
		return false;
	}

	@Override
	public boolean isSlugExist(String slug) {
		return false;
	}

	@Override
	public int getTotalItem() {
		return categoryDAO.getTotalItem();
	}

	@Override
	public List<CategoryModel> getListChild() {
		List<CategoryModel> list = categoryDAO.findAll();
	    Map<Long, CategoryModel> mapCategory = new HashMap<>();
	    List<CategoryModel> rootCategories = new ArrayList<>();

	    for (CategoryModel cate : list) {
	        cate.setListChild(new ArrayList<>());
	        mapCategory.put(cate.getId(), cate);
	    }

	    for (CategoryModel cate : list) {
	        if (cate.getParentId() != 0) {
	            CategoryModel parent = mapCategory.get(cate.getParentId());
	            if (parent != null) {
	                parent.getListChild().add(cate);
	            }
	        } else {
	            rootCategories.add(cate);
	        }
	    }

	    return rootCategories;
	}

	@Override
	public List<CategoryModel> getHotCategoryShow() {
		// TODO Auto-generated method stub
		return categoryDAO.getHotCategoryShow();
	}

	@Override
	public List<CategoryModel> getCategoryShow() {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryShow();
	}

	@Override
	public Map<Long, String> getMapCat() {
		Map<Long,String> result = new HashMap<>();
		List<CategoryModel> listCat = categoryDAO.findAll();
		for(CategoryModel cate: listCat)
		{
			result.put(cate.getId(), cate.getName());
		}
		return result;
	}

	@Override
	public List<CategoryModel> categoriesHomeShow() {
		List<CategoryModel> listCategory = categoryDAO.categoriesHomeShow();
		for(CategoryModel category: listCategory)
		{
			category.setListProduct(productDAO.findByCategoryId(category.getId(), 7));
		}
		return listCategory;
	}

}
