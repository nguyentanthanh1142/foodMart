package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;

public interface ITopicService {

	public int getTotalItem();

	public TopicModel save(TopicModel topic);

	public TopicModel findOne(Long topicId);

	public TopicModel update(TopicModel model);

	public List<TopicModel> findAll();

	public List<TopicModel> findAll(Pageble page);

	public List<TopicModel> findTopicShowFooter();
	
	public void delete(long[] ids);
	
}
