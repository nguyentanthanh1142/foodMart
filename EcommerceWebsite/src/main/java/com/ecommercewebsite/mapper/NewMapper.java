package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.NewModel;

public class NewMapper implements RowMapper<NewModel>{
	
	@Override
	public NewModel mapRow(ResultSet result) {
		try {
			NewModel news = new NewModel();
			news.setId(result.getLong("id"));
			news.setTitle(result.getString("title"));
			news.setSlug(result.getString("slug"));
			news.setContent(result.getString("content"));
			news.setThumbnail(result.getString("thumbnail"));
			news.setImgPublicId(result.getString("imgPublicId"));
			news.setShortDescription(result.getString("shortDescription"));
			news.setTopicId(result.getLong("topicId"));
			news.setCreatedAt(result.getTimestamp("createdAt"));
			news.setCreatedBy(result.getString("createdBy"));
			news.setModifiedAt(result.getTimestamp("modifiedAt"));
			news.setModifiedBy(result.getString("modifiedBy"));
			news.setCreatedAtStr();
			return news;
		} catch (SQLException e) {
			return null;
		}
	}
}
