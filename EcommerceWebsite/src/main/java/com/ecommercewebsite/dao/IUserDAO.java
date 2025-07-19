package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.paging.Pageble;

public interface IUserDAO extends GenericDAO<UserModel> {

	public UserModel findUserByLogin(String username, String password, Integer status);

	public void saveRememberToken(Long id, String token);

	public UserModel getUserByRmbToken(String rmbToken);

	public UserModel findUserByEmail(String email);
	
	public Long insertGoogleUser(UserModel user);

	public void updatePassword(UserModel user, String newPassword);

	public boolean checkUsername(String username);

	public Long saveUser(UserModel user);

	public UserModel findUserById(Long id);

	public List<UserModel> findAllAccount(Pageble pageble);
	
	public int getTotalAccount();
	public void updateGoogleId(String email, String googleId);
}
