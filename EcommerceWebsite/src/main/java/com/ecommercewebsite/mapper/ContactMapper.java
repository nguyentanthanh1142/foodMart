package com.ecommercewebsite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.ecommercewebsite.model.ContactModel;

public class ContactMapper implements RowMapper<ContactModel>{

	@Override
	public ContactModel mapRow(ResultSet result) {
		try {
			ContactModel contact = new ContactModel();
			contact.setId(result.getLong("id"));
			contact.setName(result.getString("name"));
			contact.setPhone(result.getString("phone"));
			contact.setContent(result.getString("content"));
			contact.setEmail(result.getString("email"));
			contact.setAddress(result.getString("address"));
			contact.setSubject(result.getString("subject"));
			contact.setStatus(result.getInt("status"));
			contact.setCreatedAt(result.getTimestamp("createdAt"));
			return contact;
		} catch (SQLException e) {
			return null;
		}
	}
	

}
