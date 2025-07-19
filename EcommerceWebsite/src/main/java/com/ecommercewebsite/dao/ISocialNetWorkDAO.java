package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.SocialNetWorkModel;
import com.ecommercewebsite.paging.Pageble;

public interface ISocialNetWorkDAO extends GenericDAO<SocialNetWorkModel>{
	public void delete(long[] ids);

	public List<SocialNetWorkModel> findAll();

	public List<SocialNetWorkModel> findAll(Pageble pageble);


	public SocialNetWorkModel findOne(long id);

	public List<SocialNetWorkModel> get(int limit);

	public int getTotalItem();

	public Long save(SocialNetWorkModel newmModel);

	public void update(SocialNetWorkModel model);
}
