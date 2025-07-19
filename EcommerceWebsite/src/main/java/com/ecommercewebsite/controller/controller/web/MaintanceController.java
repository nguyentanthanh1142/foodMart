package com.ecommercewebsite.controller.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/bao-tri"})
public class MaintanceController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2798631867118596888L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/maintenance/maintenance.jsp");
		rd.forward(req, resp);
	}

	
}
