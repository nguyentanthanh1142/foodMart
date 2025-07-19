package com.ecommercewebsite.model;

public class UserModel extends AbstractModel<UserModel> {
	private String userName;
	private String password;
	private String fullName;
	private String rememberToken;
	private String phone;
	private String google_id;
	private boolean is_google_account;
	private String picture;
	private int status;
	private Long roleId;
	private RoleModel role = new RoleModel();

	public String getGoogle_id() {
		return google_id;
	}

	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}

	public boolean isIs_google_account() {
		return is_google_account;
	}

	public void setIs_google_account(boolean is_google_account) {
		this.is_google_account = is_google_account;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getRememberToken() {
		return rememberToken;
	}

	public RoleModel getRole() {
		return role;
	}

	public Long getRoleId() {
		return roleId;
	}

	public int getStatus() {
		return status;
	}

	public String getUserName() {
		return userName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
