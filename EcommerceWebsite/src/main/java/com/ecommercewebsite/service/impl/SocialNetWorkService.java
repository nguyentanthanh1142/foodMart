package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.ISocialNetWorkDAO;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.model.SocialNetWorkModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ISocialNetWorkService;

public class SocialNetWorkService implements ISocialNetWorkService{

	@Inject
	private ISocialNetWorkDAO socialNetWorkDAO;
	
	@Override
	public void delete(long[] ids) {
		socialNetWorkDAO.delete(ids);
		
	}

	@Override
	public List<SocialNetWorkModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SocialNetWorkModel> findAll(Pageble pageble) {
		// TODO Auto-generated method stub
		return socialNetWorkDAO.findAll(pageble);
	}

	@Override
	public SocialNetWorkModel findOne(long id) {
		// TODO Auto-generated method stub
		return socialNetWorkDAO.findOne(id);
	}

	@Override
	public List<SocialNetWorkModel> get(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return socialNetWorkDAO.getTotalItem();
	}

	@Override
	public SocialNetWorkModel save(SocialNetWorkModel model) {
		model.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		model.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		Long Id = socialNetWorkDAO.save(model);
		return socialNetWorkDAO.findOne(Id);
	}

	@Override
	public SocialNetWorkModel update(SocialNetWorkModel model) {
		SocialNetWorkModel old = socialNetWorkDAO.findOne(model.getId());
		model.setCreatedAt(old.getCreatedAt());
		model.setCreatedBy(old.getCreatedBy());
		model.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		if (model.getImg() == null) {
			model.setImg(old.getImg());
		}
		socialNetWorkDAO.update(model);
		return model;
	}

}
