package com.ecommercewebsite.model;

public class SlideModel extends AbstractModel<SlideModel>{
	
	private String caption;
	private String img;
	private int status;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
