package com.ecommercewebsite.service;

import java.util.HashMap;
import java.util.List;

import com.ecommercewebsite.dto.CartDTO;
import com.ecommercewebsite.model.BillDetailModel;
import com.ecommercewebsite.model.BillModel;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.paging.Pageble;

public interface IBillService {
	public Long AddBills(BillModel bill, CouponModel coupon, HashMap<Long, CartDTO> cart, String uploadPath);

	public List<BillModel> findAll(Pageble pageble);
	
	public List<BillModel> findAll();

	public List<BillModel> GetDataBillByEmail(Pageble pageble, String email);

	public BillModel findBillsConfirm(String code);

	public BillModel findOne(Long id);

	public List<BillDetailModel> getBillsDetail(Long id);

	public List<BillModel> getCustomers(Pageble pageble);

	public int getTotalCustomers();

	public int getTotalItem();

	public void save(BillModel bill, double priceafterpromotion);

	public int countBillByEmail(String email);
	
	
	public void changeBillStatus(BillModel bill);
}
