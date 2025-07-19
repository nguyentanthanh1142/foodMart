package com.ecommercewebsite.dao;

import java.util.Map;

public interface IReportDAO {
	public int getNumberAccounts();
	public int newOrders();
	public Map<Long, Integer> totalItemByMonth(int month, int year);
	public double getTotalInMonth();
	public double getTotalLastMonth();
	public int getNumberOfCancledBillsinMonth();
}
