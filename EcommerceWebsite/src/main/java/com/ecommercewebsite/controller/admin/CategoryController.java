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
import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.service.ICategoryService;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin/category" })
public class CategoryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 247232181498716122L;
	@Inject
	private ICategoryService categoryService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryModel model = FormUtil.toModel(CategoryModel.class, req);
		String view = "";
		String type = model.getType() != null ?  model.getType(): SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			model.setListResult(categoryService.findAll());
			view = "/views/admin/category/list.jsp";
			req.setAttribute("mapCate", categoryService.getMapCat());
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if(model.getId()!=null)
			{
				model = categoryService.findOne(model.getId());
				
			}
			req.setAttribute("listCate", categoryService.findAll());
			req.setAttribute("topics", categoryService.findAll());
			view = "/views/admin/category/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
