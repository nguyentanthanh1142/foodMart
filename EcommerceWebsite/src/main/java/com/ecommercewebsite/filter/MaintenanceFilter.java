package com.ecommercewebsite.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.constant.SystemConstant;
import com.ecommercewebsite.model.ConfigWebModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.IConfigWebService;
import com.ecommercewebsite.utils.SessionUtil;

public class MaintenanceFilter implements Filter{

	@Inject
	private IConfigWebService configService;
	private static final List<String> ALLOWED_PATHS = Arrays.asList("/bao-tri", "/quan-tri", "/dang-nhap", "/images",
			"/dang-xuat", "/template", "/oauth2callback");
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getRequestURI();
		ConfigWebModel configweb = configService.findConfigweb();
		boolean allowedPath = false;
		String ctxPath = req.getContextPath();
		path = path.replace(ctxPath, "");
		for (String e : ALLOWED_PATHS) {
			if (path.contains(e)) {
				allowedPath = true;
				break;
			}
		}

		if (configweb.getStatus() == 0 && !allowedPath) {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
			if (user != null && user.getRole().getCode().equals(SystemConstant.ADMIN)) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(req.getContextPath() + "/bao-tri");
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
