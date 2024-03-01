/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.acc;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.profile.Account;

/**
 *
 * @author truon
 */
@WebServlet(name = "RegisterSeller", urlPatterns = {"/registerseller"})
public class RegisterSeller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterSeller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterSeller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("registerSeller.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String username = request.getParameter("store_name");
        String address = request.getParameter("address_store");
        String email = request.getParameter("email_store");
        String phonenumber = request.getParameter("phone_store");

        String message = "";
        String confirmationCode = generateConfirmationCode();

        session.setAttribute("store_name", request.getParameter("store_name"));
        session.setAttribute("address_store", request.getParameter("address_store"));
        session.setAttribute("email_store", request.getParameter("email_store"));
        session.setAttribute("phone_store", request.getParameter("phone_store"));
        session.setAttribute("confirmationCode", confirmationCode);

        // Lưu mã xác nhận vào cơ sở dữ liệu hoặc cấu trúc dữ liệu tạm thời (ví dụ: lưu vào ServletContext)
        getServletContext().setAttribute("confirmationCode", confirmationCode);

        // Gửi email chứa mã xác nhận
        sendConfirmationEmail(email, confirmationCode);

        // Chuyển hướng người dùng đến trang thay đổi mật khẩu
        response.sendRedirect("checkemail.jsp");

    }

    private String generateConfirmationCode() {
        // Triển khai mã xác nhận ngẫu nhiên ở đây (ví dụ: sử dụng UUID)
        return UUID.randomUUID().toString();
    }

    private void sendConfirmationEmail(String email, String confirmationCode) {
        final String username = "fuholafood@gmail.com";
        final String password = "buoc unuo tibx rrxj";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Confirm password reset.");
            message.setText("Code: " + confirmationCode);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
