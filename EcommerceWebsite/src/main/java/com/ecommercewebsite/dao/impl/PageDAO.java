package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.IPageDAO;
import com.ecommercewebsite.mapper.NewMapper;
import com.ecommercewebsite.mapper.PageMapper;
import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.model.PageModel;
import com.ecommercewebsite.paging.Pageble;

public class PageDAO extends AbstractDAO<PageModel> implements IPageDAO{
	
	@Override
	public List<PageModel> findByCategoryid(Long categoryId) {
		String sql = "SELECT * from news where category = ?";
		return query(sql, new PageMapper(), categoryId);
	}

	@Override
	public Long save(PageModel newModel) {
		StringBuilder sql = new StringBuilder(
				"insert into page (title,slug,thumbnail,shortDescription,content,topicId,createdAt,createdBy,modifiedAt,modifiedBy,status) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), newModel.getTitle(), newModel.getSlug(), newModel.getThumbnail(),
				newModel.getShortDescription(), newModel.getContent(), newModel.getTopicId(), newModel.getCreatedAt(),
				newModel.getCreatedBy(), newModel.getModifiedAt(), newModel.getModifiedBy(),newModel.getStatus());
	}

	@Override
	public List<PageModel> findAll() {
		String sql = "SELECT * FROM page";
		return query(sql, new PageMapper());
	}

	@Override
	public List<PageModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM page");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		return query(sql.toString(), new PageMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM page";
		return count(sql);
	}

	@Override
	public PageModel findOne(long id) {
		String sql = "SELECT  * FROM page WHERE id = ?";
		List<PageModel> news = query(sql, new PageMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(PageModel newModel) {
		StringBuilder sql = new StringBuilder("UPDATE page SET title = ?, thumbnail = ?, slug = ?,");
		sql.append(" shortDescription = ?, content = ?, topicId = ?,");
		sql.append(" createdAt = ?, createdby = ?, modifiedAt = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getSlug(),
				newModel.getShortDescription(), newModel.getContent(), newModel.getTopicId(), newModel.getCreatedAt(),
				newModel.getCreatedBy(), newModel.getModifiedAt(), newModel.getModifiedBy(), newModel.getId());
	}

	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM page where id in (");
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
		String sql = "SELECT * from page where title = " + title + " limit 1";
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
	public PageModel findPageBySlug(String slug) {
		String sql = "SELECT * FROM page where slug = '" + slug + "'";
		List<PageModel> news = query(sql, new PageMapper());
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public List<PageModel> getRecentBlog(int limit) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news ORDER BY createdAt DESC LIMIT " + limit);
		return query(sql.toString(), new PageMapper());
	}
	@Override
	public List<PageModel> getListPage(long topicId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM page where status = 1 and topicId =  " + topicId);
		System.out.println(sql.toString());
		return query(sql.toString(), new PageMapper());
	}
}
