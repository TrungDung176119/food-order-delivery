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
import java.util.List;
import model.profile.Account;

/**
 *
 * @author admin
 */
@WebServlet(name="BlockAccServlet", urlPatterns={"/blockAcc"})
public class BlockAccServlet extends HttpServlet {
   
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }  else if (acc.getRoleid()!= 3) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Tài khoản của bạn không có quyền vào trang này.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String ms = "";
        String accid = request.getParameter("accID");
        boolean block = ManageAccDAO.gI().manageAccount(ConstAccount.ACCTION_BLOCK, null, null, null, "1", null, null, accid);
        ms = block ? "Khóa tài khoản thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        request.setAttribute("ms", ms);
        List<Account> listA = ManageAccDAO.gI().getAllNormalAccount();
        request.setAttribute("total", listA.size());
        request.setAttribute("listA", listA);
        request.getRequestDispatcher("managerAcc.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
