package com.ecommercewebsite.model;

import java.sql.Timestamp;
import java.util.List;

public class PromotionModel extends AbstractModel<PromotionModel> {

	private String name;
	private String description;
	private int available;
	private int quantity;
	private double pricesale;
	private double limitedDiscount;
	private double minValue;
	private Timestamp start;
	private Timestamp end;
	private int status;
	private List<Long> listProduct;
	private  boolean isProductPromotion;
	private boolean byPercent;
	private List<ProductModel> listProductPromotion;
	

	
	public List<ProductModel> getListProductPromotion() {
		return listProductPromotion;
	}

	public void setListProductPromotion(List<ProductModel> listProductPromotion) {
		this.listProductPromotion = listProductPromotion;
	}

	public boolean getIsProductPromotion() {
		return isProductPromotion;
	}

	public void setProductPromotion(boolean isProductPromotion) {
		this.isProductPromotion = isProductPromotion;
	}

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

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public boolean isByPercent() {
		return byPercent;
	}

	public void setByPercent(boolean byPercent) {
		this.byPercent = byPercent;
	}

	public double getLimitedDiscount() {
		return limitedDiscount;
	}

	public void setLimitedDiscount(double limitedDiscount) {
		this.limitedDiscount = limitedDiscount;
	}

	public List<Long> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Long> listProduct) {
		this.listProduct = listProduct;
	}

}
