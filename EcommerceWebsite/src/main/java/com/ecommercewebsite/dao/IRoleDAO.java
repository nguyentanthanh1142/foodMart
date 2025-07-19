package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.RoleModel;

public interface IRoleDAO extends GenericDAO<RoleModel>{

	public List<RoleModel> finAllShow();
	
}
