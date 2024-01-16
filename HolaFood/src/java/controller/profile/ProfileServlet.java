/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import constant.ConstAccount;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.profile.Account;
import model.profile.AccountDetail;
import service.Validation;

/**
 *
 * @author Trung Dung
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        AccountDAO adao = new AccountDAO();
        AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
        request.setAttribute("AccDetail", accd);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        String logName = request.getParameter("nickname");
        String logImage = request.getParameter("image");
        String logEmail = request.getParameter("email");
        String logPhone = request.getParameter("phone");
        int gender = Integer.parseInt(request.getParameter("gender"));
        Date dob = Date.valueOf(request.getParameter("dob"));
        String ms = "";

        Validation v = new Validation();

        // Create DAO instance
        AccountDAO adao = new AccountDAO();

        // Call the editProfile method
        if (action.equals("update_profile")) {
            boolean isExistAcc = adao.isAccountExist(acc.getAccid(), ConstAccount.IS_ACCOUNT_DETAIL);
            if (isExistAcc) {
                if (!v.isValidEmail(logEmail)) {
                    ms = "Vui lòng nhập đúng định dạng email.";
                    AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
                    request.setAttribute("AccDetail", accd);
                } else if (!v.isValidPhoneNumber(logPhone)) {
                    ms = "Vui lòng nhập đúng định dạng số điện thoại.";
                    AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
                    request.setAttribute("AccDetail", accd);
                } else {
                    boolean update = adao.editProfile(ConstAccount.ACTION_UPDATE, acc.getAccid(), logName, logImage, logEmail, logPhone, gender, dob);
                    if (update) {
                        ms = "Cập nhật hồ sơ thành công.";
                        AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
                        request.setAttribute("AccDetail", accd);
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                        AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
                        request.setAttribute("AccDetail", accd);
                    }
                }
            } else {
                if (!v.isValidEmail(logEmail)) {
                    ms = "Vui lòng nhập đúng định dạng email.";
                    AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
                    request.setAttribute("AccDetail", accd);
                } else if (!v.isValidPhoneNumber(logPhone)) {
                    ms = "Vui lòng nhập đúng định dạng số điện thoại.";
                    AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
                    request.setAttribute("AccDetail", accd);
                } else {
                    boolean insert = adao.editProfile(ConstAccount.ACTION_INSERT, acc.getAccid(), logName, logImage, logEmail, logPhone, gender, dob);
                    if (insert) {
                        ms = "Cập nhật hồ sơ thành công.";
                        AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
                        request.setAttribute("AccDetail", accd);

                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                        AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
                        request.setAttribute("AccDetail", accd);
                    }
                }
            }
        }
        request.setAttribute("msSuccess", ms);
        // Profile updated successfully
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
