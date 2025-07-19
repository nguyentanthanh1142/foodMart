package com.ecommercewebsite.controller.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.ContactModel;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.service.IContactService;
import com.ecommercewebsite.service.impl.ContactService;
import com.ecommercewebsite.utils.FormUtil;
import com.ecommercewebsite.utils.HttpUtil;

@WebServlet(urlPatterns = { "/lien-he" })
public class ContactController extends BaseController {

	@Inject
	private IContactService contactService;
	
	private static final long serialVersionUID = 7031783417103049819L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("/views/web/contact/contact.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ContactModel contact = FormUtil.toModel(ContactModel.class, req);
		Long contactId = contactService.save(contact);
		System.out.println(contactId);
		resp.sendRedirect(req.getContextPath() + "/lien-he");
	}

}
