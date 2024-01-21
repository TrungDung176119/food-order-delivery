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
import java.util.List;
import model.profile.Account;
import model.profile.AccountAddress;
import model.profile.AccountAddress;
import service.Validation;

/**
 *
 * @author Trung Dung
 */
@WebServlet(name = "AddressServlet", urlPatterns = {"/address"})
public class AddressServlet extends HttpServlet {

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
            out.println("<title>Servlet AddressServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddressServlet at " + request.getContextPath() + "</h1>");
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
        List<AccountAddress> accd = adao.getAccAddressByid(acc.getAccid());
        request.setAttribute("addresses", accd);
        request.getRequestDispatcher("address.jsp").forward(request, response);
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
        String logName = request.getParameter("nickname");
        String logPhone = request.getParameter("phone");
        String logAddress = request.getParameter("addr");
        String logNote = request.getParameter("note");
        String status = request.getParameter("status");
        String address_id = request.getParameter("addressid");
        String ms = "";

        Validation v = new Validation();
        AccountDAO adao = new AccountDAO();
        if (!v.isValidPhoneNumber(logPhone)) {
            ms = "Vui lòng nhập đúng định dạng số điện thoại.";
        } else {
            if (address_id == null || address_id.isEmpty()) {
                boolean insert = adao.manageAddress(ConstAccount.ACTION_INSERT, null, acc.getAccid(), logName, logPhone, logAddress, logNote, status);
                if (insert) {
                    ms = "Cập nhật địa chỉ thành công.";
                } else {
                    ms = "Có lỗi xảy ra. Vui lòng thực hiện lại.";
                }
            } else {
                boolean update = adao.manageAddress(ConstAccount.ACTION_UPDATE, address_id, acc.getAccid(), logName, logPhone, logAddress, logNote, status);
                if (update) {
                    ms = "Cập nhật địa chỉ thành công.";
                } else {
                    ms = "Có lỗi xảy ra. Vui lòng thực hiện lại.";
                }
            }
        }
        request.setAttribute("ms", ms);
        List<AccountAddress> accd = adao.getAccAddressByid(acc.getAccid());
        request.setAttribute("addresses", accd);
        request.getRequestDispatcher("address.jsp").forward(request, response);
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
