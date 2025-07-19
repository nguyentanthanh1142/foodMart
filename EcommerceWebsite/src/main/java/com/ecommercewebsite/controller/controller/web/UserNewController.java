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

import com.ecommercewebsite.constant.SystemConstant;
import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.INewService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns= {"/tin-tuc"})
public class UserNewController extends BaseController{

	@Inject
	private INewService newService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6062919616469013002L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "";
		NewModel model = FormUtil.toModel(NewModel.class, req);
		Sorter sorter = new Sorter(model.getSortName(), model.getSortBy());
		model.setMaxPageItem(5);
		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
				new Sorter(model.getSortName(), model.getSortBy()));
		pageble.getSorter().setSortBy("createdAt");
		pageble.getSorter().setSortName("desc");
		model.setListResult(newService.findAll(pageble));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
		List<NewModel> list =  newService.findAll();
		req.setAttribute("list", list);
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/new/list.jsp");
		req.setAttribute(SystemConstant.MODEL, model);
		rd.forward(req, resp);
	}
}
