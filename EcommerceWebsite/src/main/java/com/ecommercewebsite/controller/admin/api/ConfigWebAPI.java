package com.ecommercewebsite.controller.admin.api;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ecommercewebsite.model.ConfigWebModel;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.IConfigWebService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-configweb" })
public class ConfigWebAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4811429793023776570L;
	private static final String UPLOAD_DIRECTORY = "/uploads";
	@Inject
	private IConfigWebService configService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();

		if (ServletFileUpload.isMultipartContent(req)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ConfigWebModel model = new ConfigWebModel();

			try {
				Map<String, String> formFields = new HashMap<>();
				String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); // 
				}

				List<FileItem> items = upload.parseRequest(req);

				for (FileItem item : items) {
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						String fieldValue = item.getString("UTF-8"); // Default iso-8859-1 so define utf-8
						System.out.println("Form Field: " + fieldName + " = " + fieldValue);
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
							formFields.put(item.getFieldName(), uniqueFileName);
						}
					}
				}

				String jsonString = mapper.writeValueAsString(formFields);
				System.out.println("Form field " + jsonString);
				model = mapper.readValue(jsonString, ConfigWebModel.class);
				model.setModifiedAt(new Timestamp(System.currentTimeMillis()));
				model.setModifiedBy(
						((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
				model = configService.update(model);
				mapper.writeValue(resp.getOutputStream(), model);

			} catch (Exception e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File upload failed: " + e.getMessage());
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request is not multipart.");
		}

	}
}