package com.ecommercewebsite.dao;

import java.util.HashMap;

import com.ecommercewebsite.dto.CartDTO;

public interface ICartDAO extends GenericDAO<CartDTO>{
	public HashMap<Long, CartDTO> addCart(Long id, HashMap<Long, CartDTO> cart);
	public HashMap<Long, CartDTO> editCart(Long id, int quanty, HashMap<Long, CartDTO> cart);
	public HashMap<Long, CartDTO> deleteCart(Long id, HashMap<Long, CartDTO> cart);
	public int totalQuanty(HashMap<Long, CartDTO> cart) ;
	public double totalPrice(HashMap<Long, CartDTO> cart);
}
