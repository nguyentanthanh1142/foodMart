package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.INewDAO;
import com.ecommercewebsite.mapper.NewMapper;
import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findByCategoryid(Long categoryId) {
		String sql = "SELECT * from news where category = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		StringBuilder sql = new StringBuilder(
				"insert into news (title,slug,thumbnail,shortDescription,content,topicId,createdAt,createdBy,modifiedAt,modifiedBy,imgPublicId) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), newModel.getTitle(), newModel.getSlug(), newModel.getThumbnail(),
				newModel.getShortDescription(), newModel.getContent(), newModel.getTopicId(), newModel.getCreatedAt(),
				newModel.getCreatedBy(), newModel.getModifiedAt(), newModel.getModifiedBy(), newModel.getImgPublicId());
	}

	@Override
	public List<NewModel> findAll() {
		String sql = "SELECT * FROM NEWS";
		return query(sql, new NewMapper());
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news where status = 1 ");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortBy() + " " + pageble.getSorter().getSortName() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news where status = 1 ";
		return count(sql);
	}

	@Override
	public NewModel findOne(long id) {
		String sql = "SELECT  * FROM news WHERE id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel newModel) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?, slug = ?,");
		sql.append(" shortDescription = ?, content = ?, topicId = ?,");
		sql.append(" createdAt = ?, createdby = ?, modifiedAt = ?, modifiedby = ?, imgPublicId=? WHERE id = ?");
		update(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getSlug(),
				newModel.getShortDescription(), newModel.getContent(), newModel.getTopicId(), newModel.getCreatedAt(),
				newModel.getCreatedBy(), newModel.getModifiedAt(), newModel.getModifiedBy(), newModel.getImgPublicId(),
				newModel.getId());
	}

	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM news where id in (");
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
	public boolean isTitleExist(String title) {
		String sql = "SELECT * from title where title = " + title + " limit 1";
		List<NewModel> news = query(sql, new NewMapper());
		return news.isEmpty() ? false : true;
	}

	@Override
	public boolean isSlugExist(String slug) {
		String sql = "SELECT * from title where slug = " + slug + " limit 1";
		List<NewModel> news = query(sql, new NewMapper());
		return news.isEmpty() ? false : true;
	}

	@Override
	public NewModel findNewBySlug(String slug) {
		String sql = "SELECT * FROM news where slug = '" + slug + "'";
		List<NewModel> news = query(sql, new NewMapper());
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public List<NewModel> getRecentBlog(int limit) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news ORDER BY createdAt DESC LIMIT " + limit);
		return query(sql.toString(), new NewMapper());
	}
}
