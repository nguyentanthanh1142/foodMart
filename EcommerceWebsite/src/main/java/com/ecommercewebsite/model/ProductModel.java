package com.ecommercewebsite.model;

public class ProductModel extends AbstractModel<ProductModel> {
	private String name;
	private String slug;
	private String detail;
	private float price;
	private float pricesale;
	private float weight;
	private String shortdesc;
	private String productimg;
	private String images;
	private Long catid;
	private int status;
	private int available;
	private CategoryModel categoryModel;
	private String imgPublicId;

	public String getImgPublicId() {
		return imgPublicId;
	}

	public void setImgPublicId(String imgPublicId) {
		this.imgPublicId = imgPublicId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getAvailable() {
		return available;
	}

	public CategoryModel getCategoryModel() {
		return categoryModel;
	}

	public Long getCatid() {
		return catid;
	}

	public String getDetail() {
		return detail;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public float getPricesale() {
		return pricesale;
	}

	public String getProductimg() {
		return productimg;
	}

	public String getShortdesc() {
		return shortdesc;
	}

	public String getSlug() {
		return slug;
	}

	public int getStatus() {
		return status;
	}

	public float getWeight() {
		return weight;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public void setCategoryModel(CategoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public void setCatid(Long catid) {
		this.catid = catid;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setPricesale(float pricesale) {
		this.pricesale = pricesale;
	}

	public void setProductimg(String productimg) {
		this.productimg = productimg;
	}

	public void setShortdesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
