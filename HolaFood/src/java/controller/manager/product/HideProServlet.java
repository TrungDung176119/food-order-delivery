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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.product.Category;
import model.product.Product;
import model.profile.Account;

/**
 *
 * @author Dung
 */
@WebServlet(name = "HideProServlet", urlPatterns = {"/hidePro"})
public class HideProServlet extends HttpServlet {

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
        String ms = "";
        int pid = Integer.parseInt(request.getParameter("pid"));
        String status = request.getParameter("status");
        if (!status.equals("Ẩn")) {
            boolean hide = ManagerProDAO.gI().manageProduct("HIDE", pid, 0, 0, null, null, 0, 0, 0, 0, "Ẩn", null, 0);
            ms = hide ? "Ẩn sản phẩm thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        } else {
            boolean unhide = ManagerProDAO.gI().manageProduct("HIDE", pid, 0, 0, null, null, 0, 0, 0, 0, "Yêu thích", null, 0);
            ms = unhide ? "Bỏ ẩn sản phẩm thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        }

        request.setAttribute("ms", ms);
        List<Category> listC = ProductDAO.gI().getAllCategory();

        if (acc.getRoleid()== 3) {
            List<Product> listp = ProductDAO.gI().getProductByType(ConstHome.TODAY_SUGGESTION, 0);
            request.setAttribute("listPro", listp);
            request.setAttribute("sum", listp.size());
        }

        if (acc.getRoleid()== 2) {
            int s_id = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid()).getSeller_id();
            List<Product> listp = ManagerCateDAO.gI().getProductBySeller(ConstShop.MENU_TODAY, 0, s_id);
            request.setAttribute("listPro", listp);
            request.setAttribute("sum", listp.size());
        }

        request.setAttribute("listC", listC);
        request.getRequestDispatcher("manager.jsp").forward(request, response);
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
