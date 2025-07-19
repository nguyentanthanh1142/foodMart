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
import com.ecommercewebsite.model.SlideModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ISlideService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin/slide" })
public class SlideController extends HttpServlet{

	private static final long serialVersionUID = 8409005432918118077L;
	@Inject
	private ISlideService slideService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		SlideModel model = FormUtil.toModel(SlideModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(slideService.findAll(pageble));
			model.setTotalItem(slideService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/slide/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = slideService.findOne(model.getId());

			}
			view = "/views/admin/slide/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
