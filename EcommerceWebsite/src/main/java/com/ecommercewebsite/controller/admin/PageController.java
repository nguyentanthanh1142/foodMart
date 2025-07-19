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
import com.ecommercewebsite.model.PageModel;
import com.ecommercewebsite.service.IPageService;
import com.ecommercewebsite.service.ITopicService;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin/page"})
public class PageController extends HttpServlet{

	@Inject
	private IPageService pageService;
	@Inject 
	private ITopicService topicService;
	private static final long serialVersionUID = -8908141570964714252L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		PageModel model = FormUtil.toModel(PageModel.class, req);
		String view = "";
		String type = model.getType() != null ?  model.getType(): SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			model.setListResult(pageService.findAll());
			view = "/views/admin/page/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId()!=null)
			{
				model = pageService.findOne(model.getId());
			}
			req.setAttribute("topics", topicService.findAll());
			view = "/views/admin/page/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
