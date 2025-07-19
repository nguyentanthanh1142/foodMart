package com.ecommercewebsite.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.ApiResponse;
import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.service.INoteService;
import com.ecommercewebsite.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-note" })
public class NoteAPI extends HttpServlet {
	/**
	 * 
	 */
	@Inject
	private INoteService noteService;

	private static final long serialVersionUID = 3256333726712006032L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try {
			NoteModel model = HttpUtil.of(req.getReader()).toModel(NoteModel.class);
			noteService.delete(model.getIds());
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
