package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;

public interface ITopicDAO extends GenericDAO<TopicModel> {

	public List<TopicModel> findAll(Pageble pageble);
	public int getTotalItem();
	public Long save(TopicModel topic);
	public TopicModel findOne(Long topicId);
	public void update(TopicModel updateTopic);
	public TopicModel findBySlug(String slug);
	public List<TopicModel> findAll();
	public void delete(long[] ids);
	public List<TopicModel> findTopicShowFooter();
}
