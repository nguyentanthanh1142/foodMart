package com.ecommercewebsite.controller.controller.web;

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
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IProductService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = { "/search/*" })
public class SearchController extends HttpServlet {
	/**
	 * 
	 */
	@Inject
	private IProductService productService;
	private static final long serialVersionUID = -8749491061425431061L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductModel model = FormUtil.toModel(ProductModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		System.out.println("Product name: " + model.getName());
		System.out.println("Sort Name: " + model.getSortName());
		System.out.println("Sort By: " + model.getSortBy());

		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
				new Sorter(model.getSortName(), model.getSortBy()));
		model.setListResult(productService.searchProduct(model, pageble));
		model.setTotalItem(productService.getTotalItem(model.getName()));
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
		System.out.println("ListResult: " + model.getListResult().size());
		System.out.println("Toatal item: " + model.getTotalItem());
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/product/search.jsp");
		rd.forward(req, resp);
	}

}
