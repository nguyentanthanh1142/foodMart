package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.SlideModel;
import com.ecommercewebsite.paging.Pageble;

public interface ISlideDAO extends GenericDAO<SlideModel> {
	public List<SlideModel> findAll();

	public List<SlideModel> findAll(Pageble pageble);

	int getTotalItem();

	public SlideModel findOne(long id);

	public Long save(SlideModel newModel);

	void update(SlideModel newModel);

	void delete(long[] ids);
}
