package murach.email;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.mail.MessagingException;
import murach.business.User;
import murach.data.UserDB;
import murach.util.MailUtilGmail;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String url = "/index.jsp";
        String action = request.getParameter("action");
        if (action == null) action = "join";

        if (action.equals("add")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            
            User user = new User(firstName, lastName, email);
            
            // 1. Lưu vào Database
            UserDB.insert(user);
            
            // 2. Gửi Email
            String to = email;
            String from = "no-reply@murach.com"; // Hoặc email của bạn
            String subject = "Welcome to our Email List";
            String body = "Dear " + firstName + ",\n\n" +
                          "Thanks for joining our email list.\n" +
                          "Have a great day!";
            try {
                MailUtilGmail.sendMail(to, from, subject, body, false);
            } catch (MessagingException e) {
                this.log("Unable to send email: " + e.getMessage());
            }
            
            request.setAttribute("user", user);
            url = "/thanks.jsp";
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}