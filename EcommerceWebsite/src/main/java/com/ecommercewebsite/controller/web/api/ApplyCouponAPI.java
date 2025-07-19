package com.ecommercewebsite.controller.web.api;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.dto.ApplyCouponResponse;
import com.ecommercewebsite.model.ApiResponse;
import com.ecommercewebsite.service.IApplyCouponService;
import com.ecommercewebsite.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-apply-coupon" })
public class ApplyCouponAPI extends HttpServlet{

	
	@Inject
	private IApplyCouponService applyCouponService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = HttpUtil.of(req.getReader())
        	    .toModel(new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {});

        String couponCode = (String) data.get("code");
        double  totalPrice = Double.parseDouble((String) data.get("totalPrice")); 
        
        System.out.println(totalPrice);
        try {
        	ApplyCouponResponse checked = applyCouponService.checkCoupon(totalPrice,couponCode);
        	if(checked != null)
        	{
        		
        		ApiResponse<ApplyCouponResponse> responseObj = new ApiResponse<>("Áp dụng mã khuyến mãi thành công", "success", checked);
                mapper.writeValue(resp.getOutputStream(), responseObj);
        	}
        	else {
        		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                ApiResponse<Object> errorResponse = new ApiResponse<>("Áp dụng mã thất bại", "error", null);
                mapper.writeValue(resp.getOutputStream(), errorResponse);
        	}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ApiResponse<Object> errorResponse = new ApiResponse<>("Áp dụng mã thất bại: " + e.getMessage(), "error", null);
            mapper.writeValue(resp.getOutputStream(), errorResponse);
		}
	}

	
	
}
