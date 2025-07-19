package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.ICouponDAO;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ICouponService;

public class CouponService implements ICouponService {

	@Inject
	private ICouponDAO couponDAO;

	@Override
	public List<CouponModel> findAll() {
		return null;
	}

	@Override
	public List<CouponModel> findAll(Pageble pageble) {
		return couponDAO.findAll(pageble);
	}

	@Override
	public CouponModel findOne(long id) {
		return couponDAO.findOne(id);
	}

	@Override
	public int getTotalItem() {
		return couponDAO.getTotalItem();
	}

	@Override
	public CouponModel save(CouponModel coupon) {
		coupon.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		coupon.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		Long couponId = couponDAO.save(coupon);
		couponDAO.createDiscoutedProductList(coupon.getListProduct(), couponId);
		return couponDAO.findOne(couponId);
	}

	@Override
	public CouponModel update(CouponModel coupon) {
		CouponModel old = couponDAO.findOne(coupon.getId());
		coupon.setCreatedAt(old.getCreatedAt());
		coupon.setCreatedBy(old.getCreatedBy());
		coupon.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		couponDAO.update(coupon);
		couponDAO.updateProductCoupon(coupon.getId(),old.getListProduct(),coupon.getListProduct());
		System.out.println("lsit cu:" + old.getListProduct());
		System.out.println("lsit moi:" + coupon.getListProduct());
		return couponDAO.findOne(coupon.getId());
	}

	@Override
	public CouponModel checkAvailableCode(String code) {
		return couponDAO.checkAvailableCode(code);
	}

	@Override
	public void subVailable(String code) {
		couponDAO.subVailable(code);

	}

	@Override
	public CouponModel findCouponById(Long id) {
		return couponDAO.findCouponById(id);
	}
	@Override
	public void delete(long[] ids) {
		couponDAO.delete(ids);
	}

	@Override
	public CouponModel checkCoupon(String code) {
		// TODO Auto-generated method stub
		return couponDAO.checkCoupon(code);
	}
}
