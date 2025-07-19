package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.IPageDAO;
import com.ecommercewebsite.dao.ITopicDAO;
import com.ecommercewebsite.model.PageModel;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IPageService;

public class PageService implements IPageService{

	@Inject
	private IPageDAO pageDAO;

	@Inject
	private ITopicDAO topicDAO;

	@Override
	public List<PageModel> findAll() {
		return pageDAO.findAll();
	}

	@Override
	public PageModel save(PageModel newModel) {
		newModel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		newModel.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		TopicModel topic = topicDAO.findBySlug(newModel.getTopicCode());
		newModel.setTopicId(topic.getId());
		Long newId = pageDAO.save(newModel);
		return pageDAO.findOne(newId);
	}

	@Override
	public List<PageModel> findAll(Pageble pageble) {

		return pageDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return pageDAO.getTotalItem();
	}

	@Override
	public PageModel findOne(long id) {
		PageModel model = pageDAO.findOne(id);
		TopicModel topic = topicDAO.findOne(model.getTopicId());
		model.setTopicCode(topic.getSlug());
		return model;
	}

	@Override
	public PageModel update(PageModel model) {
		PageModel old = pageDAO.findOne(model.getId());
		model.setCreatedAt(old.getCreatedAt());
		model.setCreatedBy(old.getCreatedBy());
		model.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		TopicModel topic = topicDAO.findBySlug(model.getTopicCode());
		model.setTopicId(topic.getId());
		pageDAO.update(model);
		return pageDAO.findOne(model.getId());
	}

	@Override
	public void delete(long[] ids) {

		pageDAO.delete(ids);
	}

	@Override
	public PageModel findPageBySlug(String slug) {
		return pageDAO.findPageBySlug(slug);
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
	public List<PageModel> getRecentBlog(int limit) {
		return pageDAO.getRecentBlog(limit);
	}

}
