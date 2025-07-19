package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.IUserDAO;
import com.ecommercewebsite.mapper.UserMapper;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.paging.Pageble;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findUserByLogin(String username, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM users as u ");
		sql.append("inner join role as r on r.id = u.roleid ");
		sql.append("where username = ? and password = ? and status = ?");
		List<UserModel> user = query(sql.toString(), new UserMapper(), username, password, status);
		return user.isEmpty() ? null : user.get(0);
	}

	@Override
	public void saveRememberToken(Long id, String token) {
		String sql = "UPDATE users set remember_token = ? where id = ?";
		update(sql, token, id);
	}

	@Override
	public UserModel getUserByRmbToken(String rmbToken) {
		StringBuilder sql = new StringBuilder("SELECT * FROM users as u ");
		sql.append("inner join role as r on r.id = u.roleid ");
		sql.append("where remember_token = ?");
		List<UserModel> user = query(sql.toString(), new UserMapper(), rmbToken);
		return user.isEmpty() ? null : user.get(0);
	}

	@Override
	public UserModel findUserByEmail(String email) {
		StringBuilder sql = new StringBuilder("SELECT * FROM users as u ");
		sql.append("inner join role as r on r.id = u.roleid ");
		sql.append("where username = ?");
		List<UserModel> user = query(sql.toString(), new UserMapper(), email);
		return user.isEmpty() ? null : user.get(0);
	}

	@Override
	public void updatePassword(UserModel user, String newPassword) {
		String sql = "UPDATE users set password = ? where id = ?";
		update(sql, newPassword, user.getId());

	}

	@Override
	public boolean checkUsername(String username) {
		String sql = "SELECT * FROM users where username='" + username + "' limit 1";
		List<UserModel> user = query(sql, new UserMapper());
		System.out.println(user.isEmpty());
		return user.isEmpty();
	}

	@Override
	public Long saveUser(UserModel user) {
		StringBuilder sql = new StringBuilder(
				"insert into users (username,password,fullname,phone,status,roleid,createdAt) ");
		sql.append(" values(?,?,?,?,?,?,?)");
		System.out.println(sql);
		return insert(sql.toString(), user.getUserName(), user.getPassword(), user.getFullName(), user.getPhone(),
				user.getStatus(), user.getRoleId(), user.getCreatedAt());
	}

	@Override
	public UserModel findUserById(Long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM users as u  ");
		sql.append("inner join role as r on r.id = u.roleid ");
		sql.append("where id = ?");
		List<UserModel> user = query(sql.toString(), new UserMapper(), id);
		return user.isEmpty() ? null : user.get(0);
	}

	@Override
	public List<UserModel> findAllAccount(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM users as u  ");
		sql.append("inner join role as r on r.id = u.roleid ");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy()))

		{
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new UserMapper());
	}

	@Override
	public int getTotalAccount() {
		String sql = "SELECT count(*) FROM users";
		return count(sql);
	}

	@Override
	public void updateGoogleId(String email, String googleId) {
		String sql = "UPDATE users set google_id = ? , is_google_account = true where email = ?";
		System.out.println(sql);
		update(sql, googleId, email);
	}

	@Override
	public Long insertGoogleUser(UserModel user) {
		StringBuilder sql = new StringBuilder(
				"insert into users (username,fullname,phone,status,roleid,createdAt,google_id,is_google_account,picture) ");
		sql.append(" values(?,?,?,?,?,?,?,?,?)");
		System.out.println(sql.toString());
		return insert(sql.toString(), user.getUserName(), user.getFullName(), user.getPhone(),
				user.getStatus(), user.getRoleId(), user.getCreatedAt(),user.getGoogle_id(), true,user.getPicture());
	}
}
