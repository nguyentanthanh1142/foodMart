package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.IPageDAO;
import com.ecommercewebsite.dao.ITopicDAO;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ITopicService;

public class TopicService implements ITopicService{

	@Inject
	private ITopicDAO topicDAO;
	@Inject
	private IPageDAO pageDAO;
	
	@Override
	public List<TopicModel> findAll(Pageble page) {
		return topicDAO.findAll(page);
		
	}

	@Override
	public int getTotalItem() {
		return topicDAO.getTotalItem();
	}

	@Override
	public TopicModel save(TopicModel topic) {
		topic.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		topic.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		Long topicId = topicDAO.save(topic);
		return topicDAO.findOne(topicId);
	}

	@Override
	public TopicModel findOne(Long topicId) {
		return topicDAO.findOne(topicId);
	}

	@Override
	public TopicModel update(TopicModel model) {
		TopicModel old = topicDAO.findOne(model.getId());
		model.setCreatedAt(old.getCreatedAt());
		model.setCreatedBy(old.getCreatedBy());
		model.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		topicDAO.update(model);
		return topicDAO.findOne(model.getId());
	}

	@Override
	public List<TopicModel> findAll() {
		return topicDAO.findAll();
	}

	@Override
	public void delete(long[] ids) {
		topicDAO.delete(ids);
	}

	@Override
	public List<TopicModel> findTopicShowFooter() {
		List<TopicModel> listTopicShow = topicDAO.findTopicShowFooter();
		for(TopicModel model : listTopicShow)
		{
			model.setListPageFooter(pageDAO.getListPage(model.getId()));
		}
		return listTopicShow;
	}

}
