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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.product.Product;
import model.profile.Account;
import model.report.ReportType;

/**
 *
 * @author Dung
 */
@WebServlet(name = "AddReportServlet", urlPatterns = {"/addReport"})
public class AddReportServlet extends HttpServlet {

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
            out.println("<title>Servlet AddReportServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddReportServlet at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("message", "Vui lòng đăng nhập trước khi sử dụng chức năng này.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String aid = request.getParameter("aid");
        String pid = request.getParameter("pid");
        if (aid != null) {
            int id = Integer.parseInt(aid);
            Account a = AccountDAO.gI().getAccountByid(id);
            request.setAttribute("ReportedAccount", a);
        } else if (pid != null) {
            Product p = ProductDAO.gI().getProductByID(pid);
            request.setAttribute("ReportedProduct", p);
        }
        List<ReportType> listrt = ReportDAO.gI().showAllReportType();
        request.setAttribute("listrt", listrt);
        request.getRequestDispatcher("report.jsp").forward(request, response);
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
        int reason_id = Integer.parseInt(request.getParameter("reason"));
        String report_description = request.getParameter("report_description");
        Date report_timestamp = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(report_timestamp);
        String proof = request.getParameter("proof");
        String ms="";
        boolean addReport;
        if (request.getParameter("account") != null) {
            addReport = ReportDAO.gI().addRequestReport(acc.getAccid(), reason_id, Integer.parseInt(request.getParameter("account")), report_description, formattedDate, 0, proof, 0, 0);
            ms = addReport ? "Tố cáo tài khoản thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        } else if (request.getParameter("product") != null) {
            addReport = ReportDAO.gI().addRequestReport(acc.getAccid(), reason_id, Integer.parseInt(request.getParameter("product")), report_description, formattedDate, 0, proof, 1, 0);
            ms = addReport ? "Tố cáo sản phẩm thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        }
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("report.jsp").forward(request, response);
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
