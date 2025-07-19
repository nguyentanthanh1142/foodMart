package com.ecommercewebsite.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.constant.SystemConstant;

@WebServlet(urlPatterns = {"/admin/profile"})	
public class UserController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5490199283926034821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/user/userlogin.jsp");
		rd.forward(req, resp);
	}

	
}
