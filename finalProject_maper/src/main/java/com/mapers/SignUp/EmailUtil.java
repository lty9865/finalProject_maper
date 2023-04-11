package com.mapers.SignUp;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
    private static final String SMTP_HOST = "smtp.naver.com";
    private static final String FROM_EMAIL = "projecttestp@naver.com";
    private static final String FROM_PASSWORD = "9125Tkdehchemd!";
    
    public static void sendEmail(String toAddress, String subject, String message) {
        Properties props = new Properties();
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });
        
        try {
            
            Message msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress(FROM_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            
            // Subject
            msg.setSubject(subject);
            
            // Set the message content
            msg.setText(message);
            
            Transport.send(msg);
            
            System.out.println("send email success");
            
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("email sending error");
        }
    }
}
