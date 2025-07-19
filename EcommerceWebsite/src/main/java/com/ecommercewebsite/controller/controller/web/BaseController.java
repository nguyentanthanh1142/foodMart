package com.ecommercewebsite.controller.controller.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.model.ConfigWebModel;
import com.ecommercewebsite.service.ICategoryService;
import com.ecommercewebsite.service.IConfigWebService;
import com.ecommercewebsite.service.IMenuService;
import com.ecommercewebsite.service.ITopicService;

public abstract class BaseController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1260390882429778542L;
	
	@Inject
	private IConfigWebService configService;
	@Inject
	private IMenuService menuService;
	@Inject
	private ICategoryService categoryService;
	@Inject
	private ITopicService topicService;
//	
    @Override
    public void init() throws ServletException {
        super.init();
        ConfigWebModel configweb= configService.findConfigweb();
        getServletContext().setAttribute("configweb", configweb);
        List<CategoryModel> list = categoryService.getListChild();
        getServletContext().setAttribute("listCategoryShow", list);
        getServletContext().setAttribute("menu", menuService.GetMenuShow());
        getServletContext().setAttribute("footerTopic", topicService.findTopicShowFooter());
    }
	
}
