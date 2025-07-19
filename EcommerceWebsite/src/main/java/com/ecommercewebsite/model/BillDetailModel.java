package com.ecommercewebsite.model;

public class BillDetailModel {

	private Long id;
	private Long product_id;
	private Long bills_id;
	private int quanty;
	private double total;
	private ProductModel productinfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Long getBills_id() {
		return bills_id;
	}

	public void setBills_id(Long bills_id) {
		this.bills_id = bills_id;
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ProductModel getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(ProductModel productinfo) {
		this.productinfo = productinfo;
	}

}
