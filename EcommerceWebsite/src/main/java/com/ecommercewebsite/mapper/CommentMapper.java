package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.CommentModel;

public class CommentMapper implements RowMapper<CommentModel> {

	@Override
	public CommentModel mapRow(ResultSet result) {
		try {
			CommentModel model = new CommentModel();
			model.setId(result.getLong("id"));
			model.setContent(result.getString("content"));
			model.setPostId(result.getLong("postId"));
			model.setParentId(result.getLong("parentId"));
			model.setCreatedAt(result.getTimestamp("createdAt"));
			if(result.getString("fullname")!=null)
			{
				model.setUsername(result.getString("fullname"));
			}
			
			return model;
		} catch (SQLException e) {
			return null;
		}
	}
}
