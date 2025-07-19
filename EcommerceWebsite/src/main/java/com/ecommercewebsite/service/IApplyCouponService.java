package com.ecommercewebsite.service;

import com.ecommercewebsite.dto.ApplyCouponResponse;

public interface IApplyCouponService {
	public ApplyCouponResponse checkCoupon(double totalPrice,String code);

}
