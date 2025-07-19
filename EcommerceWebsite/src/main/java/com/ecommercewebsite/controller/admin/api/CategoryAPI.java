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

import com.cloudinary.utils.ObjectUtils;
import com.ecommercewebsite.cloudinary.CloudinaryUtil;
import com.ecommercewebsite.model.ApiResponse;
import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.ICategoryService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-category" })
public class CategoryAPI extends HttpServlet {

	private static final long serialVersionUID = -6210404105271644580L;
	private static final String UPLOAD_DIRECTORY = "/uploads";
	@Inject
	private ICategoryService categoryService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();

		if (ServletFileUpload.isMultipartContent(req)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			CategoryModel model = new CategoryModel();

			try {
				Map<String, String> formFields = new HashMap<>();
				String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}
				List<FileItem> items = upload.parseRequest(req);
				for (FileItem item : items) {
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						String fieldValue = item.getString("UTF-8"); // Default iso-8859-1 so define utf-8
						formFields.put(fieldName, fieldValue);
						if ("field_name_from_form".equals(fieldName)) {
							formFields.put(fieldName, fieldValue);
						}

					} else {
						String fileName = item.getName();
						if (fileName != null && !fileName.isEmpty()) {
							String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
							File uploadedFile = new File(uploadPath, uniqueFileName);
							item.write(uploadedFile);
							Map uploadResult = CloudinaryUtil.getInstance().uploader().upload(uploadedFile,
									ObjectUtils.asMap("resource_type", "image", "folder", "category"));
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
				model = mapper.readValue(jsonString, CategoryModel.class); // Sử
				model.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
				model.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName()); 
				model = categoryService.save(model);
				ApiResponse<CategoryModel> responseObj = new ApiResponse<>("Thêm topic thành công", "success", model);
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
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		boolean isMultipart = req.getContentType() != null && req.getContentType().startsWith("multipart/");
		if (isMultipart) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			CategoryModel updateNew = new CategoryModel();
			try {
				ServletInputStream input = req.getInputStream();
				HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(req) {
					@Override
					public String getMethod() {
						return "POST";
					}
				};

				Map<String, String> formFields = new HashMap<>();
				String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); 
				}
				List<FileItem> items = upload.parseRequest(wrappedRequest);

				for (FileItem item : items) {
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						String fieldValue = item.getString("UTF-8"); 
						formFields.put(fieldName, fieldValue); 
					} else {
						String fileName = item.getName();
						if (fileName != null && !fileName.isEmpty()) {
							String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
							File uploadedFile = new File(uploadPath, uniqueFileName);
							item.write(uploadedFile);
							Map uploadResult = CloudinaryUtil.getInstance().uploader().upload(uploadedFile,
									ObjectUtils.asMap("resource_type", "image", "folder", "category"));
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
				updateNew = mapper.readValue(jsonString, CategoryModel.class); 
				updateNew.setModifiedAt(new Timestamp(System.currentTimeMillis()));
				updateNew.setModifiedBy(
						((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
				updateNew = categoryService.update(updateNew);
				ApiResponse<CategoryModel> responseObj = new ApiResponse<>("Cập nhật thành công", "success", updateNew);
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
			CategoryModel model = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
			categoryService.delete(model.getIds());
			ApiResponse<NoteModel> responseObj = new ApiResponse<>("Đã xóa thành công", "success");
			mapper.writeValue(resp.getOutputStream(), responseObj);
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ApiResponse<Object> errorResponse = new ApiResponse<>("Thực hiện xóa thất bại: " + e.getMessage(), "error",
					null);
			mapper.writeValue(resp.getOutputStream(), errorResponse);
		}

	}

}
