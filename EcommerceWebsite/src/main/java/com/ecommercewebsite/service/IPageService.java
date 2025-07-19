package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.PageModel;
import com.ecommercewebsite.paging.Pageble;

public interface IPageService {
	public void delete(long[] ids);

	public List<PageModel> findAll();

	public List<PageModel> findAll(Pageble pageble);

	public PageModel findPageBySlug(String slug);

	public PageModel findOne(long id);

	public List<PageModel> getRecentBlog(int limit);

	public int getTotalItem();

	boolean isSlugExist(String slug);

	boolean isTitleExist(String title);

	public PageModel save(PageModel newmModel);

	public PageModel update(PageModel model);
	

}
