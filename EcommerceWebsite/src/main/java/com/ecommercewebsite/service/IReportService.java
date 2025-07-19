package com.ecommercewebsite.service;

import java.util.Date;
import java.util.List;

import com.ecommercewebsite.model.ReportModel;

public interface IReportService {
	public int getNumberAccounts();
	public int newOrders();
	public List<ReportModel> reportReceiptDay(Date date, int limit);
	public List<ReportModel> reportReceiptMonth(Date date, int limit);
	public double getTotalInMonth();
	public double getTotalLastMonth();
	public int getNumberOfCancledBillsinMonth();
}
