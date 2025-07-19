package com.ecommercewebsite.model;

import java.sql.Timestamp;

public class DiscountModel extends AbstractModel<DiscountModel> {

	private String name;
	private String description;
	private String code;
	private int available;
	private int quantity;
	private double pricesale;
	private Timestamp start;
	private Timestamp end;
	private int status;
	private  boolean isProductCoupon;
	
	public int getAvailable() {
		return available;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isProductCoupon() {
		return isProductCoupon;
	}

	public void setProductCoupon(boolean isProductCoupon) {
		this.isProductCoupon = isProductCoupon;
	}

	public String getCode() {
		return code;
	}

	public Timestamp getEnd() {
		return end;
	}

	public double getPricesale() {
		return pricesale;
	}

	public Timestamp getStart() {
		return start;
	}

	public int getStatus() {
		return status;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public void setPricesale(double pricesale) {
		this.pricesale = pricesale;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
