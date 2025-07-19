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
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.service.ICategoryService;
import com.ecommercewebsite.service.IProductService;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin/product" })
public class ProductController extends HttpServlet {

	@Inject
	private IProductService productService;
	@Inject
	private ICategoryService categoryService;
	private static final long serialVersionUID = 1882724760469534961L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ProductModel model = FormUtil.toModel(ProductModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			model.setListResult(productService.findAll());
			view = "/views/admin/product/list.jsp";
			req.setAttribute("mapCate", categoryService.getMapCat());
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = productService.findOne(model.getId());

			}
			req.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/product/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
