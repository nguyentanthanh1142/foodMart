package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.MenuModel;
import com.ecommercewebsite.paging.Pageble;

public interface IMenuDAO extends GenericDAO<MenuModel> {
	public List<MenuModel> findAll(Pageble pageble);

	public List<MenuModel> findListMenuByParentId(Long parent_id);

	public int getTotalItem();

	public Long save(MenuModel topic);

	public MenuModel findOne(Long topicId);

	public void update(MenuModel updateTopic);

	public MenuModel findBySlug(String slug);

	public List<MenuModel> findAll();
	
	public List<MenuModel> GetMenuShow();
}
