package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.DiscountModel;
import com.ecommercewebsite.paging.Pageble;

public interface IDiscountService {
	public List<DiscountModel> findAll();

	public List<DiscountModel> findAll(Pageble pageble);

	public DiscountModel findOne(long id);

	public int getTotalItem();

	public DiscountModel save(DiscountModel discount);

	public DiscountModel update(DiscountModel discount);

	public DiscountModel checkAvailableCode(String code);

	public void subVailable(String code);

	DiscountModel findCouponById(Long id);
}
