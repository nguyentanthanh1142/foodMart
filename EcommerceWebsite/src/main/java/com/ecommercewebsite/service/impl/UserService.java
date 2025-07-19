package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.RandomStringUtils;

import com.ecommercewebsite.dao.IUserDAO;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IUserService;

public class UserService implements IUserService {

	@Inject
	private IUserDAO userDAO;

	@Override
	public UserModel findUserByLogin(String username, String password, Integer status) {
		return userDAO.findUserByLogin(username, password, status);
	}

	@Override
	public void saveRememberToken(Long id, String token) {
		userDAO.saveRememberToken(id, token);

	}

	@Override
	public UserModel getUserByRmbToken(String rmbToken) {
		return userDAO.getUserByRmbToken(rmbToken);
	}

	@Override
	public String resetPassword(String email) {
		UserModel user = userDAO.findUserByEmail(email);
		String randomPassword = RandomStringUtils.randomAlphanumeric(10);
		user.setPassword(randomPassword);
		userDAO.updatePassword(user, randomPassword);
		return randomPassword;
	}

	@Override
	public UserModel addAcount(UserModel user) {

		if (!userDAO.checkUsername(user.getUserName())) {
			return null;
		}
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		user.setStatus(1);
		user.setRoleId((long) 2);
		Long userId = userDAO.saveUser(user);
		return userDAO.findUserById(userId);
	}

	@Override
	public List<UserModel> findAllAccount(Pageble pageble) {
		return userDAO.findAllAccount(pageble);
	}

	@Override
	public int getTotalAccount() {
		return userDAO.getTotalAccount();
	}

	@Override
	public UserModel getUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}

	@Override
	public void updateGoogleId(String email, String googleId) {
		userDAO.updateGoogleId(email, googleId);

	}

	@Override
	public UserModel insertGoogleUser(UserModel user) {
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		user.setStatus(1);
		user.setRoleId((long) 2);
		Long userId = userDAO.insertGoogleUser(user);
		return userDAO.findUserById(userId);

	}

}
