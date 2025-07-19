package com.ecommercewebsite.service.impl;

import java.util.HashMap;

import javax.inject.Inject;

import com.ecommercewebsite.dao.ICartDAO;
import com.ecommercewebsite.dto.CartDTO;
import com.ecommercewebsite.service.ICartService;

public class CartService implements ICartService {

	@Inject
	private ICartDAO cartDAO;

	@Override
	public HashMap<Long, CartDTO> addCart(Long id, HashMap<Long, CartDTO> cart) {
		return cartDAO.addCart(id, cart);
	}

	@Override
	public HashMap<Long, CartDTO> editCart(Long id, int quanty, HashMap<Long, CartDTO> cart) {
		return cartDAO.editCart(id, quanty, cart);
	}

	@Override
	public HashMap<Long, CartDTO> deleteCart(Long id, HashMap<Long, CartDTO> cart) {
		return cartDAO.deleteCart(id, cart);
	}

	@Override
	public int totalQuanty(HashMap<Long, CartDTO> cart) {
		return cartDAO.totalQuanty(cart);
	}

	@Override
	public double totalPrice(HashMap<Long, CartDTO> cart) {
		return cartDAO.totalPrice(cart);
	}

}
