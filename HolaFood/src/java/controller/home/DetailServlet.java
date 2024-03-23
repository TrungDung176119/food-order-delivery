/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import constant.ConstHome;
import dal.AccountDAO;
import dal.ManagerSellerDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.cart.Item;
import model.product.Product;
import model.profile.Account;
import model.profile.Feedback;
import model.profile.Seller;
import utils.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "DetailServlet", urlPatterns = {"/detail"})
public class DetailServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailServlet at " + request.getContextPath() + "</h1>");
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

        // load product detail follow id
        int cateid = 0;
        String pid_raw = request.getParameter("pid");
        try {
            Product p = ProductDAO.gI().getProductByID(pid_raw);
            cateid = p.getCategory().getId();   //
            Seller seller = ManagerSellerDAO.gI().getSellerBySeller_Id(p.getSeller_id());
            request.setAttribute("detail", p);
            request.setAttribute("avg_rate", p.getRating());
            request.setAttribute("seller", seller);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // load list product related
        List<Product> relatedProduct = ProductDAO.gI().searchProductsByCondition(null, "", cateid + "");
        request.setAttribute("related", relatedProduct);

        List<Product> listBestPro = ProductDAO.gI().getBestProductOfSeller(ProductDAO.gI().getSidFromProduct(pid_raw));
        request.setAttribute("listP", listBestPro);

        // load all feedback with number of rating
        Account acc = (Account) session.getAttribute("acc");
        int roleid = 0;
        if (acc != null) {
            roleid = acc.getRoleid();
        }

        String rate_raw = request.getParameter("rate");
        if (rate_raw == null || rate_raw.equals("0")) {
            rate_raw = "0";
        }
        int rate = 0;
        try {
            rate = Integer.parseInt(rate_raw);
            List<Feedback> listFb = OrderDAO.gI().getAllFeedback(pid_raw, rate, roleid);
            if (listFb != null) {
                request.setAttribute("feedB", listFb);
            }
            request.setAttribute("tag", rate);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        // get number of rate
        request.setAttribute("rate1", ProductDAO.gI().getNumberOfRate(pid_raw, 1));
        request.setAttribute("rate2", ProductDAO.gI().getNumberOfRate(pid_raw, 2));
        request.setAttribute("rate3", ProductDAO.gI().getNumberOfRate(pid_raw, 3));
        request.setAttribute("rate4", ProductDAO.gI().getNumberOfRate(pid_raw, 4));
        request.setAttribute("rate5", ProductDAO.gI().getNumberOfRate(pid_raw, 5));

        // hide feedback by admin role
        String action = request.getParameter("action");
        String feed_id = request.getParameter("feed_id");
        try {
            if (action != null && action.equals("hide")) {
                boolean hide = OrderDAO.gI().setStatusFeedback(feed_id, 0);
            } else if (action != null && action.equals("show")) {
                boolean hide = OrderDAO.gI().setStatusFeedback(feed_id, 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //push product up to cart
        List<Product> listProduct = ProductDAO.gI().getProductByType(ConstHome.TODAY_SUGGESTION, 0);
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart();
        cart.initializeCartFromText(txt, listProduct);
        List<Item> listItem = cart.getItems();
        int amountItem;
        if (listItem != null) {
            amountItem = listItem.size();
        } else {
            amountItem = 0;
        }
        request.setAttribute("cart", cart);
        request.setAttribute("amount", amountItem);
        request.getRequestDispatcher("details.jsp").forward(request, response);
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

        String action = request.getParameter("action");

        //this code to save product up cookie
        Cookie[] cookies = request.getCookies();
        String txt = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    txt += cookie.getValue();
                    cookie.setMaxAge(0); // Xóa cookie cũ
                    response.addCookie(cookie);
                }
            }
        }
        String id = request.getParameter("pid");
        int number = 1;
        if (txt.isEmpty()) {
            txt = id + ":" + number;
        } else {
            txt += "-" + id + ":" + number;
        }
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(60 * 60 * 24 * 2); // Đặt thời hạn cho cookie
        response.addCookie(c);
        if (action == null || action.equals("add-to-cart")) {
            response.sendRedirect("detail?pid=" + id);
        } else if (action.equals("order-now")) {
            response.sendRedirect("showcart");
        }
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
