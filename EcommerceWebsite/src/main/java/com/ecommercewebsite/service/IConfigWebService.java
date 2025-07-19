package com.ecommercewebsite.service;

import com.ecommercewebsite.model.ConfigWebModel;

public interface IConfigWebService {
	public ConfigWebModel findConfigweb();

	public ConfigWebModel update(ConfigWebModel config);

	public void changeStatus(int id);
}
