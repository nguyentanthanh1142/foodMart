package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.SlideModel;
import com.ecommercewebsite.paging.Pageble;

public interface ISlideService {
	public List<SlideModel> findAllShow();

	public List<SlideModel> findAll(Pageble pageble);

	public int getTotalItem();

	public SlideModel findOne(long id);

	public void delete(long[] ids);

	public SlideModel save(SlideModel category);

	public SlideModel update(SlideModel product);

}
