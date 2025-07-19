package com.ecommercewebsite.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.ecommercewebsite.constant.SystemConstant;
import com.ecommercewebsite.dao.IBillDAO;
import com.ecommercewebsite.dao.IProductDAO;
import com.ecommercewebsite.dto.CartDTO;
import com.ecommercewebsite.model.BillDetailModel;
import com.ecommercewebsite.model.BillModel;
import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.IBillService;
import com.ecommercewebsite.utils.EmailUtil;

public class BillService implements IBillService {

	@Inject
	private IBillDAO billDAO;
	@Inject
	private IProductDAO productDAO;
	

	public Long AddBills(BillModel bill, CouponModel coupon, HashMap<Long, CartDTO> cart, String uploadPath ) {
		bill.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		bill.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		double promotion =0;
		
		if(coupon !=null && bill.getTotal() >= coupon.getMinValue() )
		{
			if(coupon.isByPercent())
			{	
				promotion = Math.min(coupon.getLimitedDiscount(), (bill.getTotal() * coupon.getPricesale() / 100));
			}else {
				promotion = coupon.getPricesale();
			}
		}
		
		double priceafterpromotion = bill.getTotal() - promotion;
		
		Long billId = billDAO.AddBills(bill, priceafterpromotion);
		for (Map.Entry<Long, CartDTO> itemCart : cart.entrySet()) {
			BillDetailModel billdetail = new BillDetailModel();
			billdetail.setBills_id(billId);
			billdetail.setProduct_id(itemCart.getValue().getProduct().getId());
			billdetail.setQuanty(itemCart.getValue().getQuanty());
			billdetail.setTotal(itemCart.getValue().getTotalprice());
			billDAO.AddBillsDetail(billdetail);
		}
		
		try {
			ResourceBundle mailrs = ResourceBundle.getBundle("mail");

			String host = mailrs.getString("host");
			String port = mailrs.getString("port");
			
			EmailUtil.sendCheckoutEmail(host, port, SystemConstant.MY_EMAIL, "Tan Thanh", SystemConstant.MY_PASSWORD, bill.getEmail(), "", "",cart,bill.getCode(), uploadPath);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return billId;
	}

	@Override
	public BillModel findBillsConfirm(String code) {
		return billDAO.findBillsConfirm(code);
	}

	@Override
	public List<BillDetailModel> getBillsDetail(Long id) {
		List<BillDetailModel> list = billDAO.getBillsDetail(id);
		for (BillDetailModel it : list) {
			ProductModel product = productDAO.findOne(it.getProduct_id());
			it.setProductinfo(product);
		}
		return list;
	}

	@Override
	public void save(BillModel bill, double priceafterpromotion) {

	}

	@Override
	public BillModel findOne(Long id) {
		return billDAO.findOne(id);
	}

	@Override
	public List<BillModel> findAll(Pageble pageble) {
		return billDAO.findAll(pageble);
	}
	@Override
	public List<BillModel> findAll() {
		return billDAO.findAll();
	}

	@Override
	public int getTotalItem() {
		return billDAO.getTotalItem();
	}

	@Override
	public List<BillModel> getCustomers(Pageble pageble) {
		return billDAO.getCustomers(pageble);
	}

	@Override
	public int getTotalCustomers() {
		return billDAO.getTotalCustomers();
	}

	@Override
	public List<BillModel> GetDataBillByEmail(Pageble pageble, String email) {
		// TODO Auto-generated method stub
		return billDAO.GetDataBillByEmail(pageble,email);
	}

	@Override
	public int countBillByEmail(String email) {
		// TODO Auto-generated method stub
		return billDAO.countBillByEmail(email);
	}

	@Override
	public void changeBillStatus(BillModel bill) {
		bill.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		if(bill.getStatus() == -1) {
			billDAO.cancelBill(bill);
		}
		else {
			billDAO.changeBillStatus(bill);
		}
		 
	}

}
