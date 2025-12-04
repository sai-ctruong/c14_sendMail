package murach.util;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailUtilGmail {

    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML)
            throws MessagingException {

        Properties props = new Properties();
        // Cấu hình SMTP của SendGrid
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Hãy nhớ thay thế bằng API Key thật của bạn
        final String username = "apikey";
        final String password = "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"; 

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html; charset=utf-8");
        } else {
            message.setText(body);
        }

        
        message.setFrom(new InternetAddress("congtruong19802005@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

        Transport.send(message);
    }
}