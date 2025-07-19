package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.ISlideDAO;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.model.SlideModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.ISlideService;

public class SlideService implements ISlideService {
	@Inject
	private ISlideDAO slideDao;

	@Override
	public List<SlideModel> findAllShow() {
		return slideDao.findAll();
	}

	@Override
	public List<SlideModel> findAll(Pageble pageble) {
		// TODO Auto-generated method stub
		return slideDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return slideDao.getTotalItem();
	}

	@Override
	public SlideModel findOne(long id) {
		// TODO Auto-generated method stub
		return slideDao.findOne(id);
	}

	@Override
	public void delete(long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SlideModel save(SlideModel model) {
		model.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		model.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		Long slideId = slideDao.save(model);
		return slideDao.findOne(slideId);
	}

	@Override
	public SlideModel update(SlideModel product) {
		SlideModel old = slideDao.findOne(product.getId());
		product.setCreatedAt(old.getCreatedAt());
		product.setCreatedBy(old.getCreatedBy());
		product.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		if (product.getImg() == null) {
			product.setImg(old.getImg());
		}
		slideDao.update(product);
		return slideDao.findOne(product.getId());
	}

}
