package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.SocialNetWorkModel;

public class SocialNetWorkMapper implements RowMapper<SocialNetWorkModel> {

	@Override
	public SocialNetWorkModel mapRow(ResultSet result) {
		try {
			SocialNetWorkModel model = new SocialNetWorkModel();
			model.setId(result.getLong("id"));
			model.setName(result.getString("name"));
			model.setImg(result.getString("img"));
			model.setIcon(result.getString("icon"));
			model.setAddress(result.getString("address"));
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
