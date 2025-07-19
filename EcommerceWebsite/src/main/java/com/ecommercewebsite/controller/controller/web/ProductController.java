package com.ecommercewebsite.controller.controller.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.constant.SystemConstant;
import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ICategoryService;
import com.ecommercewebsite.service.IProductService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = { "/san-pham/*" })
public class ProductController extends BaseController {

	@Inject
	private IProductService productService;
	@Inject
	private ICategoryService categoryService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8220196550879573943L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "";
		ProductModel model = FormUtil.toModel(ProductModel.class, req);
		String[] priceRanges = req.getParameterValues("priceRange");
		System.out.println("priceRanges: " + Arrays.toString(priceRanges));
		if (priceRanges != null && priceRanges.length > 0) {
			for (int i = 0; i < priceRanges.length; i++) {
				String[] parts = priceRanges[i].split("-");
				System.out.println("price BETWEEN " + parts[0] + " AND " + parts[1] + " ");
				if (i < priceRanges.length - 1) {
					System.out.println(" OR ");
				}
			}
		}
		System.out.println("Sort name :" + model.getSortName());
		System.out.println("Sort by :" + model.getSortBy());
		List<CategoryModel> listCategory = categoryService.getCategoryShow();
		if (req.getPathInfo() == null) {
			Sorter sorter = new Sorter(model.getSortName(), model.getSortBy());
			model.setMaxPageItem(5);
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			if (model.getSortBy() != null) {
				String sortBy = model.getSortBy();
				if (sortBy.equals("ctime")) {
					pageble.getSorter().setSortBy("createdAt");
				} else if (sortBy.equals("price")) {
					pageble.getSorter().setSortBy("pricesale");
				} else if (sortBy.equals("alpha")) {
					pageble.getSorter().setSortBy("name");
				}
			} else {
				pageble.getSorter().setSortBy("createdAt");
				pageble.getSorter().setSortName("desc");
			}
			model.setListResult(productService.getListProductShow(pageble, priceRanges));
			model.setTotalItem(productService.getTotalProductCategory(priceRanges));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/web/product/allproduct.jsp";
			req.setAttribute("sort", sorter);
			req.setAttribute("priceRange", priceRanges);
		} else {
			String slug = req.getPathInfo().substring(1);
			view = "/views/web/product/product.jsp";
			ProductModel product = productService.findProductBySlug(slug);
			req.setAttribute("relatedProducts", productService.getListRelatedProduct(product.getCatid()));
			req.setAttribute("product", product);

		}
		req.setAttribute(SystemConstant.MODEL, model);
		req.setAttribute("listCategory", listCategory);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}

}
