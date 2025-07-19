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
import com.ecommercewebsite.model.PromotionModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IPromotionService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;
import com.google.gson.Gson;

@WebServlet(urlPatterns = { "/admin/promotion" })
public class PromotionController extends HttpServlet{

	@Inject
	private IPromotionService promotionService;
	private static final long serialVersionUID = 3118729370909867993L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PromotionModel model = FormUtil.toModel(PromotionModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(promotionService.findAll(pageble));
			model.setTotalItem(promotionService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/promotion/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			String jsonList = "[]";
			if (model.getId() != null) {
				model = promotionService.findOne(model.getId());
				jsonList = new Gson().toJson(model.getListProduct()); // Convert sang JSON
			}
			req.setAttribute("listProduct", jsonList);
			req.setAttribute("topics", promotionService.findAll());
			view = "/views/admin/promotion/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}

}
