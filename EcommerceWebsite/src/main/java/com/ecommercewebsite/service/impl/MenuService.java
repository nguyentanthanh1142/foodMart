package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.IMenuDAO;
import com.ecommercewebsite.model.MenuModel;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IMenuService;

public class MenuService implements IMenuService{

	@Inject
	private IMenuDAO menuDAO;
	
	@Override
	public List<MenuModel> findAll(Pageble pageble) {
		return menuDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return menuDAO.getTotalItem();
	}

	@Override
	public MenuModel save(MenuModel menu) {
		menu.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		menu.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		Long menuId = menuDAO.save(menu);
		return menuDAO.findOne(menuId);
	}

	@Override
	public MenuModel findOne(Long topicId) {
		// TODO Auto-generated method stub
		return menuDAO.findOne(topicId);
	}

	@Override
	public MenuModel update(MenuModel updateMenu) {
		MenuModel old = menuDAO.findOne(updateMenu.getId());
		updateMenu.setCreatedAt(old.getCreatedAt());
		updateMenu.setCreatedBy(old.getCreatedBy());
		updateMenu.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		menuDAO.update(updateMenu);
		return menuDAO.findOne(updateMenu.getId());
		
	}

	@Override
	public MenuModel findBySlug(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuModel> findAll() {
		return menuDAO.findAll();
	}

	@Override
	public List<MenuModel> findListMenuByParentId(Long parent_id) {
		// TODO Auto-generated method stub
		return menuDAO.findListMenuByParentId(parent_id);
	}

	@Override
	public List<MenuModel> GetMenuShow() {
		// TODO Auto-generated method stub
		return menuDAO.GetMenuShow();
	}

}
