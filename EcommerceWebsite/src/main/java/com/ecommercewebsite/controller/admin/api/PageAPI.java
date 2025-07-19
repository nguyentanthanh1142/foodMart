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

import com.ecommercewebsite.model.ApiResponse;
import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.model.PageModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.INoteService;
import com.ecommercewebsite.service.IPageService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-page" })
public class PageAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5175847180093704515L;
	private static final String UPLOAD_DIRECTORY = "/uploads";
	@Inject
	private IPageService pageService;
	@Inject
	private INoteService noteService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();

		if (ServletFileUpload.isMultipartContent(req)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			PageModel model = new PageModel();

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
				model = mapper.readValue(jsonString, PageModel.class); // Sử
				// Set other properties and timestamps
				UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
				model.setCreatedBy(user.getUserName());
				model.setModifiedBy(""); // Set the actual user if needed

				// Save the model
				try {
					model = pageService.save(model);
					NoteModel note = new NoteModel();
					note.setContent("Admin đã thêm trang đơn mới: " + model.getTitle());
					note.setCreatedBy(user.getUserName());
					noteService.save(note);
					ApiResponse<PageModel> responseObj = new ApiResponse<>("Đã thêm thành công", "success", model);
					mapper.writeValue(resp.getOutputStream(), responseObj);
				} catch (Exception e) {
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					ApiResponse<Object> errorResponse = new ApiResponse<>(
							"Thực hiện thao tác thất bại: " + e.getMessage(), "error", null);
					mapper.writeValue(resp.getOutputStream(), errorResponse);
				}

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
		resp.setContentType("application/json; charset=UTF-8");
		boolean isMultipart = req.getContentType() != null && req.getContentType().startsWith("multipart/");
		System.out.println(isMultipart);

		if (isMultipart) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			PageModel updateNew = new PageModel();

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
						String fieldValue = item.getString("UTF-8");
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
							System.out.println("File uploaded to: " + uploadedFile.getAbsolutePath());

							// Set the file path or file name to the model
//	                            model.setImage(uploadedFile.getAbsolutePath());  
						}
					}
				}

				String jsonString = mapper.writeValueAsString(formFields);
				System.out.println("Form field " + jsonString);
				updateNew = mapper.readValue(jsonString, PageModel.class); // Sử
				// Set other properties and timestamps
				UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
				updateNew.setModifiedAt(new Timestamp(System.currentTimeMillis()));
				updateNew.setModifiedBy(user.getUserName());
				// Save the model
				try {
					updateNew = pageService.update(updateNew);
					NoteModel note = new NoteModel();
					note.setContent("Admin đã cập nhật trang đơn : " + updateNew.getTitle());
					note.setCreatedBy(user.getUserName());
					noteService.save(note);
					ApiResponse<PageModel> responseObj = new ApiResponse<>("Đã thêm thành công", "success", updateNew);
					mapper.writeValue(resp.getOutputStream(), responseObj);
				} catch (Exception e) {
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					ApiResponse<Object> errorResponse = new ApiResponse<>(
							"Thực hiện thao tác thất bại: " + e.getMessage(), "error", null);
					mapper.writeValue(resp.getOutputStream(), errorResponse);
				}
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

		PageModel model = HttpUtil.of(req.getReader()).toModel(PageModel.class);
		System.out.println(model.getIds());
		try {
			pageService.delete(model.getIds());
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
			NoteModel note = new NoteModel();
			note.setContent("Admin đã xóa các trang đơn : " + model.getIds().toString());
			note.setCreatedBy(user.getUserName());
			noteService.save(note);
			ApiResponse<PageModel> responseObj = new ApiResponse<>("Đã xóa thành công", "success", null);
			mapper.writeValue(resp.getOutputStream(), responseObj);
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ApiResponse<Object> errorResponse = new ApiResponse<>("Thực hiện thao tác thất bại: " + e.getMessage(),
					"error", null);
			mapper.writeValue(resp.getOutputStream(), errorResponse);
		}
		mapper.writeValue(resp.getOutputStream(), "{}");

	}

}