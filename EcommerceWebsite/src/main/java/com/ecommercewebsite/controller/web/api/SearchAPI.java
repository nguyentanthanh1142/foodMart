package com.ecommercewebsite.controller.web.api;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-product-search" })
public class SearchAPI extends HttpServlet{
	
	@Inject
	private IProductService productService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        List<ProductModel> products = productService.findByNameLike(name);
		mapper.writeValue(resp.getOutputStream(), products);
	}
	
	
}
