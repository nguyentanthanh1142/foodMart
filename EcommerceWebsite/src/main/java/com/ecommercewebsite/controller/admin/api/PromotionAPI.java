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
import com.ecommercewebsite.model.PromotionModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.INoteService;
import com.ecommercewebsite.service.IPromotionService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(urlPatterns = { "/api-admin-promotion" })
public class PromotionAPI extends HttpServlet {

	@Inject
	private IPromotionService promotionService;
	@Inject
	private INoteService noteService;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try {
			PromotionModel coupon = HttpUtil.of(req.getReader()).toModel(PromotionModel.class);
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");

			if (user != null) {
				coupon.setCreatedBy(user.getUserName());
				coupon.setModifiedBy(user.getUserName());
			}
			coupon = promotionService.save(coupon);
			NoteModel note = new NoteModel();
			note.setContent("Admin đã thêm chương trình khuyến mãi mới: " + coupon.getName());
			note.setCreatedBy(user.getUserName());
			noteService.save(note);
			ApiResponse<PromotionModel> responseObj = new ApiResponse<>("Thêm promotion thành công", "success", coupon);
			mapper.writeValue(resp.getOutputStream(), responseObj);
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ApiResponse<Object> errorResponse = new ApiResponse<>("Thêm coupon thất bại: " + e.getMessage(), "error",
					null);
			mapper.writeValue(resp.getOutputStream(), errorResponse);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try {
			PromotionModel updateCoupon = HttpUtil.of(req.getReader()).toModel(PromotionModel.class);
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
			if (user != null) {
				updateCoupon.setModifiedBy(user.getUserName());
			}
			updateCoupon = promotionService.update(updateCoupon);
			ApiResponse<PromotionModel> responseObj = new ApiResponse<>("Cập nhật coupon thành công", "success",
					updateCoupon);
			mapper.writeValue(resp.getOutputStream(), responseObj);
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ApiResponse<Object> errorResponse = new ApiResponse<>("Thêm coupon thất bại: " + e.getMessage(), "error",
					null);
			mapper.writeValue(resp.getOutputStream(), errorResponse);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try {
			PromotionModel model = HttpUtil.of(req.getReader()).toModel(PromotionModel.class);
			promotionService.delete(model.getIds());
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
