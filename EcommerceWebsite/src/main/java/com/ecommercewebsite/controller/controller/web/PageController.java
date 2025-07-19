package com.ecommercewebsite.controller.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.PageModel;
import com.ecommercewebsite.service.IPageService;

@WebServlet(urlPatterns = {"/trang-don/*"})
public class PageController extends BaseController{
	@Inject
	private IPageService pageService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String slug = req.getPathInfo().substring(1);
		PageModel page = pageService.findPageBySlug(slug);
		req.setAttribute("page", page);
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/new/page.jsp");
		rd.forward(req, resp);
	}
	
	
}
