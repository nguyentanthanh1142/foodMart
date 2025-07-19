package com.ecommercewebsite.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.service.IConfigWebService;

@WebServlet(urlPatterns= {"/admin/configweb/status/*"})
public class MaintanceController extends HttpServlet{
	private static final long serialVersionUID = 8578198503861927061L;
	@Inject
	private IConfigWebService configService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getPathInfo().substring(1);
		configService.changeStatus(Integer.parseInt(code));
		resp.sendRedirect(req.getContextPath() + "/admin/configweb");
	}

}
