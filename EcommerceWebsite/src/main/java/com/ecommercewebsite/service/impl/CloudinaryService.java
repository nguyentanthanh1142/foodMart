package com.ecommercewebsite.service.impl;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommercewebsite.cloudinary.CloudinaryUtil;
import com.ecommercewebsite.service.ICloudinaryService;

public class CloudinaryService implements ICloudinaryService {

	@Override
	public Map uploadFileItem(FileItem item, String folder) {
		
		try {
			InputStream inputStream = item.getInputStream();
			Cloudinary cloudinary = CloudinaryUtil.getInstance();
			
			Map uploadResult = cloudinary.uploader().upload(inputStream, ObjectUtils.asMap("resource_type", "image",
	                "folder", folder
		            ));		
			return uploadResult;
        } catch (Exception e) {
			e.printStackTrace();
            return null;
        }
    }
	@Override
	public boolean deleteImage(String publicId) {
	    try {
	        Map result = CloudinaryUtil.getInstance().uploader().destroy(publicId, ObjectUtils.emptyMap());
	        return "ok".equals(result.get("result"));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
