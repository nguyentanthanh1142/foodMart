package com.ecommercewebsite.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.ApiResponse;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.ICouponService;
import com.ecommercewebsite.service.INoteService;
import com.ecommercewebsite.service.ITopicService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-coupon" })
public class CouponAPI extends HttpServlet {

	@Inject
	private ICouponService couponService;
	@Inject
	private INoteService noteService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3561764623415010819L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		try {
			CouponModel coupon = HttpUtil.of(req.getReader()).toModel(CouponModel.class);
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");

			if (user != null) {
				coupon.setCreatedBy(user.getUserName());
				coupon.setModifiedBy(user.getUserName());
			}
			coupon = couponService.save(coupon);
			NoteModel note = new NoteModel();
			note.setContent("Admin đã thêm mã giảm giá mới: " + coupon.getName());
			note.setCreatedBy(user.getUserName());
			noteService.save(note);
			ApiResponse<CouponModel> responseObj = new ApiResponse<>("Thêm coupon thành công", "success", coupon);
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
			CouponModel updateCoupon = HttpUtil.of(req.getReader()).toModel(CouponModel.class);
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
			if (user != null) {
				updateCoupon.setModifiedBy(user.getUserName());
			}
			updateCoupon = couponService.update(updateCoupon);
			ApiResponse<CouponModel> responseObj = new ApiResponse<>("Cập nhật coupon thành công", "success",
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
			CouponModel model = HttpUtil.of(req.getReader()).toModel(CouponModel.class);
			couponService.delete(model.getIds());
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
