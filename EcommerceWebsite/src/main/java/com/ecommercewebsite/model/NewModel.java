package com.ecommercewebsite.model;

public class NewModel extends AbstractModel<NewModel>{
	
	private Long topicId;
	private String title;
	private String thumbnail;
	private String content;
	private String shortDescription;
	private String topicCode;
	private String slug;
	private String imgPublicId;
	
	public String getImgPublicId() {
		return imgPublicId;
	}
	public void setImgPublicId(String imgPublicId) {
		this.imgPublicId = imgPublicId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getTopicCode() {
		return topicCode;
	}
	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}

	
}
