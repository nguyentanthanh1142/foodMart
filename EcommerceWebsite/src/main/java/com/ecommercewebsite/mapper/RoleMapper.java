package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.RoleModel;


public class RoleMapper implements RowMapper<RoleModel>{

	@Override
	public RoleModel mapRow(ResultSet result) {
		try {
			RoleModel model = new RoleModel();
			model.setId(result.getLong("id"));
			model.setName(result.getString("name"));
			model.setCode(result.getString("code"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
