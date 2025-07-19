package com.ecommercewebsite.model;

import java.util.Map;

public class ReportModel {
	private int value;
	private String time;
	private Map<Long, Integer> mapValue;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Map<Long, Integer> getMapValue() {
		return mapValue;
	}
	public void setMapValue(Map<Long, Integer> mapValue) {
		this.mapValue = mapValue;
	}
	
}	
