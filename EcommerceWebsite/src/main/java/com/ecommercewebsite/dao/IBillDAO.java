package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.BillDetailModel;
import com.ecommercewebsite.model.BillModel;
import com.ecommercewebsite.paging.Pageble;

public interface IBillDAO extends GenericDAO<BillModel> {
	public Long AddBills(BillModel bill, double priceafterpromotion);

	public Long AddBillsDetail(BillDetailModel billdetail);

	public BillModel findBillsConfirm(String code);

	public List<BillDetailModel> getBillsDetail(Long id);

	public BillModel findOne(Long id);

	public List<BillModel> findAll(Pageble pageble);
	public List<BillModel> findAll();
	public int getTotalItem();

	public List<BillModel> getCustomers(Pageble pageble);

	public List<BillModel> GetDataBillByEmail(Pageble pageble, String email);

	public int getTotalCustomers();

	public int countBillByEmail(String email);
	
	public void changeBillStatus(BillModel model);
	public void cancelBill(BillModel model);
}
