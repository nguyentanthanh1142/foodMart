package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.NoteModel;

public class NoteMapper implements RowMapper<NoteModel>{

	@Override
	public NoteModel mapRow(ResultSet result) {
		try {
			NoteModel model = new NoteModel();
			model.setId(result.getLong("id"));
			model.setContent(result.getString("content"));
			model.setCreatedAt(result.getTimestamp("createdAt"));
			model.setCreatedBy(result.getString("createdBy"));
			return model;
		} catch (SQLException e) {
			return null;
		}
	}

}
