package com.ecommercewebsite.dao.impl;

import java.util.List;

import com.ecommercewebsite.dao.IConfigWebDAO;
import com.ecommercewebsite.mapper.ConfigWebMapper;
import com.ecommercewebsite.model.ConfigWebModel;

public class ConfigWebDAO extends AbstractDAO<ConfigWebModel> implements IConfigWebDAO{

	@Override
	public ConfigWebModel findConfigweb() {
		String sql = "SELECT * FROM configweb";
		List<ConfigWebModel> result = query(sql,new ConfigWebMapper());
		return result.get(0);
	}

	@Override
	public void update(ConfigWebModel config) {
		StringBuilder sql = new StringBuilder("UPDATE configweb SET webname = ?, web_detail = ?, hotline = ?,");
		sql.append(" logo = ?, icon = ?, ");
		sql.append(" email = ?, address_store = ?, status = ? WHERE id = ?");
		update(sql.toString(), config.getWebname(), config.getWeb_detail(), config.getHotline(),
				config.getLogo(), config.getIcon(), config.getEmail(),
				config.getAddress_store(), config.getStatus(),  config.getId());
		
	}

	@Override
	public void changeStatus(int id) {
		String sql = "UPDATE configweb SET status = case when  status =0 then 1 when  status =1 then 0 end where  id =" +  id;
		update(sql);
		
	}

	@Override
	public ConfigWebModel findConfigweb(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigWebModel findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
