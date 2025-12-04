package murach.util;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailUtilGmail {
    public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
            throws MessagingException {
        
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // --- THAY THÔNG TIN CỦA BẠN Ở ĐÂY ---
        final String username = "congtruong19802005@gmail.com";
        final String password = "tcxo gmtr hjhy pgko"; 
        // -------------------------------------

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        
        if (bodyIsHTML) message.setContent(body, "text/html; charset=utf-8");
        else message.setText(body);

        Transport.send(message);
    }
}