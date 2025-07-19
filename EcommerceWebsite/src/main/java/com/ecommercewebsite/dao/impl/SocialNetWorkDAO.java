package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.ISocialNetWorkDAO;
import com.ecommercewebsite.mapper.SocialNetWorkMapper;
import com.ecommercewebsite.model.SocialNetWorkModel;
import com.ecommercewebsite.paging.Pageble;

public class SocialNetWorkDAO extends AbstractDAO<SocialNetWorkModel> implements ISocialNetWorkDAO {

	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM socialnetwork where id in (");
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
	public List<SocialNetWorkModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SocialNetWorkModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM socialnetwork");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		return query(sql.toString(), new SocialNetWorkMapper());
	}

	@Override
	public SocialNetWorkModel findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SocialNetWorkModel> get(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT * FROM socialnetwork";
		return count(sql);
	}

	@Override
	public Long save(SocialNetWorkModel social) {
		StringBuilder sql = new StringBuilder(
				"insert into socialnetwork (name,img,icon,address,status,createdAt, modifiedAt, createdBy, modifiedBy) ");
		sql.append("values (?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), social.getName(), social.getImg(), social.getIcon(), social.getAddress(),
				social.getStatus(), social.getCreatedAt(), social.getModifiedAt(), social.getCreatedBy(),
				social.getModifiedBy());
	}

	@Override
	public void update(SocialNetWorkModel model) {
		StringBuilder sql = new StringBuilder("UPDATE product SET name = ?, img = ?, icon = ?,");
		sql.append(" address = ?, status = ?,");
		sql.append(" modifiedAt = ?, modifiedBy = ? WHERE id = ?");
		update(sql.toString(), model.getName(), model.getImg(), model.getIcon(), model.getAddress(), model.getStatus(),
				model.getModifiedAt(), model.getModifiedBy(), model.getId());
	}

}
