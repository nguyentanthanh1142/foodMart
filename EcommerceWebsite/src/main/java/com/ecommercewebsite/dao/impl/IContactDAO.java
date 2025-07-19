package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.ContactDAO;
import com.ecommercewebsite.mapper.ContactMapper;
import com.ecommercewebsite.mapper.NewMapper;
import com.ecommercewebsite.model.ContactModel;
import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.paging.Pageble;

public class IContactDAO extends AbstractDAO<ContactModel> implements ContactDAO {

	
	@Override
	public List<ContactModel> findAll() {
		String sql = "SELECT * FROM contact";
		return query(sql, new ContactMapper());
	}
	@Override
	public List<ContactModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM contact");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		return query(sql.toString(), new ContactMapper());
	}
	@Override
	public Long save(ContactModel contact) {
		StringBuilder sql = new StringBuilder(
				"insert into contact (name,phone,email,address,subject,content,status,createdAt) ");
		sql.append("values (?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), contact.getName(), contact.getPhone(), contact.getEmail(),
				contact.getAddress(), contact.getSubject(), contact.getContent(), contact.getStatus(),
				contact.getCreatedAt());

	}
	@Override
	public ContactModel findOne(long id) {
		String sql = "SELECT  * FROM contact WHERE id = " + id;
		List<ContactModel> contact = query(sql, new ContactMapper());
		return contact.isEmpty() ? null : contact.get(0);
	}
	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM contact";
		return count(sql);
	}
}
