package com.ecommercewebsite.controller.controller.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/images")
public class ImageController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2109176479490346554L;
	private String imagePath;

	@Override
	public void init() throws ServletException {
		imagePath = System.getProperty("catalina.home") + File.separator + "images";
		//catalina.home  --> ex: c:/tomcat-9.0/ + images
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String requestImage = req.getPathInfo();

		String requestImage = req.getParameter("image");
		// exp    image.jsp
	
		System.out.print(requestImage);
		if (requestImage == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		File image = new File(imagePath, URLDecoder.decode(requestImage, "UTF-8"));
		System.out.println(URLDecoder.decode(requestImage, "UTF-8"));
		// Check if file actually exists in file system.
		if (!image.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		String contentType = getServletContext().getMimeType(image.getName());
		// check if file is actually an image
		if (contentType == null || !contentType.startsWith("image")) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		resp.reset();
		resp.setContentType(contentType);
		resp.setHeader("Content-Length", String.valueOf(image.length()));
		// write img content to reponse
		Files.copy(image.toPath(), resp.getOutputStream());

	}

}
