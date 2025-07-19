package com.ecommercewebsite.dto;

import com.ecommercewebsite.model.CouponModel;

public class ApplyCouponResponse {

	private double promotion;
	private double priceAfterPromotion;
	private CouponModel coupon;
	public double getPromotion() {
		return promotion;
	}
	public void setPromotion(double promotion) {
		this.promotion = promotion;
	}
	public double getPriceAfterPromotion() {
		return priceAfterPromotion;
	}
	public void setPriceAfterPromotion(double priceAfterPromotion) {
		this.priceAfterPromotion = priceAfterPromotion;
	}
	public CouponModel getCoupon() {
		return coupon;
	}
	public void setCoupon(CouponModel coupon) {
		this.coupon = coupon;
	}
	
}
