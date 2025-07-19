package com.ecommercewebsite.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.IReportDAO;
import com.ecommercewebsite.model.ReportModel;
import com.ecommercewebsite.service.IReportService;

public class ReportService implements IReportService {

	@Inject
	private IReportDAO reportDAO;

	@Override
	public int getNumberAccounts() {
		return reportDAO.getNumberAccounts();
	}

	@Override
	public int newOrders() {
		return reportDAO.newOrders();
	}

	@Override
	public List<ReportModel> reportReceiptDay(Date date, int limit) {
		return null;
//		return billsDAO.reportReceiptDay(date, limit);
	}

	@Override
	public List<ReportModel> reportReceiptMonth(Date date, int limit) {
		List<ReportModel> list = new ArrayList<>();
		for (int i = limit - 1; i >= 0; i--) {
			Date d = subMonths(date, i);
			System.out.println("Ngay gio hien tai: " + d.toString());
			int month = covert2M(d);
			int year = covert2Y(d);
			System.out.println("Thang:  " + month);
			System.out.println("Nam:  " + year);
			ReportModel rp = new ReportModel();
			rp.setTime(covertD2MYS(d));
			rp.setMapValue(reportDAO.totalItemByMonth(month, year));
			list.add(rp);
		}
		return list;
	}

	@Override
	public double getTotalInMonth() {
		return reportDAO.getTotalInMonth();
	}

	@Override
	public double getTotalLastMonth() {
		return reportDAO.getTotalLastMonth();
	}

	@Override
	public int getNumberOfCancledBillsinMonth() {
		return reportDAO.getNumberOfCancledBillsinMonth();
	}

	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date subMonths(Date date, int month) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -month);
		return cal.getTime();
	}

	public String covertD2MYS(Date date) {
		DateFormat df = new SimpleDateFormat("MM/yyy");
		return df.format(date);
	}

	public int covert2M(Date date) {
		DateFormat df = new SimpleDateFormat("M");
		return Integer.parseInt(df.format(date));
	}

	public int covert2Y(Date date) {
		DateFormat df = new SimpleDateFormat("yyy");
		return Integer.parseInt(df.format(date));
	}

}
