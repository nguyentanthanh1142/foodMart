package com.ecommercewebsite.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.ReportModel;
import com.ecommercewebsite.service.ICategoryService;
import com.ecommercewebsite.service.INewService;
import com.ecommercewebsite.service.IReportService;

@WebServlet(urlPatterns = {"/admin"})
public class HomeController extends HttpServlet{

	@Inject
	private INewService newService;
	@Inject
	private ICategoryService categoryService;
	@Inject
	private IReportService reportService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1678037663092850443L;

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			NewModel newModel = new NewModel();
//			newModel.setCategoryId((long) 1);
//			newModel.setTitle("Day la title");
//			newModel.setContent("Day la content");
//			newModel.setShortDescription("Day la shortdescription");
//			newModel.setThumbnail("Day la thumbnail");
//			newModel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//			newModel.setCreatedBy("");
//			newModel.setModifiedAt(new Timestamp(System.currentTimeMillis()));
//			newModel.setModifiedBy("");
//			long id = newService.save(newModel);
//			System.out.print(id);
			Map<Long, String> mapCate = categoryService.getMapCat();
			List<ReportModel>  listRpCat = reportService.reportReceiptMonth(new Date(), 6);
			for(ReportModel i:listRpCat) {
				System.out.println("Date: " + i.getTime() +", Value: " + i.getValue());
			    for (Map.Entry<Long, Integer> entry : i.getMapValue().entrySet()) {
			    	Long key = entry.getKey();
			    	String categoryName = mapCate.get(key);
			        System.out.println(entry.getKey() + "/" + entry.getValue() + (i.getMapValue().get(key) != null ? " Có" : " Không") + " - Tên: " + categoryName);
			    }
			}
			req.setAttribute("rpCate",listRpCat);
			req.setAttribute("rpAccount",reportService.getNumberAccounts());
			req.setAttribute("rpTotalInMonth",reportService.getTotalInMonth());
			req.setAttribute("rpTotalLastMonth",reportService.getTotalLastMonth());
			req.setAttribute("rpCancelBill",reportService.getNumberOfCancledBillsinMonth());
			req.setAttribute("mapCate", mapCate);
			req.setAttribute("reportMonth", listRpCat);
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
			rd.forward(req, resp);
	}

}
