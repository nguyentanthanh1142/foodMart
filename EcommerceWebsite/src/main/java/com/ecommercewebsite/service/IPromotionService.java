package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.PromotionModel;
import com.ecommercewebsite.paging.Pageble;

public interface IPromotionService {
	public List<PromotionModel> findAll();

	public List<PromotionModel> findAll(Pageble pageble);

	public PromotionModel findOne(long id);

	public int getTotalItem();

	public PromotionModel save(PromotionModel coupon);

	public PromotionModel update(PromotionModel coupon);

	public void subVailable(String code);

	public PromotionModel findPromotionById(Long id);

	public void delete(long[] ids);

	public PromotionModel checkCoupon(int id);
	
	public PromotionModel getSpecialPromotion();
	public PromotionModel getSpecialPromotion(int limit);
}
