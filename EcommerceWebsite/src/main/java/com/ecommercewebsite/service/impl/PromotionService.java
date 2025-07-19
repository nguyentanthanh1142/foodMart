package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.IProductDAO;
import com.ecommercewebsite.dao.IPromotionDAO;
import com.ecommercewebsite.model.PromotionModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IPromotionService;

public class PromotionService implements IPromotionService{

	@Inject
	private IPromotionDAO promotionDAO;
	@Inject
	private IProductDAO productDAO;
	
	@Override
	public List<PromotionModel> findAll() {
		// TODO Auto-generated method stub
		return promotionDAO.findAll();
	}

	@Override
	public List<PromotionModel> findAll(Pageble pageble) {
		// TODO Auto-generated method stub
		return promotionDAO.findAll(pageble);
	}

	@Override
	public PromotionModel findOne(long id) {
		// TODO Auto-generated method stub
		return promotionDAO.findOne(id);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PromotionModel save(PromotionModel coupon) {
		coupon.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		coupon.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		Long couponId = promotionDAO.save(coupon);
		promotionDAO.createPromoteProductList(coupon.getListProduct(), couponId);
		return promotionDAO.findOne(couponId);
	}

	@Override
	public PromotionModel update(PromotionModel coupon) {
		PromotionModel old = promotionDAO.findOne(coupon.getId());
		coupon.setCreatedAt(old.getCreatedAt());
		coupon.setCreatedBy(old.getCreatedBy());
		coupon.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		promotionDAO.update(coupon);
		promotionDAO.updateProductCoupon(coupon.getId(),old.getListProduct(),coupon.getListProduct());
		System.out.println("lsit cu:" + old.getListProduct());
		System.out.println("lsit moi:" + coupon.getListProduct());
		return promotionDAO.findOne(coupon.getId());
	}

	@Override
	public void subVailable(String code) {
		// TODO Auto-generated method stub
		promotionDAO.subVailable(code);
	}

	@Override
	public PromotionModel findPromotionById(Long id) {
		// TODO Auto-generated method stub
		return promotionDAO.findPromotionById(id);
	}

	@Override
	public void delete(long[] ids) {
		promotionDAO.delete(ids);
		
	}

	@Override
	public PromotionModel checkCoupon(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionModel getSpecialPromotion() {
		PromotionModel promotion = promotionDAO.getSpecialPromotion();
		promotion.setListProductPromotion(productDAO.getSpecialPromotion(promotion.getId()));
		return promotion;
	}
	@Override
	public PromotionModel getSpecialPromotion(int limit) {
		PromotionModel promotion = promotionDAO.getSpecialPromotion();
		promotion.setListProductPromotion(productDAO.getSpecialPromotion(promotion.getId(),limit));
		return promotion;
	}
}
