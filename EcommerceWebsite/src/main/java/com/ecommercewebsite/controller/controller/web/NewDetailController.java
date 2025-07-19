package com.ecommercewebsite.controller.controller.web;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.CommentModel;
import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.service.ICommentService;
import com.ecommercewebsite.service.INewService;

@WebServlet(urlPatterns = {"/tin-tuc/*"})
public class NewDetailController extends BaseController{
	
	@Inject
	private INewService newService;
	@Inject
	private ICommentService commentService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 134246437146380359L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String slug = req.getPathInfo().substring(1);
		System.out.println("Da zo duoc day " + slug);
		NewModel news = newService.findNewBySlug(slug);
		List<CommentModel> comments = commentService.getCommentsByPost(news.getId());
		req.setAttribute("news", news);
		req.setAttribute("comments", comments);
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/new/detail.jsp");
		rd.forward(req, resp);
	}

}
