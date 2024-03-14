/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager.product;

import constant.ConstHome;
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
import java.util.List;
import model.product.Category;
import model.product.Product;
import model.profile.Account;

/**
 *
 * @author Dung
 */
@WebServlet(name = "UpdateProServlet", urlPatterns = {"/updatePro"})
public class UpdateProServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProServlet at " + request.getContextPath() + "</h1>");
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
        String productID = request.getParameter("pid");
        List<Category> listC = ProductDAO.gI().getAllCategory();
        request.setAttribute("listC", listC);
        if (productID == null || productID.isEmpty()) {
            request.getRequestDispatcher("update.jsp").forward(request, response);
        } else {
            Product p = ProductDAO.gI().getProductByID(productID);
            request.setAttribute("detail", p);
            request.getRequestDispatcher("update.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        String productid = request.getParameter("productid");
        String image = request.getParameter("image");
        String title = request.getParameter("title");
        int cate_id = Integer.parseInt(request.getParameter("category"));
        int current_price = Integer.parseInt(request.getParameter("new_price"));
        int in_stock = Integer.parseInt(request.getParameter("in_stock"));
        String status = request.getParameter("status");
        String describe = request.getParameter("describe");
        int s_id = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid()).getSeller_id();
        String ms = "";

        try {
            if (productid == null || productid.isEmpty()) {
                boolean insert = ManagerProDAO.gI().manageProduct("INSERT", 0, cate_id, s_id, image, title, 0, current_price, 0, in_stock, status, describe, 0);
                ms = insert ? "Thêm sản phẩm thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
            } else {
                int pid = Integer.parseInt(productid);
                int sold = Integer.parseInt(request.getParameter("sold"));
                float rating = Float.parseFloat(request.getParameter("rating"));
                int old_price = Integer.parseInt(request.getParameter("current_price"));
                boolean update = ManagerProDAO.gI().manageProduct("UPDATE", pid, cate_id, s_id, image, title, old_price, current_price, sold, in_stock, status, describe, rating);
                ms = update ? "Cập nhật sản phẩm thành công." : "Có lỗi xảy ra. Vui lòng thực hiện lại.";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ms = "Có lỗi xảy ra. Vui lòng thực hiện lại.";
        }

        request.setAttribute("ms", ms);

        // Retrieve updated account list and total count
        List<Product> listp = ManagerCateDAO.gI().getProductBySeller(ConstShop.MENU_TODAY, 0, s_id);
        request.setAttribute("listPro", listp);
        request.setAttribute("sum", listp.size());

        // Forward to the managerAcc.jsp page
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
