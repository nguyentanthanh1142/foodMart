package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommercewebsite.model.RoleModel;

public interface RowMapper<T>{
	public T mapRow(ResultSet result);
}

