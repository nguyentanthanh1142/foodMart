package com.ecommercewebsite.controller.web.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.dto.CartDTO;
import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.model.CommentModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.ICommentService;
import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.ecommercewebsite.web.websocket.CommentWebSocket;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-comments" })
public class CommentAPI extends HttpServlet {

	/**
	 * 
	 */
	@Inject
	private ICommentService commentService;
	@Inject
	private static final long serialVersionUID = -3950268839534436301L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CommentModel comment = HttpUtil.of(req.getReader()).toModel(CommentModel.class);
		comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		comment.setUsername(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getFullName());
		comment.setUserId(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getId());
		System.out.println("User  Id " + comment.getUserId());
		System.out.println("Post  Id " + comment.getPostId());
		System.out.println("Content " + comment.getContent());
		System.out.println("User  Id " + comment.getUsername());
		try {
			System.out.println("DA zo duoc try");
			commentService.saveComment(comment);
			System.out.println("DA zo duoc try 2");
			CommentWebSocket.broadcast(comment.getPostId(), "new-comment", comment);
			mapper.writeValue(resp.getOutputStream(), comment);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
		}
	}

}
