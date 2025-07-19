package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.TopicModel;

public class TopicMapper implements RowMapper<TopicModel> {

	@Override
	public TopicModel mapRow(ResultSet result) {
		try {
			TopicModel model = new TopicModel();
			model.setId(result.getLong("id"));
			model.setName(result.getString("name"));
			model.setSlug(result.getString("slug"));
			model.setMetakey(result.getString("metakey"));
			model.setMetadesc(result.getString("metadesc"));
			model.setShowFooter(result.getInt("showfooter"));
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
