package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel>{
	public List<NewModel> findByCategoryid(Long categoryId);
	public Long save(NewModel newModel);
	public List<NewModel> findAll();
	public List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
	public NewModel findOne(long id);
	void update(NewModel newModel);
	void delete(long[] ids);
	boolean isTitleExist(String title);
	boolean isSlugExist(String slug);
	NewModel findNewBySlug(String slug);
	
	public List<NewModel> getRecentBlog(int limit);
}
