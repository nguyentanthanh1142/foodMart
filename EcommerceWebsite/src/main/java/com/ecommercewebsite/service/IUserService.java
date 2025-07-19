package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.paging.Pageble;

public interface IUserService {
	public void saveRememberToken(Long id, String token);

	public int getTotalAccount();

	public String resetPassword(String email);

	public UserModel findUserByLogin(String username, String password, Integer status);

	public UserModel getUserByRmbToken(String rmbToken);

	public UserModel getUserByEmail(String email);

	public UserModel addAcount(UserModel user);

	public List<UserModel> findAllAccount(Pageble pageble);
	
	public void updateGoogleId(String email, String googleId);

	public UserModel insertGoogleUser(UserModel user);
}
