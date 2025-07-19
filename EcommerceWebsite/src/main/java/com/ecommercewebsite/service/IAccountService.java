package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.UserModel;

public interface IAccountService {
	
	public List<UserModel> findAllAccount();
	
}
