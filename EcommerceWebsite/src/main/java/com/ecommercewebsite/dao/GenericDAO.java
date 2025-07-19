package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.mapper.ResultSetHandler;
import com.ecommercewebsite.mapper.RowMapper;

public interface GenericDAO<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

	void update(String sql, Object... parameters);

	Long insert(String sql, Object... parameters);
	
	int count(String sql, Object... parameters);

	<R> R querySingleResult(String sql, ResultSetHandler<R> handler, Object... parameters);

}
