package com.ecommercewebsite.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.ecommercewebsite.dao.ICartDAO;
import com.ecommercewebsite.dto.CartDTO;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.service.IProductService;

public class CartDAO extends AbstractDAO<CartDTO> implements ICartDAO{

	@Inject
	private IProductService productService;
	
	@Override
	public HashMap<Long, CartDTO> addCart(Long id, HashMap<Long, CartDTO> cart) {
		CartDTO itemCart = new CartDTO();
		ProductModel product = productService.findOne(id);
		if (product != null) {
			if (cart.containsKey(id)) {
				itemCart = cart.get(id);
				itemCart.setQuanty(itemCart.getQuanty() + 1);
				itemCart.setTotalprice(itemCart.getQuanty() * itemCart.getProduct().getPricesale());
			} else {
				itemCart.setProduct(product);
				itemCart.setQuanty(1);
				itemCart.setTotalprice(product.getPricesale());
			}
			cart.put(id, itemCart);
		}
		return cart;
	}

	@Override
	public HashMap<Long, CartDTO> editCart(Long id, int quanty, HashMap<Long, CartDTO> cart) {
		if (cart == null) {
			return cart;
		}
		CartDTO itemCart = new CartDTO();
		if (cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuanty(quanty);
			double totalPrice = quanty * itemCart.getProduct().getPricesale();
			itemCart.setTotalprice(totalPrice);
		}
		cart.put(id, itemCart);
		return cart;
	}

	@Override
	public HashMap<Long, CartDTO> deleteCart(Long id, HashMap<Long, CartDTO> cart) {
		if (cart == null) {
			return cart;
		}
		if (cart.containsKey(id)) {
			cart.remove(id);
		}
		return cart;
	}

	@Override
	public int totalQuanty(HashMap<Long, CartDTO> cart) {
		int totalQuanty = 0;
		for (Map.Entry<Long, CartDTO> itemCart : cart.entrySet()) {
			totalQuanty += itemCart.getValue().getQuanty();
		}
		return totalQuanty;
	}

	@Override
	public double totalPrice(HashMap<Long, CartDTO> cart) {
		double totalPrice = 0;
		for (Map.Entry<Long, CartDTO> itemCart : cart.entrySet()) {
			totalPrice += itemCart.getValue().getTotalprice();
		}
		return totalPrice;
	}

}
