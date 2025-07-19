package com.ecommercewebsite.service.impl;

import javax.inject.Inject;

import com.ecommercewebsite.dao.ICouponDAO;
import com.ecommercewebsite.dto.ApplyCouponResponse;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.service.IApplyCouponService;

public class ApplyCouponService implements IApplyCouponService{

	@Inject
	private ICouponDAO couponDAO;
	
	@Override
	public ApplyCouponResponse checkCoupon(double totalPrice, String code) {
		// TODO Auto-generated method stub
		ApplyCouponResponse response = new ApplyCouponResponse();
		CouponModel coupon = couponDAO.checkCoupon(code);
		if(coupon != null) {
			response.setCoupon(coupon);
			response.setPromotion(coupon.isByPercent()?Math.min(coupon.getLimitedDiscount(), (totalPrice*coupon.getPricesale()/100)):coupon.getPricesale());
			response.setPriceAfterPromotion(totalPrice - response.getPromotion());
			return response;
		}
		return null;
	}

}
