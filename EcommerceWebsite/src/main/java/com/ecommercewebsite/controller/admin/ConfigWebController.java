package com.ecommercewebsite.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.constant.SystemConstant;
import com.ecommercewebsite.model.ConfigWebModel;
import com.ecommercewebsite.service.IConfigWebService;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin/configweb"})
public class ConfigWebController extends HttpServlet{
	
	@Inject
	private IConfigWebService configService;
	private static final long serialVersionUID = -1289880568443570918L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		ConfigWebModel model = FormUtil.toModel(ConfigWebModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			req.setAttribute("config", configService.findConfigweb());
			view = "/views/admin/config/configweb.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			req.setAttribute("config", configService.findConfigweb());
			view = "/views/admin/config/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
