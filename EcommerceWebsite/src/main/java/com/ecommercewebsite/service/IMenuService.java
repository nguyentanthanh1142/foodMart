package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.MenuModel;
import com.ecommercewebsite.paging.Pageble;

public interface IMenuService {
	public List<MenuModel> findAll(Pageble pageble);

	public List<MenuModel> findListMenuByParentId(Long parent_id);

	public int getTotalItem();

	public MenuModel save(MenuModel topic);

	public MenuModel findOne(Long topicId);

	public MenuModel update(MenuModel updateTopic);

	public MenuModel findBySlug(String slug);

	public List<MenuModel> findAll();
	public List<MenuModel> GetMenuShow();
}
