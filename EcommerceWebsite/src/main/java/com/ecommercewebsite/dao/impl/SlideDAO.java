package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.ISlideDAO;
import com.ecommercewebsite.mapper.SlideMapper;
import com.ecommercewebsite.model.SlideModel;
import com.ecommercewebsite.paging.Pageble;

public class SlideDAO extends AbstractDAO<SlideModel> implements ISlideDAO {

	@Override
	public List<SlideModel> findAll() {
		String sql = "SELECT * FROM slide ";
		return query(sql, new SlideMapper());
	}

	@Override
	public List<SlideModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM slide");

		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		} else {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " desc");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new SlideMapper());
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SlideModel findOne(long id) {
		String sql = "SELECT * FROM slide where id = " + id;
		System.out.println(sql);
		List<SlideModel> product = query(sql, new SlideMapper());
		return product.isEmpty() ? null : product.get(0);
	}

	@Override
	public Long save(SlideModel slide) {
		StringBuilder sql = new StringBuilder(
				"insert into slide (caption,img,status,createdAt, modifiedAt, createdBy, modifiedBy) ");
		sql.append("values (?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), slide.getCaption(), slide.getImg(), slide.getStatus(), slide.getCreatedAt(),
				slide.getModifiedAt(), slide.getCreatedBy(), slide.getModifiedBy());
	}

	@Override
	public void update(SlideModel newSlide) {
		StringBuilder sql = new StringBuilder("UPDATE slide SET caption = ?, img = ?, status = ?,");
		sql.append("  modifiedAt = ?, modifiedBy = ? WHERE id = ?");
		update(sql.toString(), newSlide.getCaption(), newSlide.getImg(), newSlide.getStatus(), newSlide.getModifiedAt(),
				newSlide.getModifiedBy(), newSlide.getId());
	}

	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM slide where id in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append(ids[i] + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			System.out.println(sql.toString());
			update(sql.toString());
		}
	}

}
