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
import com.ecommercewebsite.model.ContactModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IContactService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin/contact" })
public class ContactController extends HttpServlet {

	@Inject
	private IContactService contactService;
	private static final long serialVersionUID = 7581985881818054908L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ContactModel model = FormUtil.toModel(ContactModel.class, req);
		String view = "";
		String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
		if (type.equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(contactService.findAll(pageble));
			model.setTotalItem(contactService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			view = "/views/admin/contact/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = contactService.findOne(model.getId());
			}
			req.setAttribute("topics", contactService.findAll());
			view = "/views/admin/contact/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}

}
