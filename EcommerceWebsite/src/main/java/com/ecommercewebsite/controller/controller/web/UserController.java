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

import com.ecommercewebsite.model.BillModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.IBillService;
import com.ecommercewebsite.service.impl.BillService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;

@WebServlet(urlPatterns = { "/tai-khoan" })
public class UserController extends HttpServlet {

	@Inject
	private IBillService billService;

	private static final long serialVersionUID = 3214892656098879430L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "";
		String action = req.getParameter("action");
		System.out.println("Path: " + action);
		if(action != null)
		{
			switch (action) {
			case "don-hang":
				int page = Integer.parseInt(req.getParameter("page"));
				System.out.print(page);
				int maxPageItem = 2;
				Pageble pageble = new PageRequest(page, 5, new Sorter("", ""));
				String email = ((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName();

				int totalItem = billService.countBillByEmail(email);
				int totalPage = (int) Math.ceil((double) totalItem / maxPageItem);
				List<BillModel> bills = billService.GetDataBillByEmail(pageble, email);
				view = "/views/web/user/userorder.jsp";
				req.setAttribute("bills", bills);
				req.setAttribute("totalItem", totalItem);
				req.setAttribute("totalPage", totalPage);
				break;

			case "edit":

				break;
			case "remove":

				break;
			case "clear":
				break;
			default:
			}
			;
		}
		else {
			view = "/views/web/user/userview.jsp";
		}
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}

}
