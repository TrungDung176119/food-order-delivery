/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import constant.ConstHome;
import constant.ConstShop;
import dal.DashboardDAO;
import dal.ManagerCateDAO;
import dal.ManagerSellerDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import model.dashboard.ProDashboard;
import model.product.Category;
import model.product.Product;
import model.profile.Account;

/**
 *
 * @author anhdu
 */
@WebServlet(name = "FilterDashboardProServlet", urlPatterns = {"/filterdbpro"})
public class FilterDashboardProServlet extends HttpServlet {

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
            out.println("<title>Servlet FilterDashboardProServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterDashboardProServlet at " + request.getContextPath() + "</h1>");
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
        } else if (acc.getRoleid() == 1) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Tài khoản của bạn không được phép vào trang này.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        List<Category> listC = ProductDAO.gI().getAllCategory();
        if (acc.getRoleid() == 3) {
            List<Product> listp = ProductDAO.gI().getProductByType(ConstHome.TODAY_SUGGESTION, 0);
            request.setAttribute("listPro", listp);
            request.setAttribute("sum", listp.size());
        }
        if (acc.getRoleid() == 2) {
            int s_id = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid()).getSeller_id();
            List<Product> listp = ManagerCateDAO.gI().getProductBySeller(ConstShop.LOAD_PRODUCT_BY_SELLER, 0, s_id);
            request.setAttribute("listPro", listp);
            request.setAttribute("sum", listp.size());
        }
        try {

            int type = 1;
            String startDateS = request.getParameter("startDate");
            String endDateS = request.getParameter("endDate");
//        PrintWriter out = response.getWriter();
//        out.print(startDateS + ","+endDateS);
            Date startDate = null;
            Date endDate = null;
            if (startDateS != null && !startDateS.isEmpty()) {
                startDate = Date.valueOf(startDateS);
            }
            if (endDateS != null && !endDateS.isEmpty()) {
                endDate = Date.valueOf(endDateS);
            }
            List<ProDashboard> listPD = DashboardDAO.gI().getAllProDashboard(startDate, endDate);
            request.setAttribute("startDate", startDateS);
            request.setAttribute("endDate", endDateS);
            request.setAttribute("listPD", listPD);
            request.setAttribute("tag", type);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
