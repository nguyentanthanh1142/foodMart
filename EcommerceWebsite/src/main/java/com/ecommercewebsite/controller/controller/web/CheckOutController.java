package com.ecommercewebsite.controller.controller.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.dto.CartDTO;
import com.ecommercewebsite.utils.SessionUtil;

@WebServlet(urlPatterns= {"/checkout"})
public class CheckOutController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -919132579189270071L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) SessionUtil.getInstance().getValue(req, "Cart");		
		req.setAttribute("Cart", cart);
		RequestDispatcher rd  = req.getRequestDispatcher("/views/web/cart/checkout.jsp");
		rd.forward(req, resp);
	}

	
	
	
	
}
