package com.ecommercewebsite.controller.admin.api;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.cloudinary.utils.ObjectUtils;
import com.ecommercewebsite.cloudinary.CloudinaryUtil;
import com.ecommercewebsite.model.ApiResponse;
import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.ICloudinaryService;
import com.ecommercewebsite.service.IProductImageService;
import com.ecommercewebsite.service.IProductService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-product" })
public class ProductAPI extends HttpServlet {

	@Inject
	private IProductService productService;

	@Inject
	private IProductImageService productImageService;

	@Inject
	private ICloudinaryService cloudinaryService;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2164223756667683409L;
	private static final String UPLOAD_DIRECTORY = "/uploads";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		List<String> imageList = new ArrayList<>();

		if (ServletFileUpload.isMultipartContent(req)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ProductModel model = new ProductModel();

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
						String uniqueFileName = System.currentTimeMillis() + "_" + item.getName();
						File uploadedFile = new File(uploadPath, uniqueFileName);
						item.write(uploadedFile);
						Map uploadResult = CloudinaryUtil.getInstance().uploader().upload(uploadedFile,
								ObjectUtils.asMap("resource_type", "image", "folder", "products"));
						uploadedFile.delete();
//						Map uploadResult = cloudinaryService.uploadFileItem(item, "products");
						if (uploadResult != null) {
							String imageUrl = (String) uploadResult.get("secure_url");
							String newPublicId = (String) uploadResult.get("public_id");
							formFields.put(item.getFieldName(), imageUrl);
							formFields.put("imgPublicId", newPublicId);
							System.out.println("Form Field: " + item.getFieldName() + " = " + imageUrl);
						}

//						System.out.println("Da zo duoc file");
//						String fieldName = item.getFieldName();
//						System.out.println("form field file:"  + fieldName);
//						String fileName = item.getName();
//						if (fileName != null && !fileName.isEmpty()) {
//							// Handle file name collision by appending timestamp
//							String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
//							File uploadedFile = new File(uploadPath, uniqueFileName);
//							item.write(uploadedFile);
//							if ("images".equals(fieldName)) {
//								// model.setSomeProperty(fieldValue); // Set appropriate fields in your model
//								// Lưu trữ các giá trị form vào Map
//								imageList.add( uniqueFileName);
//							}
//							else {
//								formFields.put(fieldName, uniqueFileName);
//							}
//							
////							formFields.put(item.getFieldName(), uniqueFileName);
//							System.out.println("File uploaded to: " + uploadedFile.getAbsolutePath());
//							// Set the file path or file name to the model
////	                            model.setImage(uploadedFile.getAbsolutePath());  
					}
				}
				String jsonString = mapper.writeValueAsString(formFields);
				model = mapper.readValue(jsonString, ProductModel.class); // Sử
				model.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
				model.setModifiedBy(""); // Set the actual user if needed

				// Save the model
				model = productService.save(model);
				if (model == null || model.getId() == null || model.getId() <= 0) {
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					ApiResponse<Object> errorResponse = new ApiResponse<>("Thêm sản phẩm thất bại.", "error", null);
					mapper.writeValue(resp.getOutputStream(), errorResponse);
					return;
				}
				productImageService.save(imageList, model.getId());
				ApiResponse<ProductModel> responseObj = new ApiResponse<>("Thêm topic thành công", "success", model);
				mapper.writeValue(resp.getOutputStream(), responseObj);

			} catch (Exception e) {
				e.printStackTrace();
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
				ApiResponse<Object> errorResponse = new ApiResponse<>("Upload thất bại: " + e.getMessage(), "error", null);
				mapper.writeValue(resp.getOutputStream(), errorResponse);
			}
		} else

		{
			
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request is not multipart.");
			
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		boolean isMultipart = req.getContentType() != null && req.getContentType().startsWith("multipart/");

		if (isMultipart) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ProductModel updatePro = new ProductModel();

			try {

				ServletInputStream input = req.getInputStream();
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
				List<String> subImageList = new ArrayList<>();
				// Process the items (form fields or file fields)
				for (FileItem item : items) {
					if (item.isFormField()) {
						// Process regular form fields (non-file fields)
						String fieldName = item.getFieldName();
						String fieldValue = item.getString("UTF-8"); // Default iso-8859-1 so define utf-8
						System.out.println("Form Field: " + fieldName + " = " + fieldValue);
						formFields.put(fieldName, fieldValue); // Lưu vào Map
						// Map form fields to model properties
						if ("images".equals(fieldName)) {
							// model.setSomeProperty(fieldValue); // Set appropriate fields in your model
							// Lưu trữ các giá trị form vào Map
							System.out.println("Form Field cua nhieu anh: " + fieldName + " = " + fieldValue);
							subImageList.add(fieldName);
						}

					} else {
						String fileName = item.getName();
						if (fileName != null && !fileName.isEmpty()) {
							String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
							File uploadedFile = new File(uploadPath, uniqueFileName);
							item.write(uploadedFile);
							Map uploadResult = CloudinaryUtil.getInstance().uploader().upload(uploadedFile,
									ObjectUtils.asMap("resource_type", "image", "folder", "products"));
							uploadedFile.delete();
							if (uploadResult != null) {
								String imageUrl = (String) uploadResult.get("secure_url");
								String newPublicId = (String) uploadResult.get("public_id");
								formFields.put(item.getFieldName(), imageUrl);
								formFields.put("imgPublicId", newPublicId);
								System.out.println("Form Field: " + item.getFieldName() + " = " + imageUrl);
							}
						}
					}
				}
				String jsonString = mapper.writeValueAsString(formFields);
				updatePro = mapper.readValue(jsonString, ProductModel.class); // Sử
				updatePro.setModifiedAt(new Timestamp(System.currentTimeMillis()));
				updatePro.setModifiedBy(
						((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
				updatePro = productService.update(updatePro);
				ApiResponse<ProductModel> responseObj = new ApiResponse<>("Cập nhật thành công", "success", updatePro);
				mapper.writeValue(resp.getOutputStream(), responseObj);
			} catch (Exception e) {
				e.printStackTrace();
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
				ApiResponse<Object> errorResponse = new ApiResponse<>("Upload thất bại: " + e.getMessage(), "error", null);
				mapper.writeValue(resp.getOutputStream(), errorResponse);
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
		try {
			ProductModel model = HttpUtil.of(req.getReader()).toModel(ProductModel.class);
			productService.delete(model.getIds());
			ApiResponse<NoteModel> responseObj = new ApiResponse<>("Đã xóa thành công", "success");
			mapper.writeValue(resp.getOutputStream(), responseObj);
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ApiResponse<Object> errorResponse = new ApiResponse<>("Thực hiện xóa thất bại: " + e.getMessage(), "error",
					null);
			mapper.writeValue(resp.getOutputStream(), errorResponse);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		mapper.writeValue(resp.getOutputStream(), productService.findAll());
	}

}
