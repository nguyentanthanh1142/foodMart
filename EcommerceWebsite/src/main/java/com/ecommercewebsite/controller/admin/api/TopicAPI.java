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
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.INoteService;
import com.ecommercewebsite.service.ITopicService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-admin-topic"})
public class TopicAPI extends HttpServlet{

	@Inject
	private ITopicService topicService;
	@Inject
	private INoteService noteService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8751165356556859233L;

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try {
			TopicModel model = HttpUtil.of(req.getReader()).toModel(TopicModel.class);
			topicService.delete(model.getIds());
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		  try {
	            TopicModel topic = HttpUtil.of(req.getReader()).toModel(TopicModel.class);
	            UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");

	            if (user != null) {
	                topic.setCreatedBy(user.getUserName());
	                topic.setModifiedBy(user.getUserName());
	            }
	            topic = topicService.save(topic);
	            NoteModel note = new NoteModel();
	            note.setContent("Admin đã thêm chủ đề mới: " + topic.getName());
	            note.setCreatedBy(user.getUserName());
	            noteService.save(note);
	            ApiResponse<TopicModel> responseObj = new ApiResponse<>("Thêm topic thành công", "success", topic);
	            mapper.writeValue(resp.getOutputStream(), responseObj);

	        } catch (Exception e) {
	            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            ApiResponse<Object> errorResponse = new ApiResponse<>("Thêm topic thất bại: " + e.getMessage(), "error", null);
	            mapper.writeValue(resp.getOutputStream(), errorResponse);
	        }
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        try {
            TopicModel topic = HttpUtil.of(req.getReader()).toModel(TopicModel.class);
            UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");

            if (user != null) {
                topic.setModifiedBy(user.getUserName());
            }

            topic = topicService.update(topic);
            ApiResponse<TopicModel> responseObj = new ApiResponse<>("Cập nhật topic thành công", "success", topic);
            mapper.writeValue(resp.getOutputStream(), responseObj);

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ApiResponse<Object> errorResponse = new ApiResponse<>("Cập nhật topic thất bại: " + e.getMessage(), "error", null);
            mapper.writeValue(resp.getOutputStream(), errorResponse);
        }
    }
}
