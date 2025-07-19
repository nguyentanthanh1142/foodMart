package com.ecommercewebsite.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.constant.SystemConstant;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.IUserService;
import com.ecommercewebsite.utils.SessionUtil;

public class AuthorizationFilter implements Filter {

	@Inject
	private IUserService userService;

	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
	    UserModel userInSession = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
	    if (userInSession == null) {
	        autoLoginWhenRmb(req);
	    }
		String url = req.getRequestURI();
		if (url.startsWith(req.getContextPath() + "/admin")) {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
			if (user != null) {
				if (user.getRole().getCode().equals(SystemConstant.ADMIN))
					chain.doFilter(request, response);
				else if (user.getRole().getCode().equals(SystemConstant.USER)) {
					resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void autoLoginWhenRmb(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		String rememberMeToken = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("rememberMe".equals(cookie.getName())) {
					rememberMeToken = cookie.getValue();
					break;
				}
			}
		}
		if (rememberMeToken != null) {
			UserModel user = userService.getUserByRmbToken(rememberMeToken);
			if (user != null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", user);
			}
		} else {
			// 
		}
	}
}
