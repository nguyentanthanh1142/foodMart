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
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ICouponService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;
import com.google.gson.Gson;

@WebServlet(urlPatterns = { "/admin/coupon" })
public class CouponController extends HttpServlet{

	
	@Inject
	private ICouponService couponService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -918525865477940280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CouponModel model = FormUtil.toModel(CouponModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(couponService.findAll(pageble));
			model.setTotalItem(couponService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/coupon/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			String jsonList = "[]";
			if (model.getId() != null) {
				model = couponService.findOne(model.getId());
				jsonList = new Gson().toJson(model.getListProduct()); // Convert sang JSON
			}
			req.setAttribute("listProduct", jsonList);
			req.setAttribute("topics", couponService.findAll());
			view = "/views/admin/coupon/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}

	
}
