package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.ConfigWebModel;

public class ConfigWebMapper implements RowMapper<ConfigWebModel> {

	@Override
	public ConfigWebModel mapRow(ResultSet result) {
		try {
			ConfigWebModel config = new ConfigWebModel();
			config.setId(result.getLong("id"));
			config.setWebname(result.getString("webname"));
			config.setWeb_detail(result.getString("web_detail"));
			config.setHotline(result.getString("hotline"));
			config.setLogo(result.getString("logo"));
			config.setEmail(result.getString("email"));
			config.setIcon(result.getString("icon"));
			config.setAddress_store(result.getString("address_store"));
			config.setStatus(result.getInt("status"));
			return config;
		} catch (SQLException e) {
			return null;
		}
	}

}
