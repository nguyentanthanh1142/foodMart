package com.ecommercewebsite.service.impl;

import javax.inject.Inject;

import com.ecommercewebsite.dao.IConfigWebDAO;
import com.ecommercewebsite.model.ConfigWebModel;
import com.ecommercewebsite.service.IConfigWebService;

public class ConfigWebService implements IConfigWebService {

	@Inject
	private IConfigWebDAO iconfigDAO;

	@Override
	public ConfigWebModel findConfigweb() {
		return iconfigDAO.findConfigweb();
	}

	@Override
	public ConfigWebModel update(ConfigWebModel config) {
		ConfigWebModel old = iconfigDAO.findConfigweb();
		if (config.getLogo() == null) {
			config.setLogo(old.getLogo());
		}
		if (config.getIcon() == null) {
			config.setIcon(old.getIcon());
		}
		iconfigDAO.update(config);
		return iconfigDAO.findConfigweb();
	}

	@Override
	public void changeStatus(int id) {
		iconfigDAO.changeStatus(id);

	}

}
