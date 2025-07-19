package com.ecommercewebsite.controller.admin.api;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.model.SocialNetWorkModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.ISocialNetWorkService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-admin-socialnetwork"})
public class SocialNetWorkAPI extends HttpServlet{

	/**
	 * 
	 */
	@Inject
	private ISocialNetWorkService socialNetWorkService;
	private static final long serialVersionUID = 5003729356172854830L;
	private static final String UPLOAD_DIRECTORY = "/uploads";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		

		if (ServletFileUpload.isMultipartContent(req)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			SocialNetWorkModel model = new SocialNetWorkModel();

			try {
				// Tạo map để lưu form fields
				Map<String, String> formFields = new HashMap<>();
				// Ensure the upload directory exists
				String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); // Create the directory if it doesn't exist
				}

				// Parse the request to get the file items
				List<FileItem> items = upload.parseRequest(req);

				// Process the items (form fields or file fields)
				for (FileItem item : items) {
					if (item.isFormField()) {
						// Process regular form fields (non-file fields)
						String fieldName = item.getFieldName();
						String fieldValue = item.getString("UTF-8"); // Default iso-8859-1 so define utf-8
						System.out.println("Form Field: " + fieldName + " = " + fieldValue);
						formFields.put(fieldName, fieldValue); // Lưu vào Map
						// Map form fields to model properties
						if ("field_name_from_form".equals(fieldName)) {
							// model.setSomeProperty(fieldValue); // Set appropriate fields in your model
							// Lưu trữ các giá trị form vào Map
							formFields.put(fieldName, fieldValue);
						}

					} else {
						// Process file upload fields
						String fileName = item.getName();
						if (fileName != null && !fileName.isEmpty()) {
							// Handle file name collision by appending timestamp
							String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
							File uploadedFile = new File(uploadPath, uniqueFileName);
							item.write(uploadedFile);
							formFields.put(item.getFieldName(), uniqueFileName);

							// Set the file path or file name to the model
//	                            model.setImage(uploadedFile.getAbsolutePath());  
						}
					}
				}
				
				String jsonString = mapper.writeValueAsString(formFields);
				model = mapper.readValue(jsonString, SocialNetWorkModel.class); // Sử
				// Set other properties and timestamps
				
				model.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
				model.setModifiedBy(""); // Set the actual user if needed

				// Save the model
				model = socialNetWorkService.save(model);
				mapper.writeValue(resp.getOutputStream(), model);

			} catch (Exception e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File upload failed: " + e.getMessage());
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request is not multipart.");
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");	
		 boolean isMultipart = req.getContentType() != null && req.getContentType().startsWith("multipart/");
		 System.out.println(isMultipart);
		   
		 if (isMultipart) {
			 
	            FileItemFactory factory = new DiskFileItemFactory();
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            SocialNetWorkModel updatePro = new SocialNetWorkModel();
	            
	            try {
	            	
	            	  // Đọc thủ công input stream từ PUT body
	            	ServletInputStream input = req.getInputStream();
	            // Tạo một RequestWrapper để giả lập POST multipart
	            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(req) {
	                @Override
	                public String getMethod() {
	                    return "POST"; // trick FileUpload để nó nghĩ đây là POST
	                }
	            };
	            	
	                // Tạo map để lưu form fields
	                Map<String, String> formFields = new HashMap<>();
	                // Ensure the upload directory exists
	                String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
	                File uploadDir = new File(uploadPath);
	                if (!uploadDir.exists()) {
	                    uploadDir.mkdirs(); // Create the directory if it doesn't exist
	                }

	                // Parse the request to get the file items
	                List<FileItem> items = upload.parseRequest(wrappedRequest);

	                // Process the items (form fields or file fields)
	                for (FileItem item : items) {
	                    if (item.isFormField()) {
	                        // Process regular form fields (non-file fields)
	                        String fieldName = item.getFieldName();
	                        String fieldValue = item.getString("UTF-8"); // Default iso-8859-1 so define utf-8
	                        System.out.println("Form Field: " + fieldName + " = " + fieldValue);
	                        formFields.put(fieldName, fieldValue);  // Lưu vào Map
	                        // Map form fields to model properties
	                        if ("field_name_from_form".equals(fieldName)) {
	                          //  model.setSomeProperty(fieldValue);  // Set appropriate fields in your model
	                        	  // Lưu trữ các giá trị form vào Map
	                            formFields.put(fieldName, fieldValue);
	                        }

	                    } else {
	                        // Process file upload fields
	                        String fileName = item.getName();
	                        String fieldValue = item.getString("UTF-8"); // Default iso-8859-1 so define utf-8
	                        if (fileName != null && !fileName.isEmpty()) {
	                            // Handle file name collision by appending timestamp
	                            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
	                            File uploadedFile = new File(uploadPath, uniqueFileName);
	                            item.write(uploadedFile);
	                            formFields.put(item.getFieldName(), uniqueFileName);
	                            System.out.println("File uploaded to: " + uploadedFile.getAbsolutePath());
	                            
	                            // Set the file path or file name to the model
//	                            model.setImage(uploadedFile.getAbsolutePath());  
	                        }
	                    }
	                }
	               
	                String jsonString = mapper.writeValueAsString(formFields);
	                System.out.println("Form field " + jsonString);
	                updatePro = mapper.readValue(jsonString, SocialNetWorkModel.class);  // Sử
	                // Set other properties and timestamps

	                updatePro.setModifiedAt(new Timestamp(System.currentTimeMillis()));
	                updatePro.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
	                // Save the model
	                updatePro =  socialNetWorkService.update(updatePro);
	                mapper.writeValue(resp.getOutputStream(), updatePro);

	            } catch (Exception e) {
	                e.printStackTrace();
	                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File upload failed: " + e.getMessage());
	            }
	        } else {
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request is not multipart.");
	        }
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ProductModel model = HttpUtil.of(req.getReader()).toModel(ProductModel.class);
		socialNetWorkService.delete(model.getIds());
		mapper.writeValue(resp.getOutputStream(), "{}");
	}

	

}
