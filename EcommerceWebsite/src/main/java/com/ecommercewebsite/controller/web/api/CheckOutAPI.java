package com.ecommercewebsite.controller.web.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.dto.CartDTO;
import com.ecommercewebsite.model.BillModel;

import com.ecommercewebsite.model.CouponModel;
import com.ecommercewebsite.service.IBillService;
import com.ecommercewebsite.service.ICouponService;

import com.ecommercewebsite.utils.HttpUtil;
import com.ecommercewebsite.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-checkout" })
public class CheckOutAPI extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1225858615858384533L;
	@Inject
	private ICouponService CouponService;
	@Inject
	private IBillService billService;
	private static final String UPLOAD_DIRECTORY = "/uploads";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		BillModel bills = HttpUtil.of(req.getReader()).toModel(BillModel.class);
		bills.setQuanty((int) SessionUtil.getInstance().getValue(req, "TotalQuantyCart"));
		bills.setTotal((double) SessionUtil.getInstance().getValue(req, "TotalPriceCart") + 40000);
		bills.setStatus(0);
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		bills.setCode(uuid);
		
		CouponModel coupon = CouponService.checkAvailableCode(bills.getReductionCode());
		if(coupon !=null)
		{
			bills.setCoupon(1);
			bills.setCoupon_id(coupon.getId());
		}
		else {
			bills.setCoupon(0);
			bills.setCoupon_id(null);
		}
		
		
		@SuppressWarnings("unchecked")
		 HashMap<Long, CartDTO> carts = (HashMap<Long, CartDTO>) SessionUtil.getInstance().getValue(req, "Cart");
		String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
		Long id = billService.AddBills(bills, coupon,carts,uploadPath);
		System.out.println(id);
//		if (billService.AddBills(bills, priceafterpromotion) > 0) {
//			if (copon != null) {
//				CouponService.subVailable(code);
//			}
//			

//			try {
//		        MimeMessage message = new MimeMessage(session);
//	            message.setFrom(new InternetAddress(username));
//	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//	            message.setSubject("Cảm ơn bạn đã đặt hàng!");
//				
//				
//				// Create a Simple MailMessage.
//				MimeMessage message = emailSender.createMimeMessage();
//				boolean multipart = true;
//				MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
//				String content = null;
//				URLConnection connection = null;
//				try {
//					connection = new URL("http://localhost:8080/spring-web/gio-hang/sendmail/" + uuid2)
//							.openConnection();
//					connection.setDoOutput(true); // Triggers POST.
//					connection.setRequestProperty("Accept-Charset", "UTF-8");
//					connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//					Scanner scanner = new Scanner(connection.getInputStream());
//					scanner.useDelimiter("\\Z");
//					content = scanner.next();
//					scanner.close();
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//				
//				
//				
//				
//				
//				
//				
//				
//				
//				
//				
//				helper.setTo(bills.getEmail());
//				helper.setSubject("Cảm ơn bạn đã đặt hàng!");
//				helper.setText(content,true);
//				for (Map.Entry<Integer, CartDTO> itemCart : carts.entrySet()) {
//					helper.addInline("image" + itemCart.getValue().getProductEntity().getProduct_id(),
//							 new File("D:/apache-tomcat-8.5.57/images/" + itemCart.getValue().getProductEntity().getProductimg()));
//				}
//				helper.addInline("maillogo",
//						 new File("D:/apache-tomcat-8.5.57/images/maillogo.png"));
//				// Send Message!
//				this.emailSender.send(message);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				// ... cleanup that will execute whether or not an error occurred ...
//			}
//		}
//		session.removeAttribute("Cart");
//	}
		SessionUtil.getInstance().removeValue(req, "Cart");
        mapper.writeValue(resp.getOutputStream(), bills);
	}
}
