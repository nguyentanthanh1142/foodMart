package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.SocialNetWorkModel;
import com.ecommercewebsite.paging.Pageble;

public interface ISocialNetWorkService {
	public void delete(long[] ids);

	public List<SocialNetWorkModel> findAll();

	public List<SocialNetWorkModel> findAll(Pageble pageble);


	public SocialNetWorkModel findOne(long id);

	public List<SocialNetWorkModel> get(int limit);

	public int getTotalItem();

	public SocialNetWorkModel save(SocialNetWorkModel newmModel);

	public SocialNetWorkModel update(SocialNetWorkModel model);
}
