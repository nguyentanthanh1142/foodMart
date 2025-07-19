package com.ecommercewebsite.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.ApiResponse;
import com.ecommercewebsite.model.BillModel;
import com.ecommercewebsite.model.MenuModel;
import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.IBillService;
import com.ecommercewebsite.utils.FormUtil;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-change-bill-status" })
public class BillAPI extends HttpServlet {

	@Inject
	private IBillService billService;

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		BillModel bill = HttpUtil.of(req.getReader()).toModel(BillModel.class);
		bill.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		billService.changeBillStatus(bill);
		ApiResponse<BillModel> responseObj = new ApiResponse<>("Cập nhật thành công", "success", bill);
		mapper.writeValue(resp.getOutputStream(), responseObj);
	}

}
