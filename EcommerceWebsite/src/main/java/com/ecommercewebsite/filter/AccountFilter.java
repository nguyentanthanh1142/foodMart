package com.ecommercewebsite.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.utils.SessionUtil;

@WebFilter(urlPatterns = "/tai-khoan")
public class AccountFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
	    UserModel userInSession = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (userInSession != null  ) {
			chain.doFilter(request, response);
			return;
		} else {
			resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
