package com.ecommercewebsite.model;

public class ProductImageModel extends AbstractModel<ProductImageModel>{

	Long product_id;
	String img;
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
}
