package com.ecommercewebsite.service;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;

public interface ICloudinaryService {
	 public Map uploadFileItem(FileItem item, String folder);
	 public boolean deleteImage(String publicId);
}
