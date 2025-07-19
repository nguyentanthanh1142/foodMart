package com.ecommercewebsite.controller.web.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.dto.CartDTO;
import com.ecommercewebsite.service.ICartService;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/cart" })
public class CartAPI extends HttpServlet {

	@Inject
	private ICartService cartService;
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 8547686034405304600L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("application/json");
	    req.setCharacterEncoding("UTF-8");

	    String action = req.getParameter("action");
	    Long productId = req.getParameter("productId") != null
	            ? Long.parseLong(req.getParameter("productId"))
	            : null;

	    Map<String, Object> result = new HashMap<>();
	    @SuppressWarnings("unchecked")
	    HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) SessionUtil.getInstance().getValue(req, "Cart");

	    if (cart == null) {
	        cart = new HashMap<>();
	    }

	    switch (action) {
	        case "add":
	            if (productId != null) {
	                cart = cartService.addCart(productId, cart);
	                SessionUtil.getInstance().putValue(req, "Cart", cart);
	                SessionUtil.getInstance().putValue(req, "TotalQuantyCart", cartService.totalQuanty(cart));
	                SessionUtil.getInstance().putValue(req, "TotalPriceCart", cartService.totalPrice(cart));
	                
	                CartDTO addedProduct = cart.get(productId);
	                result.put("status", "success");
	                result.put("totalQuanty", cartService.totalQuanty(cart));

	                // Trả về thông tin sản phẩm để hiển thị trong popup
	                Map<String, Object> product = new HashMap<>();
	                product.put("name", addedProduct.getProduct().getName());
	                product.put("price", (addedProduct.getProduct().getPricesale()));
	                product.put("image", addedProduct.getProduct().getProductimg());
	                result.put("product", product);
	            } else {
	                result.put("status", "fail");
	                result.put("message", "Missing productId");
	            }
	            break;

	        case "edit":
	            if (productId != null) {
	                int quanty = Integer.parseInt(req.getParameter("quanty"));
	                cart = cartService.editCart(productId, quanty, cart);
	                SessionUtil.getInstance().putValue(req, "Cart", cart);
	                SessionUtil.getInstance().putValue(req, "TotalQuantyCart", cartService.totalQuanty(cart));
	                SessionUtil.getInstance().putValue(req, "TotalPriceCart", cartService.totalPrice(cart));
	                result.put("status", "success");
	                result.put("totalQuanty", cartService.totalQuanty(cart));
	            } else {
	                result.put("status", "fail");
	                result.put("message", "Missing productId");
	            }
	            break;

	        case "remove":
	        	 if (productId != null) {
	        	        cart = cartService.deleteCart(productId,cart);
	        	        SessionUtil.getInstance().putValue(req, "Cart", cart);
	        	        SessionUtil.getInstance().putValue(req, "TotalQuantyCart", cartService.totalQuanty(cart));
	        	        SessionUtil.getInstance().putValue(req, "TotalPriceCart", cartService.totalPrice(cart));
	        	        result.put("status", "success");
	        	        result.put("totalQuanty", cartService.totalQuanty(cart));
	        	    } else {
	        	        result.put("status", "fail");
	        	        result.put("message", "Missing productId");
	        	    }
	            break;

	        case "clear":
	            cart.clear();
	            SessionUtil.getInstance().putValue(req, "Cart", cart);
	            SessionUtil.getInstance().putValue(req, "TotalQuantyCart", 0);
	            SessionUtil.getInstance().putValue(req, "TotalPriceCart", 0);
	            result.put("status", "success");
	            result.put("message", "Giỏ hàng đã được xóa.");
	            break;

	        default:
	            result.put("status", "fail");
	            result.put("message", "Unknown action");
	    }

	    new ObjectMapper().writeValue(resp.getOutputStream(), result);
	}
}