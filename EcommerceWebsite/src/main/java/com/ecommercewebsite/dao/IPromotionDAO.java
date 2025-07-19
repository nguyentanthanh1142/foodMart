package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.PromotionModel;
import com.ecommercewebsite.paging.Pageble;

public interface IPromotionDAO extends GenericDAO<PromotionModel>{
	public List<PromotionModel> findAll();
	public List<PromotionModel> findAll(Pageble pageble);
	public PromotionModel findOne(long id);
	public PromotionModel getSpecialPromotion();
	public int getTotalItem();
	public Long save(PromotionModel promotion);
	public void update(PromotionModel updatePromotion);
	public void subVailable(String code);
	public PromotionModel findPromotionById(Long id);
	public void createPromoteProductList(List<Long> list, Long promotionId);
	public void updateProductCoupon(Long promotionId, List<Long> oldL, List<Long> newL);
	public void delete(long[] ids);
//	public PromotionModel checkCoupon(String code);
}
