package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.PageModel;
import com.ecommercewebsite.paging.Pageble;

public interface IPageDAO extends GenericDAO<PageModel>{
	public List<PageModel> findByCategoryid(Long categoryId);
	public Long save(PageModel newModel);
	public List<PageModel> findAll();
	public List<PageModel> findAll(Pageble pageble);
	int getTotalItem();
	public PageModel findOne(long id);
	void update(PageModel newModel);
	void delete(long[] ids);
	boolean isTitleExist(String title);
	boolean isSlugExist(String slug);
	PageModel findPageBySlug(String slug);
	public List<PageModel> getRecentBlog(int limit);
	public List<PageModel> getListPage(long topicId);
}
