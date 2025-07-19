package com.ecommercewebsite.dao.impl;

import java.util.List;

import com.ecommercewebsite.dao.IRoleDAO;
import com.ecommercewebsite.mapper.RoleMapper;
import com.ecommercewebsite.model.RoleModel;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO{

	@Override
	public List<RoleModel> finAllShow() {
		String sql = "SELECT * FROM role ";
		return query(sql, new RoleMapper());
	}

	
}
