/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author admin
 */
public class Mail {
    
    public static boolean sendMail(String userRecieveEmail, String title, String content) {
        String APP_EMAIL = "fuholafood@gmail.com"; // our email
        String APP_PASSWORD = "buoc unuo tibx rrxj"; // our password

        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");   // HOST_NAME
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", 587);// Port for TLS/STARTTLS

        //get session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userRecieveEmail));
            message.setSubject(title);
            message.setContent(content, "text/html");

            // send message
            Transport.send(message);

            System.out.println("Message sent successfully");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
