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
import com.ecommercewebsite.model.MenuModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IMenuService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin/menu"})
public class MenuController extends HttpServlet{
	@Inject
	private IMenuService menuService;
	private static final long serialVersionUID = -2246907186551942838L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MenuModel model = FormUtil.toModel(MenuModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(menuService.findAll(pageble));
			model.setTotalItem(menuService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			req.setAttribute(SystemConstant.MODEL, model);
			view = "/views/admin/menu/list.jsp";
		}
		else if(type.equals(SystemConstant.EDIT)) {
			List<MenuModel> listmenu = menuService.findAll();
			if(model.getId() !=null)
			{
				model = menuService.findOne(model.getId());
				
			}
			view = "/views/admin/menu/edit.jsp";
			req.setAttribute("listmenu", listmenu);
		}
		req.setAttribute(SystemConstant.MODEL, model);
		
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}

}
