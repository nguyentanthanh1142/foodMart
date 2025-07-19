package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.ContactModel;
import com.ecommercewebsite.paging.Pageble;

public interface ContactDAO extends GenericDAO<ContactModel>{

	List<ContactModel> findAll();
	List<ContactModel> findAll(Pageble pageble);
	Long save(ContactModel contact);
	ContactModel findOne(long id);
	int getTotalItem();
}
