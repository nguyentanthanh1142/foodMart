package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.MenuModel;

public class MenuMapper implements RowMapper<MenuModel> {

	@Override
	public MenuModel mapRow(ResultSet result) {
		try {
			MenuModel model = new MenuModel();
			model.setId(result.getLong("id"));
			model.setName(result.getString("name"));
			model.setSlug(result.getString("slug"));
			model.setOrders(result.getInt("orders"));
			model.setParent_id(result.getLong("parent_id"));
			model.setStatus(result.getInt("status"));
			model.setCreatedAt(result.getTimestamp("createdAt"));
			model.setCreatedBy(result.getString("createdBy"));
			model.setModifiedAt(result.getTimestamp("modifiedAt"));
			model.setModifiedBy(result.getString("modifiedBy"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}
}