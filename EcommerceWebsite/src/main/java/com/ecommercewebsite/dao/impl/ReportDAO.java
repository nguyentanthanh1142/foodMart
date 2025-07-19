package com.ecommercewebsite.dao.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.ecommercewebsite.dao.IReportDAO;
import com.ecommercewebsite.model.ReportModel;

public class ReportDAO  extends AbstractDAO<ReportModel>implements IReportDAO{

	@Override
	public int getNumberAccounts() {
		String sql = "SELECT COUNT(id) from users";
		return count(sql);
	}

	@Override
	public int newOrders() {
		String sql = "SELECT COUNT(id) FROM bills WHERE createdAt >= NOW() - INTERVAL 7 DAY;";
		return count(sql);
	}

	@Override
	public Map<Long, Integer> totalItemByMonth(int month, int year)
	{
		 String sql = "SELECT product.catid, COALESCE(SUM(detail.quanty), 0) AS tongsanpham "
	               + "FROM product "
	               + "LEFT JOIN ( "
	               + "    SELECT billdetail.product_id, billdetail.quanty "
	               + "    FROM billdetail "
	               + "    JOIN bills ON billdetail.bills_id = bills.id "
	               + "    WHERE MONTH(bills.createdAt) = " + month +" AND YEAR(bills.createdAt) =  " + year
	               + ") AS detail ON product.id = detail.product_id "
	               + "GROUP BY product.catid";
		 System.out.println(sql);
	    return querySingleResult(sql, rs -> {
	        Map<Long, Integer> map = new HashMap<>();
	        while (rs.next()) {
	            map.put(rs.getLong("catid"), rs.getInt("tongsanpham"));
	            System.out.print("catid:" + rs.getInt("catid") + " Tong san pham " + rs.getInt("tongsanpham"));
	        }
	        return map;
	    });
	}
	public int covert2M(Date date) {
		DateFormat df = new SimpleDateFormat("M");
		return Integer.parseInt(df.format(date));
	}

	public int covert2Y(Date date) {
		DateFormat df = new SimpleDateFormat("yyy");
		return Integer.parseInt(df.format(date));
	}

	@Override
	public double getTotalInMonth() {
		String sql = "SELECT COALESCE(sum(total),0) FROM bills where status = 4 and MONTH(createdAt) = MONTH(CURRENT_DATE()) and year(createdAt)  = year(CURRENT_DATE())";
		Double total = querySingleResult(sql, rs -> {
		    if (rs.next()) {
		        double value = rs.getDouble(1);
		        return rs.wasNull() ? null : value;
		    }
		    return null;});
		return total;
	}

	@Override
	public double getTotalLastMonth() {
		String sql = "SELECT COALESCE(sum(total),0) FROM bills WHERE status = 4 and YEAR(createdAt) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH) AND MONTH(createdAt) = MONTH(createdAt - INTERVAL 1 MONTH)";
		Double total = querySingleResult(sql, rs -> {
		    if (rs.next()) {
		        double value = rs.getDouble(1);
		        return rs.wasNull() ? null : value;
		    }
		    return null;});
		return total;
	}

	@Override
	public int getNumberOfCancledBillsinMonth() {
		String sql = "SELECT count(id) FROM bills WHERE status = -1 and MONTH(createdAt) = MONTH(CURRENT_DATE()) and year(createdAt)  = year(CURRENT_DATE()) ";
		return count(sql);
	}
	
}
