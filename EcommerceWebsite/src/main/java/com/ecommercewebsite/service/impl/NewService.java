package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.INewDAO;
import com.ecommercewebsite.dao.ITopicDAO;
import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ICloudinaryService;
import com.ecommercewebsite.service.INewService;

public class NewService implements INewService {

	@Inject
	private INewDAO newDAO;

	@Inject
	private ITopicDAO topicDAO;
	
	@Inject
	private ICloudinaryService cloudinaryService;

	@Override
	public List<NewModel> findAll() {
		return newDAO.findAll();
	}

	@Override
	public NewModel save(NewModel newModel) {
		newModel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		newModel.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		TopicModel topic = topicDAO.findBySlug(newModel.getTopicCode());
		newModel.setTopicId(topic.getId());
		Long newId = newDAO.save(newModel);
		return newDAO.findOne(newId);
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {

		return newDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewModel findOne(long id) {
		NewModel model = newDAO.findOne(id);
		TopicModel topic = topicDAO.findOne(model.getTopicId());
		model.setTopicCode(topic.getSlug());
		return model;
	}

	@Override
	public NewModel update(NewModel model) {
		NewModel old = newDAO.findOne(model.getId());
		model.setCreatedAt(old.getCreatedAt());
		model.setCreatedBy(old.getCreatedBy());
		model.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		TopicModel topic = topicDAO.findBySlug(model.getTopicCode());
		model.setTopicId(topic.getId());
		if (model.getThumbnail() == null) {
			model.setThumbnail(old.getThumbnail());
		}
		newDAO.update(model);
		return newDAO.findOne(model.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids){
			NewModel news  = newDAO.findOne(id);
			if(news.getThumbnail()!= null)
			{
				cloudinaryService.deleteImage(news.getImgPublicId());
			}
		}
		newDAO.delete(ids);
	}

	@Override
	public NewModel findNewBySlug(String slug) {
		return newDAO.findNewBySlug(slug);
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
	public List<NewModel> getRecentBlog(int limit) {
		return newDAO.getRecentBlog(limit);
	}

}
