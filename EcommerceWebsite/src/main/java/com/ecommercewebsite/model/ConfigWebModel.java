package com.ecommercewebsite.model;

public class ConfigWebModel extends AbstractModel<ConfigWebModel>{
	private String webname;
	private String web_detail;
	private String hotline;
	private String logo;
	private String icon;
	private	 String email;
	private String address_store;
	private int status;
	public String getWebname() {
		return webname;
	}
	public void setWebname(String webname) {
		this.webname = webname;
	}
	public String getWeb_detail() {
		return web_detail;
	}
	public void setWeb_detail(String web_detail) {
		this.web_detail = web_detail;
	}
	public String getHotline() {
		return hotline;
	}
	public void setHotline(String hotline) {
		this.hotline = hotline;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress_store() {
		return address_store;
	}
	public void setAddress_store(String address_store) {
		this.address_store = address_store;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
