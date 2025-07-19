package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;

public interface ICouponDAO extends GenericDAO<CouponModel>{
	public List<CouponModel> findAll();
	public List<CouponModel> findAll(Pageble pageble);
	public CouponModel findOne(long id);
	public int getTotalItem();
	public Long save(CouponModel contact);
	public void update(CouponModel updateCoupon);
	public CouponModel checkAvailableCode(String code);
	public void subVailable(String code);
	public CouponModel findCouponById(Long id);
	public void createDiscoutedProductList(List<Long> list, Long couponId);
	public void updateProductCoupon(Long couponId, List<Long> oldL, List<Long> newL);
	public void delete(long[] ids);
	public CouponModel checkCoupon(String code);
}
