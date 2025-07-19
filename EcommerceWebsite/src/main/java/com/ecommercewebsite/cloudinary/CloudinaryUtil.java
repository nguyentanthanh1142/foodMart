package com.ecommercewebsite.cloudinary;

import java.util.ResourceBundle;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUtil {
	
	public static Cloudinary getInstance() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("cloudinary");
		Cloudinary cloudinary = new Cloudinary(ObjectUtils
				.asMap("cloud_name", resourceBundle.getString("cloudName"), 
						"api_key", resourceBundle.getString("apiKey"),
						"api_secret", resourceBundle.getString("apiSecret"),
						"secure", true));
		return cloudinary;
	}

}
