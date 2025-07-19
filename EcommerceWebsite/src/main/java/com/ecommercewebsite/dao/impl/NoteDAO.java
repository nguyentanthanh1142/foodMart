package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.INoteDAO;
import com.ecommercewebsite.mapper.NewMapper;
import com.ecommercewebsite.mapper.NoteMapper;
import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.paging.Pageble;

public class NoteDAO extends AbstractDAO<NoteModel> implements INoteDAO {

	@Override
	public List<NoteModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM note");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new NoteMapper());
	}

	@Override
	public List<NoteModel> findAllByUser(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM note where createdBy = ");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new NoteMapper());
	}

	@Override
	public Long save(NoteModel note) {
		StringBuilder sql = new StringBuilder("insert into note (content,createdBy) ");
		sql.append("values (?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), note.getContent(), note.getCreatedBy());
	}

	@Override
	public NoteModel findById(Long id) {
		String sql = "SELECT * FROM note where id =  " + id;
		List<NoteModel> result = query(sql, new NoteMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM note";
		return count(sql);
	}

	@Override
	public int getTotalItemBW(String to, String from) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM note ");
		if (!to.isEmpty() && !from.isEmpty()) {
			sql.append(" where createdAt between '" + to  + "' and '" + from +"'");
		}
		System.out.println(sql);
		return count(sql.toString());
	}

	@Override
	public List<NoteModel> findNoteBetween(String to, String from, Pageble pageble) {
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM note ");
		if (!to.isEmpty() && !from.isEmpty()) {
			sql.append(" WHERE createdAt between '" + to  + "' and '" + from +"'");
		}
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new NoteMapper());
	}

	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM note where id in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append(ids[i] + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			update(sql.toString());
		}
	}

}
