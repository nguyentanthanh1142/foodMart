package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.ContactModel;
import com.ecommercewebsite.paging.Pageble;

public interface IContactService {
	public List<ContactModel> findAll();

	public List<ContactModel> findAll(Pageble pageble);

	public ContactModel findOne(long id);

	public int getTotalItem();

	public Long save(ContactModel contact);

}
