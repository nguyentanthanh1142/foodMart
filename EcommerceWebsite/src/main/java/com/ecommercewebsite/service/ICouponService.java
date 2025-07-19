package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.paging.Pageble;

public interface ICouponService {
	public List<CouponModel> findAll();
	public List<CouponModel> findAll(Pageble pageble);
	public CouponModel findOne(long id);
	public int getTotalItem();
	public CouponModel save(CouponModel coupon);
	public CouponModel update(CouponModel coupon);
	public CouponModel checkAvailableCode(String code);
	public void subVailable(String code);
	CouponModel findCouponById(Long id);
	public void delete(long[] ids);
	public CouponModel checkCoupon(String code);
}
