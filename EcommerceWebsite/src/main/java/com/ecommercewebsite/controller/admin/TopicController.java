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
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.service.ITopicService;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = "/admin/topic")
public class TopicController extends HttpServlet {

	@Inject
	private ITopicService topicService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5498216693162668803L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TopicModel model = FormUtil.toModel(TopicModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			model.setListResult(topicService.findAll());
			req.setAttribute(SystemConstant.MODEL, model);
			view = "/views/admin/topic/list.jsp";
		}
		else if(type.equals(SystemConstant.EDIT)) {
			
			if(model.getId() !=null)
			{
				model = topicService.findOne(model.getId());
			}
			view = "/views/admin/topic/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}