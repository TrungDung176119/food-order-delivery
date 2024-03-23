/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.report;

import dal.AccountDAO;
import dal.ProductDAO;
import dal.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.product.Product;
import model.profile.Account;
import model.report.ReportRequest;

/**
 *
 * @author Dung
 */
@WebServlet(name = "ManagerReportServlet", urlPatterns = {"/managerReport"})
public class ManagerReportServlet extends HttpServlet {

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
            out.println("<title>Servlet ManagerReportServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerReportServlet at " + request.getContextPath() + "</h1>");
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
        List<ReportRequest> listrp = ReportDAO.gI().showAllReportByType(1);
        List<ReportRequest> listra = ReportDAO.gI().showAllReportByType(0);
        int count0 = ReportDAO.gI().ReportRequestCount(0);
        int count1 = ReportDAO.gI().ReportRequestCount(1);
        int sum = count0 + count1;
        for (ReportRequest report : listra) {
            Account reportedAccount = AccountDAO.gI().getAccountByid(report.getReportedthing_id());
            Account reportAcc = AccountDAO.gI().getAccountByid(report.getAcc_id());
            if (report.getApproved_id() > 0) {
                Account approvedAcc = AccountDAO.gI().getAccountByid(report.getApproved_id());
                report.setApprovedAcc(approvedAcc);
            }
            report.setReportedAccount(reportedAccount);
            report.setReportAcc(reportAcc);
        }

        for (ReportRequest report : listrp) {
            Product reportedProduct = ProductDAO.gI().getProductByID(report.getReportedthing_id());
            Account reportAcc = AccountDAO.gI().getAccountByid(report.getAcc_id());
            if (report.getApproved_id() > 0) {
                Account approvedAcc = AccountDAO.gI().getAccountByid(report.getApproved_id());
                report.setApprovedAcc(approvedAcc);
            }
            report.setReportedProduct(reportedProduct);
            report.setReportAcc(reportAcc);
        }
        request.setAttribute("listrp", listrp);
        request.setAttribute("listra", listra);
        request.setAttribute("count0", count0);
        request.setAttribute("count1", count1);
        request.setAttribute("count", sum);
        request.getRequestDispatcher("managerReport.jsp").forward(request, response);
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
