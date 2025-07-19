package com.ecommercewebsite.dto;

import com.ecommercewebsite.model.ProductModel;

public class CartDTO {
	private int quanty;
	private double totalprice;
	private ProductModel product;

	public CartDTO() {
		super();
	}

	public CartDTO(int quanty, double totalprice, ProductModel product) {
		super();
		this.quanty = quanty;
		this.totalprice = totalprice;
		this.product = product;
	}
	
	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

}
