package com.ecommercewebsite.model;

public class BillModel extends AbstractModel<BillModel> {
	private String code;
	private String reductionCode;
	private String email;
	private String display_name;
	private String phone;
	private String address;
	private String city;
	private String district;
	private String province;
	private String note;
	private double total;
	private int quanty;
	private int status;
	private int coupon;
	private Long coupon_id;

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCode() {
		return code;
	}

	public int getCoupon() {
		return coupon;
	}

	public Long getCoupon_id() {
		return coupon_id;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public String getDistrict() {
		return district;
	}

	public String getEmail() {
		return email;
	}

	public String getNote() {
		return note;
	}

	public String getPhone() {
		return phone;
	}

	public String getProvince() {
		return province;
	}

	public int getQuanty() {
		return quanty;
	}

	public String getReductionCode() {
		return reductionCode;
	}

	public int getStatus() {
		return status;
	}

	public double getTotal() {
		return total;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}

	public void setCoupon_id(Long coupon_id) {
		this.coupon_id = coupon_id;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public void setReductionCode(String reductionCode) {
		this.reductionCode = reductionCode;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
