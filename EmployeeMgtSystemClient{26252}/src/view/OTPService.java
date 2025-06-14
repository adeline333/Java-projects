/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author adeli
 */
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import javax.mail.*;
import javax.mail.internet.*;

public class OTPService {
    private static final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();
    private static final long OTP_VALIDITY = 5 * 60 * 1000; // 5 minutes in milliseconds
    
    // Email configuration - should be moved to properties file in production
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_USERNAME = "adelinetuyizere333@gmail.com";
    private static final String EMAIL_PASSWORD = "qwro xoog qhdp skye";

    public static String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    public static void sendOTPEmail(String recipientEmail, String otp) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.ssl.trust", SMTP_HOST);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL_USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Your OTP Verification Code");
        message.setText("Your OTP code is: " + otp + "\nThis code is valid for 5 minutes.");

        Transport.send(message);
        
        // Store OTP with timestamp
        otpStorage.put(recipientEmail, otp + ":" + System.currentTimeMillis());
    }

    public static boolean verifyOTP(String email, String userEnteredOTP) {
        String storedData = otpStorage.get(email);
        if (storedData == null) {
            return false;
        }
        
        String[] parts = storedData.split(":");
        String storedOTP = parts[0];
        long timestamp = Long.parseLong(parts[1]);
        
        // Check if OTP is expired
        if ((System.currentTimeMillis() - timestamp) > OTP_VALIDITY) {
            otpStorage.remove(email);
            return false;
        }
        
        return storedOTP.equals(userEnteredOTP);
    }
}