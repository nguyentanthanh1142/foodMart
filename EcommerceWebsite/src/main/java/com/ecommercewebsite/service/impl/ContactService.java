package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.impl.IContactDAO;
import com.ecommercewebsite.model.ContactModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IContactService;

public class ContactService implements IContactService{

	@Inject
	private IContactDAO contactDAO;
	@Override
	public List<ContactModel> findAll() {
		return contactDAO.findAll();
	}

	@Override
	public List<ContactModel> findAll(Pageble pageble) {
		return contactDAO.findAll(pageble);
	}

	@Override
	public ContactModel findOne(long id) {
		return contactDAO.findOne(id);
	}

	@Override
	public int getTotalItem() {
		return contactDAO.getTotalItem();
	}

	@Override
	public Long save(ContactModel contact) {
		contact.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return contactDAO.save(contact);
	}


}
