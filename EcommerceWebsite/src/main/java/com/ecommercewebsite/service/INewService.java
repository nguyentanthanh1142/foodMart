package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.paging.Pageble;

public interface INewService {
	public void delete(long[] ids);

	public List<NewModel> findAll();

	public List<NewModel> findAll(Pageble pageble);

	public NewModel findNewBySlug(String slug);

	public NewModel findOne(long id);

	public List<NewModel> getRecentBlog(int limit);

	public int getTotalItem();

	boolean isSlugExist(String slug);

	boolean isTitleExist(String title);

	public NewModel save(NewModel newmModel);

	public NewModel update(NewModel model);

}
