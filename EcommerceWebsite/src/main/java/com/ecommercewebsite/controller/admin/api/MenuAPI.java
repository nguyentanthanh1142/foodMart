package com.ecommercewebsite.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.MenuModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.IMenuService;
import com.ecommercewebsite.utils.FormUtil;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-menu" })
public class MenuAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8171999818980999390L;
	@Inject
	private IMenuService menuService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		MenuModel menu = FormUtil.toModel(MenuModel.class, req);
		menu.setListResult(menuService.findListMenuByParentId(menu.getParent_id()));
		
		mapper.writeValue(resp.getOutputStream(), menu.getListResult().isEmpty()?"{}":menu.getListResult());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		MenuModel menu = HttpUtil.of(req.getReader()).toModel(MenuModel.class);
		menu.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		menu.setModifiedBy("");
		menu.setOrders(menu.getOrders()+1);
		menu = menuService.save(menu);
		mapper.writeValue(resp.getOutputStream(), menu);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		MenuModel updateMenu = HttpUtil.of(req.getReader()).toModel(MenuModel.class);
		updateMenu.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		updateMenu = menuService.update(updateMenu);
		mapper.writeValue(resp.getOutputStream(), updateMenu);
	}

}
