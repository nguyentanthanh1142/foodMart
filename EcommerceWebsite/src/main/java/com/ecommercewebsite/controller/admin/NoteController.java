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
import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.paging.PageRequest;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.INoteService;
import com.ecommercewebsite.sort.Sorter;
import com.ecommercewebsite.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin/note" })
public class NoteController extends HttpServlet {

	@Inject
	private INoteService noteService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -9004126549441308035L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoteModel model = FormUtil.toModel(NoteModel.class, req);
		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
				new Sorter(model.getSortName(), model.getSortBy()));
		String action = req.getParameter("action");
		if (action != null && action.equals("search")) {
			String to = req.getParameter("to");
			String from = req.getParameter("from");

			model.setListResult(noteService.findNoteBetween(to, from, pageble));
			model.setTotalItem(noteService.getTotalItemBW(to, from));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			req.setAttribute("to", to);
			req.setAttribute("from", from);
		} else if (action == null) {

			String type = model.getType() != null ? model.getType() : SystemConstant.LIST;
			if (type.equals(SystemConstant.LIST)) {

				model.setListResult(noteService.findAll(pageble));
				model.setTotalItem(noteService.getTotalItem());
				model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));

			}
		}
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/note/list.jsp");
		rd.forward(req, resp);
	}
}
