package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.PageModel;

public class PageMapper implements RowMapper<PageModel> {

	@Override
	public PageModel mapRow(ResultSet result) {
		try {
			PageModel page = new PageModel();
			page.setId(result.getLong("id"));
			page.setTitle(result.getString("title"));
			page.setSlug(result.getString("slug"));
			page.setContent(result.getString("content"));
			page.setThumbnail(result.getString("thumbnail"));
			page.setShortDescription(result.getString("shortDescription"));
			page.setTopicId(result.getLong("topicId"));
			page.setCreatedAt(result.getTimestamp("createdAt"));
			page.setCreatedBy(result.getString("createdBy"));
			page.setModifiedAt(result.getTimestamp("modifiedAt"));
			page.setModifiedBy(result.getString("modifiedBy"));
			page.setStatus(result.getInt("status"));
			page.setCreatedAtStr();
			return page;
		} catch (SQLException e) {
			return null;
		}
	}
}
