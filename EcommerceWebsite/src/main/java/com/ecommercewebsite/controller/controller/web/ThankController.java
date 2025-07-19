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

import com.ecommercewebsite.model.BillDetailModel;
import com.ecommercewebsite.model.BillModel;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.service.IBillService;
import com.ecommercewebsite.service.ICouponService;

@WebServlet(urlPatterns = {"/checkout/thankyou/*"})
public class ThankController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8007585924920843957L;
	@Inject
	private IBillService billService;
	@Inject
	private ICouponService conponService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String code = req.getPathInfo().substring(1);
			BillModel bill = billService.findBillsConfirm(code);
			List<BillDetailModel> billdetail = billService.getBillsDetail(bill.getId());
			double total = 0;
			for (BillDetailModel it : billdetail) {
				total += it.getTotal();
			}
			CouponModel coupon = conponService.findCouponById(bill.getCoupon_id());
			req.setAttribute("couponEntity", coupon);
			req.setAttribute("bill", bill);
			req.setAttribute("billdetail", billdetail);
			req.setAttribute("total", total);
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/trang-chu");
			return;
		}
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/cart/comfirmcheckout.jsp");
		rd.forward(req, resp);
	}

	
	
	
}
