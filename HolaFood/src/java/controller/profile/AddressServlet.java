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
import model.profile.AccountAddress;
import model.profile.AccountAddress;
import service.Validation;

/**
 *
 * @author Trung Dung
 */
@WebServlet(name="AddressServlet", urlPatterns={"/address"})
public class AddressServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddressServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddressServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        AccountAddress accd = adao.getAccAddressByid(acc.getAccid());
        request.setAttribute("AccAddr", accd);
        request.getRequestDispatcher("address.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
        String logPhone = request.getParameter("phone");
        String logAddress = request.getParameter("addr");
        String logNote = request.getParameter("note");
        String status = request.getParameter("status");
        String ms = "";

        Validation v = new Validation();

        // Create DAO instance
        AccountDAO adao = new AccountDAO();

        // Call the editProfile method
        if (action.equals("update_address")) {
            boolean isExistAcc = adao.isAccountExist(acc.getAccid(), ConstAccount.IS_ACCOUNT_ADDRESS);
            if (isExistAcc) {
                if (!v.isValidPhoneNumber(logPhone)) {
                    ms = "Vui lòng nhập đúng định dạng số điện thoại.";
                    AccountAddress accd = adao.getAccAddressByid(acc.getAccid());
                    request.setAttribute("AccAddr", accd);
                } else {
                    boolean update = adao.editAddress(ConstAccount.ACTION_UPDATE, acc.getAccid(), logName, logPhone, logAddress, logNote, status);
                    if (update) {
                        ms = "Cập nhật hồ sơ thành công.";
                        AccountAddress accd = adao.getAccAddressByid(acc.getAccid());
                        request.setAttribute("AccAddr", accd);
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                        AccountAddress accd = adao.getAccAddressByid(acc.getAccid());
                        request.setAttribute("AccAddr", accd);
                    }
                }
            } else {
                if (!v.isValidPhoneNumber(logPhone)) {
                    ms = "Vui lòng nhập đúng định dạng số điện thoại.";
                    AccountAddress accd = adao.getAccAddressByid(acc.getAccid());
                    request.setAttribute("AccAddr", accd);
                } else {
                    boolean insert = adao.editAddress(ConstAccount.ACTION_INSERT, acc.getAccid(), logName, logPhone, logAddress, logNote, status);
                    if (insert) {
                        ms = "Cập nhật hồ sơ thành công.";
                        AccountAddress accd = adao.getAccAddressByid(acc.getAccid());
                        request.setAttribute("AccAddr", accd);

                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                        AccountAddress accd = adao.getAccAddressByid(acc.getAccid());
                        request.setAttribute("AccAddr", accd);
                    }
                }
            }
        }
        request.setAttribute("msSuccess", ms);
        // Profile updated successfully
        request.getRequestDispatcher("address.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
