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
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IRoleService;
import com.ecommercewebsite.service.IUserService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin/account"})
public class AccountController extends HttpServlet{
	private static final long serialVersionUID = 2897830896907815808L;
	@Inject 
	private IUserService userService;
	@Inject
	private IRoleService roleService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel model = FormUtil.toModel(UserModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(userService.findAllAccount(pageble));
			model.setTotalItem(userService.getTotalAccount());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			req.setAttribute(SystemConstant.MODEL, model);
			view = "/views/admin/user/list.jsp";
		}
		else if(type.equals(SystemConstant.EDIT)) {
			
			if(model.getUserName() !=null)
			{
				model = userService.getUserByEmail(model.getUserName());
				
			}
			req.setAttribute("listrole", roleService.findAllShow());
			view = "/views/admin/user/changerole.jsp";
		}
		else if (type.equals("detail")) {
			
			if(model.getUserName() !=null)
			{
				model = userService.getUserByEmail(model.getUserName());
			}
			view = "/views/admin/user/profileuser.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
