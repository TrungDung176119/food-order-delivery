/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager.account;

import constant.ConstAccount;
import dal.AccountDAO;
import dal.ManageAccDAO;
import dal.ManagerOrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.profile.Account;

/**
 *
 * @author Dung
 */
@WebServlet(name = "EditAccController", urlPatterns = {"/updateAcc"})
public class UpdateAccServlet extends HttpServlet {

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
            out.println("<title>Servlet EditAccController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAccController at " + request.getContextPath() + "</h1>");
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
        } else if (acc.getRoleid() != 3) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Tài khoản của bạn không có quyền vào trang này.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String accid = request.getParameter("accID");
        if (accid == null || accid.isEmpty()) {
            request.getRequestDispatcher("updateAcc.jsp").forward(request, response);
        } else {
            int acc_id = Integer.parseInt(accid);
//            AccountDAO acca = new AccountDAO();
            Account a = AccountDAO.gI().getAccountByid(acc_id);
            request.setAttribute("acca", a);
            request.getRequestDispatcher("updateAcc.jsp").forward(request, response);
        }
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
        String accid = request.getParameter("accid");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String roleid = request.getParameter("roleid");
        String isBlock = request.getParameter("isBlock");
        String coin = "0";
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String createTime = currentTime.format(formatter);
        String ms = "";
        try {
            if (accid == null || accid.isEmpty()) {
                boolean existName = AccountDAO.gI().isUsernameExist(username);
                if (existName) {
                    ms = "Tên tài khoản này đã tồn tại. Vui lòng dùng tên khác.";
                } else {
                    // Insert new account
                    boolean insert = ManageAccDAO.gI().manageAccount(ConstAccount.ACTION_INSERT, username, password, roleid, isBlock, coin, createTime, null);
                    ms = insert ? "Thêm tài khoản thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
                }
            } else {
                // Update existing account
                boolean update = ManageAccDAO.gI().manageAccount(ConstAccount.ACTION_UPDATE, username, password, roleid, isBlock, coin, createTime, accid);
                ms = update ? "Cập nhật tài khoản thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            ms = "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        }
        request.setAttribute("ms", ms);

        // Retrieve updated account list and total count
        List<Account> listA = ManageAccDAO.gI().getAllNormalAccount();
        request.setAttribute("total", listA.size());
        request.setAttribute("listA", listA);

        // Forward to the managerAcc.jsp page
        request.getRequestDispatcher("managerAcc.jsp").forward(request, response);

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
