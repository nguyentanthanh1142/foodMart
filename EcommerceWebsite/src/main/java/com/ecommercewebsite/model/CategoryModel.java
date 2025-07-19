package com.ecommercewebsite.model;

import java.util.List;

public class CategoryModel extends AbstractModel<CategoryModel> {

	private String name;
	private String slug;
	private String metadesc;
	private String metakey;
	private String img;
	private long parentId;
	private int status;
	private List<CategoryModel> listChild;
	private List<ProductModel> listProduct;
	private boolean hotCate;
	private String imgPublicId;
	

	public String getImgPublicId() {
		return imgPublicId;
	}

	public void setImgPublicId(String imgPublicId) {
		this.imgPublicId = imgPublicId;
	}

	public List<ProductModel> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductModel> listProduct) {
		this.listProduct = listProduct;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public boolean isHotCate() {
		return hotCate;
	}

	public void setHotCate(boolean hotCate) {
		this.hotCate = hotCate;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getMetadesc() {
		return metadesc;
	}

	public void setMetadesc(String metadesc) {
		this.metadesc = metadesc;
	}

	public String getMetakey() {
		return metakey;
	}

	public void setMetakey(String metakey) {
		this.metakey = metakey;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public List<CategoryModel> getListChild() {
		return listChild;
	}

	public void setListChild(List<CategoryModel> listChild) {
		this.listChild = listChild;
	}

}
