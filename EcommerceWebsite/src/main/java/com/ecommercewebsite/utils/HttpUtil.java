package com.ecommercewebsite.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	String value;
	private Map<String, String[]> formData;

	public HttpUtil(Map<String, String[]> formData) {
		this.formData = formData;
	}

	public HttpUtil(String value) {
		this.value = value;
	}

	public <T> T toModel(Class<T> tclass) {
		try {
			return new ObjectMapper().readValue(value, tclass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public <T> T toModel(TypeReference<T> tRef) {
	    try {
	        return new ObjectMapper().readValue(value, tRef);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.println("Loi httpUtil");
		}
		return new HttpUtil(sb.toString());
	}

	
	public static HttpUtil ofMultipart(Map<String, String[]> formData) {
		return new HttpUtil(formData);
	}
}
