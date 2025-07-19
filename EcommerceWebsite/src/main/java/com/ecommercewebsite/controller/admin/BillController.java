package com.ecommercewebsite.controller.admin;

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
import com.ecommercewebsite.model.BillDetailModel;
import com.ecommercewebsite.model.BillModel;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.service.IBillService;
import com.ecommercewebsite.service.ICouponService;
import com.ecommercewebsite.utils.FormUtil;


@WebServlet(urlPatterns = "/admin/bill")
public class BillController extends HttpServlet{
	
	private static final long serialVersionUID = 3015965760892603932L;
		@Inject
		private IBillService billService;
		@Inject
		private ICouponService couponService;
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			BillModel model = FormUtil.toModel(BillModel.class, req);
			String view = "";
			String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
			if (type.equals(SystemConstant.LIST)) {
				model.setListResult(billService.findAll());
				req.setAttribute(SystemConstant.MODEL, model);
				view = "/views/admin/bill/list.jsp";
			}
			else if(type.equals(SystemConstant.EDIT)) {
				
				if(model.getId() !=null)
				{
					model = billService.findOne(model.getId());
					List<BillDetailModel> billDetail = billService.getBillsDetail(model.getId());
					double total = 0;
					for (BillDetailModel it : billDetail) {
						total += it.getTotal();
					}
					CouponModel couponEntity = couponService.findCouponById(model.getCoupon_id());
					req.setAttribute("couponEntity", couponEntity);
					req.setAttribute("billdetail", billDetail);
					req.setAttribute("total", total);
				}
				view = "/views/admin/bill/edit.jsp";
			}
			req.setAttribute(SystemConstant.MODEL, model);
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
}
