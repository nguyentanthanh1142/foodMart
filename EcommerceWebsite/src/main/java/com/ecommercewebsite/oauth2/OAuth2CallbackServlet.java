package com.ecommercewebsite.oauth2;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommercewebsite.model.UserModel;
import com.ecommercewebsite.service.IUserService;
import com.ecommercewebsite.utils.SessionUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

@WebServlet("/oauth2callback")
public class OAuth2CallbackServlet extends HttpServlet {
	/**
	 * 
	 */
	@Inject
	private IUserService userService;
	private static final long serialVersionUID = -971063794492151457L;
	private static final String CLIENT_ID = "16254816110-43212mlei2kqqton2v8rfbsfha666s2p.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/EcommerceWebsite/oauth2callback";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");

        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
            "https://oauth2.googleapis.com/token",
            CLIENT_ID, CLIENT_SECRET, code, REDIRECT_URI)
            .execute();

        GoogleCredential credential = new GoogleCredential().setAccessToken(tokenResponse.getAccessToken());
        System.out.print(tokenResponse.getAccessToken());
        Oauth2 oauth2 = new Oauth2.Builder(
            new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
            .setApplicationName("YourAppName")
            .build();

        Userinfo userinfo = oauth2.userinfo().get().execute();

        UserModel user = userService.getUserByEmail(userinfo.getEmail());
        System.out.println("Google ID: " + userinfo.getId());
        if (user != null) {
            if (user.getGoogle_id() == null) {
            	userService.updateGoogleId(userinfo.getEmail(), userinfo.getId());
            }
//            session.setAttribute("currentUser", existingUser);
        } else {
        	user = new UserModel();
        	user.setUserName(userinfo.getEmail());
        	user.setPicture(userinfo.getPicture());
        	user.setGoogle_id(userinfo.getId());
        	user.setFullName(userinfo.getFamilyName());
        	user = userService.insertGoogleUser(user);
        	
        	
        }
    	SessionUtil.getInstance().removeValue(request, "USERMODEL");
		String token = UUID.randomUUID().toString();
		userService.saveRememberToken(user.getId(), token);
		Cookie cookie = new Cookie("rememberMe", token);
		cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
		cookie.setPath("/");
//		cookie.setHttpOnly(true);
		// cookie.setSecure(true); // nếu HTTPS thì mở cái này
		response.addHeader("Set-Cookie", "rememberMe=" + token + "; Max-Age=2592000; Path=/; HttpOnly");
        // Tùy chọn: lưu thông tin người dùng vào DB bằng JDBC
        // JDBC code here...

        // Lưu thông tin người dùng vào session
    	SessionUtil.getInstance().putValue(request, "USERMODEL", user);

        response.sendRedirect(request.getContextPath() + "/trang-chu");
    }
	
}
