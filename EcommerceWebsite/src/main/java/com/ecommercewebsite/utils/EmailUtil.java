package com.ecommercewebsite.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import com.ecommercewebsite.dto.CartDTO;

public class EmailUtil {
	private static final String UPLOAD_DIRECTORY = "/uploads";
	
    public static void sendEmail(String host, String port,
            final String senderEmail, String senderName, final String password,
            String recipientEmail, String subject, String message) throws AddressException,
            MessagingException, UnsupportedEncodingException {
  
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
  
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        };
  
        Session session = Session.getInstance(properties, auth);
  
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
  
        msg.setFrom(new InternetAddress(senderEmail, senderName));
        InternetAddress[] toAddresses = { new InternetAddress(recipientEmail) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
  
        // sends the e-mail
        Transport.send(msg);
  
    }
    
    public static void sendCheckoutEmail(String host, String port,
            final String senderEmail, String senderName, final String password,
            String recipientEmail, String subject, String message,  Map<Long, CartDTO> carts, String uuid2, String uploadPath) throws AddressException,
            MessagingException, UnsupportedEncodingException {
    	
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
  
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        };
  
        Session session = Session.getInstance(properties, auth);
        
        try {
        	
            // Tạo email
            MimeMessage message1 = new MimeMessage(session);
//            message1.setFrom(new InternetAddress(senderEmail, senderName));
            //message1.setFrom(new InternetAddress(username));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message1.setSubject("Cảm ơn bạn đã đặt hàng!");
            // Lấy nội dung HTML từ URL
            String content = "";
            try {
                URLConnection connection = new URL("http://localhost:8080/EcommerceWebsite/gio-hang/sendmail/" + uuid2).openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                
                InputStreamReader reader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                Scanner scanner = new Scanner(reader);
                scanner.useDelimiter("\\Z");
                content = scanner.next();
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Multipart email (HTML + images)
            MimeMultipart multipart = new MimeMultipart("related");

            // HTML part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);

            // Inline images from cart
            for (Map.Entry<Long, CartDTO> itemCart : carts.entrySet()) {
                String cid = "image" + itemCart.getValue().getProduct().getId();
                String imagePath = ""+ uploadPath +"/" + itemCart.getValue().getProduct().getProductimg();
                addInlineImage(multipart, cid, imagePath);
            }

            // Logo
//            addInlineImage(multipart, "maillogo", "D:/apache-tomcat-8.5.57/images/maillogo.png");

            message1.setContent(multipart);

            // Gửi mail
            Transport.send(message1);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    private static void addInlineImage(MimeMultipart multipart, String contentId, String path) throws MessagingException {
        MimeBodyPart imagePart = new MimeBodyPart();
        try {
            imagePart.attachFile(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagePart.setContentID("<" + contentId + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        multipart.addBodyPart(imagePart);
    }
}