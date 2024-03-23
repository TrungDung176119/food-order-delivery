/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager.product;

import constant.ConstHome;
import constant.ConstSeller;
import constant.ConstShop;
import dal.ManagerCateDAO;
import dal.ManagerProDAO;
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
import java.util.ArrayList;
import java.util.List;
import model.product.Category;
import model.product.Product;
import model.profile.Account;

/**
 *
 * @author Dung
 */
@WebServlet(name = "SearchProServlet", urlPatterns = {"/searchPro"})
public class SearchProServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchProServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchProServlet at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("message", "Tài khoản của bạn không có quyền vào trang này.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String cateid = request.getParameter("cateid");
        String status = request.getParameter("status");
        List<Category> listC = ProductDAO.gI().getAllCategory();
        List<Product> listp = new ArrayList<>();
        request.setAttribute("listC", listC);

        if ((status == null || status.isEmpty()) && (cateid == null || cateid.isEmpty())) {
            if (acc.getRoleid() == 3) {
                listp = ProductDAO.gI().getProductByType(ConstHome.TODAY_SUGGESTION, 0);
            }
            if (acc.getRoleid() == 2) {
                int s_id = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid()).getSeller_id();
                listp = ManagerCateDAO.gI().getProductBySeller(ConstShop.LOAD_PRODUCT_BY_SELLER, 0, s_id);
            }
        } else if ((status == null || status.isEmpty()) && (cateid != null && !cateid.isEmpty())) {
            if (acc.getRoleid() == 3) {
                listp = ManagerProDAO.gI().filterProduct("CATE", "ADMIN", cateid, status, 0);
            } else if (acc.getRoleid() == 2) {
                int s_id = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid()).getSeller_id();
                listp = ManagerProDAO.gI().filterProduct("CATE", "SELLER", cateid, status, s_id);
            }
        }
        if ((status != null && !status.isEmpty()) && (cateid == null || cateid.isEmpty())) {
            if (acc.getRoleid() == 3) {
                listp = ManagerProDAO.gI().filterProduct("STATUS", "ADMIN", cateid, status, 0);
            } else if (acc.getRoleid() == 2) {
                int s_id = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid()).getSeller_id();
                listp = ManagerProDAO.gI().filterProduct("STATUS", "SELLER", cateid, status, s_id);
            }
        }
        request.setAttribute("listPro", listp);
        request.setAttribute("sum", listp.size());
        request.getRequestDispatcher("manager.jsp").forward(request, response);
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
        String title = request.getParameter("query");
        String ms = "";
        List<Product> listp = null;
        if (acc.getRoleid() == 2) {
            int s_id = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid()).getSeller_id();
            listp = ManagerProDAO.gI().searchbyCondition(title, s_id);
        } else if (acc.getRoleid() == 3) {
            listp = ManagerProDAO.gI().searchbyCondition(title, constant.ConstAccount.IS_ADMIN);
        }
        request.setAttribute("listPro", listp);
        request.setAttribute("sum", listp.size());
        List<Category> listC = ProductDAO.gI().getAllCategory();
        request.setAttribute("listC", listC);
        if (!listp.isEmpty()) {
            ms = "Tìm thấy sản phẩm có tên chứa từ khóa " + "'" + title + "'";
        } else {
            ms = "Không tìm thấy sản phẩm có tên chứa từ khóa " + "'" + title + "'";
        }
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("manager.jsp").forward(request, response);
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
