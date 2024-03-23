/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.report;

import constant.ConstAccount;
import dal.ManageAccDAO;
import dal.ReportDAO;
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
 * @author Dung
 */
@WebServlet(name = "ReportBlockServlet", urlPatterns = {"/reportBlock"})
public class ReportBlockServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        String accid = request.getParameter("accid");
        String request_id = request.getParameter("rid");
        String ms = "";
        if (accid == null || accid.isEmpty()) {
            boolean RequestState = ReportDAO.gI().UpdateRequest(2, acc.getAccid(), Integer.parseInt(request_id));
            ms = RequestState ? "Hủy yêu cầu thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        } else {
            ManageAccDAO.gI().manageAccount(ConstAccount.ACCTION_BLOCK, null, null, null, "1", null, null, accid);
            boolean RequestState = ReportDAO.gI().UpdateRequest(1, acc.getAccid(), Integer.parseInt(request_id));
            ms = RequestState ? "Khóa tài khoản thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        }
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("managerReport").forward(request, response);
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
        processRequest(request, response);
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
