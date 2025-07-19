package com.ecommercewebsite.model;

import java.util.List;

public class TopicModel extends AbstractModel<TopicModel>{
	private String name;
	private String slug;
	private String metakey;
	private String metadesc;
	private int showFooter;
	private int status;
	private List<PageModel> listPageFooter;
//	
	public String getName() {
		return name;
	}
	public List<PageModel> getListPageFooter() {
		return listPageFooter;
	}
	public void setListPageFooter(List<PageModel> listPageFooter) {
		this.listPageFooter = listPageFooter;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getMetakey() {
		return metakey;
	}
	public void setMetakey(String metakey) {
		this.metakey = metakey;
	}
	public String getMetadesc() {
		return metadesc;
	}
	public void setMetadesc(String metadesc) {
		this.metadesc = metadesc;
	}
	public int getShowFooter() {
		return showFooter;
	}
	public void setShowFooter(int showFooter) {
		this.showFooter = showFooter;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
