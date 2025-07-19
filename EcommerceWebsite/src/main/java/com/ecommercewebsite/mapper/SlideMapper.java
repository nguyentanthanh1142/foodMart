package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.RoleModel;
import com.ecommercewebsite.model.SlideModel;

public class SlideMapper implements RowMapper<SlideModel> {

	@Override
	public SlideModel mapRow(ResultSet result) {

		try {
			SlideModel model = new SlideModel();
			model.setId(result.getLong("id"));
			model.setCaption(result.getString("caption"));
			model.setImg(result.getString("img"));
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
