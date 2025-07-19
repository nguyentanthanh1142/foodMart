package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.IMenuDAO;
import com.ecommercewebsite.mapper.MenuMapper;
import com.ecommercewebsite.mapper.TopicMapper;
import com.ecommercewebsite.model.MenuModel;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;

public class MenuDAO extends AbstractDAO<MenuModel> implements IMenuDAO {

	@Override
	public List<MenuModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM menu");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new MenuMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM menu";
		return count(sql);
	}

	@Override
	public Long save(MenuModel topic) {
		StringBuilder sql = new StringBuilder(
				"insert into menu (name,slug,status,orders,parent_id,createdAt,createdBy,modifiedAt,modifiedBy) ");
		sql.append("values (?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), topic.getName(), topic.getSlug(), topic.getStatus(), topic.getOrders(),
				topic.getParent_id(), topic.getCreatedAt(), topic.getCreatedBy(), topic.getModifiedAt(),
				topic.getModifiedBy());
	}

	@Override
	public MenuModel findOne(Long menuId) {
		String sql = "SELECT * FROM menu where id = ?";

		List<MenuModel> menu = query(sql, new MenuMapper(), menuId);

		return menu.isEmpty() ? null : menu.get(0);
	}

	@Override
	public void update(MenuModel updateTopic) {
		StringBuilder sql = new StringBuilder("UPDATE menu SET name = ?, slug = ?,");
		sql.append(" status = ?, orders = ?, parent_id = ?,");
		sql.append(" createdat = ?, createdby = ?, modifiedat = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateTopic.getName(), updateTopic.getSlug(), updateTopic.getStatus(),
				updateTopic.getOrders(), updateTopic.getParent_id(), updateTopic.getCreatedAt(),
				updateTopic.getCreatedBy(), updateTopic.getModifiedAt(), updateTopic.getModifiedBy(),
				updateTopic.getId());

	}

	@Override
	public MenuModel findBySlug(String slug) {
		return null;
	}

	@Override
	public List<MenuModel> findAll() {
		String sql = "SELECT * FROM menu where status != 0 ";
		return query(sql, new MenuMapper());
	}

	@Override
	public List<MenuModel> findListMenuByParentId(Long parent_id) {
		String sql = "SELECT * FROM menu WHERE parent_id = " + parent_id + " and status = 1";
		return query(sql, new MenuMapper());
	}

	@Override
	public List<MenuModel> GetMenuShow() {
		String sql = "SELECT * FROM menu where status = 1 and parent_id = 0 ORDER BY orders asc";
		return query(sql, new MenuMapper());
	}

}
