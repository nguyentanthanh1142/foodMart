package com.ecommercewebsite.dao;

import com.ecommercewebsite.model.ConfigWebModel;

public interface IConfigWebDAO extends GenericDAO<ConfigWebModel>{

	public ConfigWebModel findConfigweb();

	public void update(ConfigWebModel config);

	public void changeStatus(int id);
	
	public ConfigWebModel findConfigweb(Long id);

	public ConfigWebModel findOne(Long id);
}
