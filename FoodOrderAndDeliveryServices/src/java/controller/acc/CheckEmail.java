/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.acc;

import utils.*;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.profile.Account;

/**
 *
 * @author truon
 */
@WebServlet(name = "CheckEmail", urlPatterns = {"/checkemail"})
public class CheckEmail extends HttpServlet {

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
            out.println("<title>Servlet CheckEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckEmail at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        String code = request.getParameter("code");
//        String newPassword = req.getParameter("newPassword");

        // Kiểm tra mã xác nhận và mật khẩu mới
        // Lấy mã xác nhận từ ServletContext
        String storeName = (String) session.getAttribute("store_name");
        String address = (String) session.getAttribute("address_store");
        String email = (String) session.getAttribute("email_store");
        String phoneNumber = (String) session.getAttribute("phone_store");
        String confirmationCode = (String) session.getAttribute("confirmationCode");

        if (confirmationCode != null && confirmationCode.equals(code)) {
            // Xác nhận mã xác nhận thành công
            // Thực hiện thay đổi mật khẩu (ví dụ: lưu vào cơ sở dữ liệu)

            // Reset mã xác nhận trong ServletContext
            getServletContext().removeAttribute("confirmationCode");

            AccountDAO.gI().Seller(storeName, address, email, phoneNumber,acc.getAccid());
            // Chuyển hướng người dùng đến trang thành công
            response.sendRedirect("home");
        } else {
            // Mã xác nhận không hợp lệ
            // Chuyển hướng người dùng đến trang lỗi
            response.sendRedirect("checkemail.jsp");
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
