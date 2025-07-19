package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet result) {
		try {
			CategoryModel model = new CategoryModel();
			model.setId(result.getLong("id"));
			model.setName(result.getString("name"));
			model.setSlug(result.getString("slug"));
			model.setImg(result.getString("img"));
			model.setImgPublicId(result.getString("imgPublicId"));
			model.setStatus(result.getInt("status"));
			model.setParentId(result.getLong("parentId"));
			model.setMetadesc(result.getString("metadesc"));
			model.setMetakey(result.getString("metakey"));
			model.setCreatedAt(result.getTimestamp("createdAt"));
			model.setCreatedBy(result.getString("createdBy"));
			model.setModifiedAt(result.getTimestamp("modifiedAt"));
			model.setModifiedBy(result.getString("modifiedBy"));
			model.setHotCate(result.getBoolean("hotCate"));
			
			return model;
		} catch(SQLException e) {
			return null;
		}
	}

}
