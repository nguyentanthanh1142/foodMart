package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.RoleModel;
import com.ecommercewebsite.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet result) {
		try {
			UserModel user = new UserModel();
			user.setId(result.getLong("id"));
			user.setUserName(result.getString("username"));
			user.setFullName(result.getString("fullName"));
			user.setPassword(result.getString("password"));
			user.setStatus(result.getInt("status"));
			user.setRememberToken(result.getString("remember_token"));
			user.setPhone(result.getString("phone"));
			user.setGoogle_id(result.getString("google_id"));
			user.setIs_google_account(result.getBoolean("is_google_account"));
			user.setPicture(result.getString("picture"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(result.getString("code"));
				role.setName(result.getString("name"));
				user.setRole(role);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return user;
		} catch (SQLException e) {
			return null;
		}
	}
}
