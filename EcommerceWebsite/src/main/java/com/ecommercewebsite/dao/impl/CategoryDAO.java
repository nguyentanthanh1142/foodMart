package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.ICategoryDAO;
import com.ecommercewebsite.mapper.CategoryMapper;
import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.paging.Pageble;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql = "SELECT * FROM category WHERE id = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), id);
		return category.isEmpty() ? null : category.get(0);
	}

	@Override
	public Long save(CategoryModel category) {
		StringBuilder sql = new StringBuilder(
				"insert into category (img, hotCate, parentId,name,slug,metadesc,metakey,status,createdAt,createdBy,modifiedAt,modifiedBy) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), category.getImg(), category.isHotCate(), category.getParentId(),
				category.getName(), category.getSlug(), category.getMetadesc(), category.getMetakey(),
				category.getStatus(), category.getCreatedAt(), category.getCreatedBy(), category.getModifiedAt(),
				category.getModifiedBy());
	}

	@Override
	public void update(CategoryModel category) {
		StringBuilder sql = new StringBuilder("UPDATE category SET name = ?, slug = ?, metadesc = ?,");
		sql.append(" metakey = ?, status = ?, parentId = ?, img = ?, hotCate = ?,");
		sql.append(" createdAt = ?, createdby = ?, modifiedAt = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), category.getName(), category.getSlug(), category.getMetadesc(), category.getMetakey(),
				category.getStatus(), category.getParentId(), category.getImg(), category.isHotCate(),
				category.getCreatedAt(), category.getCreatedBy(), category.getModifiedAt(), category.getModifiedBy(),
				category.getId());
	}

	@Override
	public CategoryModel findCategoryBySlug(String slug) {
		String sql = "SELECT * FROM category WHERE slug = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), slug);
		return category.isEmpty() ? null : category.get(0);
	}

	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM category where id in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append(ids[i] + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			System.out.println(sql.toString());
			update(sql.toString());
		}
	}

	@Override
	public boolean isSlugExist(String slug) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CategoryModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM category");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		return query(sql.toString(), new CategoryMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM category";
		return count(sql);
	}

	@Override
	public List<CategoryModel> getHotCategoryShow() {
		String sql = "SELECT * FROM category WHERE status = 1 and hotCate = 1";
		return query(sql, new CategoryMapper());
	}

	@Override
	public List<CategoryModel> getCategoryShow() {
		String sql = "SELECT * FROM category WHERE status = 1 ";
		return query(sql, new CategoryMapper());
	}

	@Override
	public List<CategoryModel> categoriesHomeShow() {
		String sql = "SELECT * FROM category WHERE status = 1 and parentid = 0";
		return query(sql, new CategoryMapper());
	}

}
