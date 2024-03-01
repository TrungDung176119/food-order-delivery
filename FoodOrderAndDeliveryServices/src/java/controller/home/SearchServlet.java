/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import constant.ConstHome;
import dal.ProductDAO;
import dal.ServiceDAO;
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
import utils.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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

        String cateid = request.getParameter("cateid");
        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");
        String sortBy = request.getParameter("sortBy");

        // set active category that was choosen 
        try {
            int cid = Integer.parseInt(cateid);
            request.setAttribute("tag", cid);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        List<Product> listPbyCondition = ProductDAO.gI().searchProductsByCondition(null, null, cateid);
        if (cateid != null && sortBy != null) {
            listPbyCondition = ProductDAO.gI().searchProductsByCondition(null, sortBy, cateid);
        }
        if (action != null && action.equals("search")) {
            listPbyCondition = ProductDAO.gI().searchProductsByCondition(keyword, null, cateid);
        }
        if (action != null && sortBy != null) {
            listPbyCondition = ProductDAO.gI().searchProductsByCondition(keyword, sortBy, null);
        }

        request.setAttribute("sortBy", sortBy);
        request.setAttribute("cateid", cateid);
        request.setAttribute("keyword", keyword);
        request.setAttribute("listP", listPbyCondition);

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            if (keyword != null && !keyword.trim().isEmpty()) {
                boolean addNewSearchHis = ServiceDAO.gI().addNewSearchHistory(acc.getAccid(), keyword);
            }
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

        request.getRequestDispatcher("search.jsp").forward(request, response);
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
        String keyword = request.getParameter("keyword");

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
