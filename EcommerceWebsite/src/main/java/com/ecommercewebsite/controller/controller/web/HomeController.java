package com.ecommercewebsite.controller.controller.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.constant.SystemConstant;
import com.ecommercewebsite.model.CategoryModel;
import com.ecommercewebsite.model.NewModel;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.ICategoryService;
import com.ecommercewebsite.service.INewService;
import com.ecommercewebsite.service.IProductService;
import com.ecommercewebsite.service.IPromotionService;
import com.ecommercewebsite.service.IUserService;
import com.ecommercewebsite.utils.EmailUtil;
import com.ecommercewebsite.utils.FormUtil;
import com.ecommercewebsite.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap", "/thoat" })
public class HomeController extends BaseController {

	/**
	 * 
	 */
	@Inject
	private IProductService productService;
	@Inject
	private INewService newSerivce;
	@Inject
	private IUserService userService;
	@Inject
	private ICategoryService categoryService;
	@Inject
	private IPromotionService promotionService;	
	
	

	private static final long serialVersionUID = 5890959096920430384L;

	ResourceBundle resource = ResourceBundle.getBundle("messages");
	ResourceBundle oauth2 = ResourceBundle.getBundle("oauth2");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			String alert = req.getParameter("alert");
			String message = req.getParameter("message");
			if (alert != null && message != null) {
				req.setAttribute("alert", alert);
				req.setAttribute("message", resource.getString(message));
			}
	        String oauthURL = "https://accounts.google.com/o/oauth2/auth?" +
	                "client_id=" + oauth2.getString("client_id") +
	                "&redirect_uri=" + oauth2.getString("REDIRECT_URI") +
	                "&response_type=code" +
	                "&scope=" + URLEncoder.encode(oauth2.getString("SCOPE"), "UTF-8");
	        req.setAttribute("oauthURL", oauthURL);
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			rd.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			Cookie cookie = new Cookie("rememberMe", null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			resp.addCookie(cookie);
			resp.sendRedirect(req.getContextPath() + "/trang-chu");
		} else if (action != null && action.equals("register")) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/register.jsp");
			rd.forward(req, resp);
		}
		// auto login when remember
		else if (action != null && action.equals("resetpassword")) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/resetpassword.jsp");
			rd.forward(req, resp);
		} else {

			List<ProductModel> products = productService.findAll();
			List<NewModel> recentBlogs = newSerivce.getRecentBlog(3);
			List<CategoryModel> categories = categoryService.getHotCategoryShow();
			req.setAttribute("categoriesSlide", categories);
			req.setAttribute("products", products);
			req.setAttribute("recentBlogs", recentBlogs);
			req.setAttribute("specialPromotion", promotionService.getSpecialPromotion(4));
			req.setAttribute("newproducts", productService.getListNewProducts());
			req.setAttribute("listCateProduct", categoryService.categoriesHomeShow());
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("action" + action);
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, req);
			model = userService.findUserByLogin(model.getUserName(), model.getPassword(), 1);
			if (model != null) {
				boolean rememberMe = req.getParameter("remember") != null;
				if (rememberMe) {
					System.out.println("Da zo duoc day remember");
					String token = UUID.randomUUID().toString();
					userService.saveRememberToken(model.getId(), token);

					Cookie cookie = new Cookie("rememberMe", token);
					cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
					cookie.setPath("/");
//					cookie.setHttpOnly(true);
					// cookie.setSecure(true); // nếu HTTPS thì mở cái này
					resp.addHeader("Set-Cookie", "rememberMe=" + token + "; Max-Age=2592000; Path=/; HttpOnly");
//					resp.addCookie(cookie);
					
				}

				SessionUtil.getInstance().putValue(req, "USERMODEL", model);
				if (model.getRole().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath() + "/trang-chu");
				} else if (model.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/admin");
				}
			}
			else {
				   resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		} else if (action != null && action.equals("resetpassword")) {

			ResourceBundle mailrs = ResourceBundle.getBundle("mail");

			String host = mailrs.getString("host");
			String port = mailrs.getString("port");

//				
//				String email = context.getInitParameter("email");
//				String name = context.getInitParameter("name");
//				String pass = context.getInitParameter("pass");

			String recipient = req.getParameter("email");
			String subject = "Your Password has been reset";

			String newPassword = userService.resetPassword(recipient);
			;
			System.out.println(newPassword);
			String content = "Hi, this is your new password: " + newPassword;
			content += "\nNote: for security reason, " + "you must change your password after logging in.";

			String message = "";

			try {
				EmailUtil.sendEmail(host, port, SystemConstant.MY_EMAIL, "TAn Thanh", SystemConstant.MY_PASSWORD,
						recipient, subject, content);
				message = "Your password has been reset. Please check your e-mail.";
			} catch (Exception ex) {
				ex.printStackTrace();
				message = "There were an error: " + ex.getMessage();
			} finally {
				req.setAttribute("message", message);
				req.getRequestDispatcher("message.jsp").forward(req, resp);
			}
		} else if (action != null && action.equals("register")) {
			UserModel model = FormUtil.toModel(UserModel.class, req);
			userService.addAcount(model);
			resp.sendRedirect(
					req.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");

		} else {
			resp.sendRedirect(
					req.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
		}
	}
}
