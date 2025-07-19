package com.ecommercewebsite.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.IRoleDAO;
import com.ecommercewebsite.model.RoleModel;
import com.ecommercewebsite.service.IRoleService;

public class RoleService implements IRoleService{

	@Inject
	private IRoleDAO roleDAO;
	
	@Override
	public List<RoleModel> findAllShow() {
		// TODO Auto-generated method stub
		return roleDAO.finAllShow();
	}

}
