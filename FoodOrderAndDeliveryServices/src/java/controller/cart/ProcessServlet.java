/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

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
import utils.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProcessServlet", urlPatterns = {"/process"})
public class ProcessServlet extends HttpServlet {

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
            out.println("<title>Servlet ProcessServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath() + "</h1>");
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
        ProductDAO dao = new ProductDAO();
        List<Product> listProduct = dao.getProductByType(constant.ConstHome.TODAY_SUGGESTION, 0);
        Cookie[] arr = request.getCookies();
        String txt = "";

        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        Cart cart = new Cart();
        cart.initializeCartFromText(txt, listProduct);

        String num_raw = request.getParameter("number");
        String id_raw = request.getParameter("pid");

        int id = 0, num = 0;
        try {
            if (id_raw != null) {
                id = Integer.parseInt(id_raw);
            }
            Product p = dao.getProductByID(id);
            int stock = p.getNumber_in_stock();
            num = Integer.parseInt(num_raw);
            if (num == -1 && (cart.getQuantityByPId(id) <= 1)) {
                cart.deleteItem(id);
            } else {
                if (num == 1 && cart.getQuantityByPId(id) >= stock) {
                    num = 0;
                }
                Item i = new Item(p, num);
                cart.addItemIntoCart(i);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        List<Item> listItems = cart.getItems();
        txt = "";
        if (!listItems.isEmpty()) {
            txt = listItems.get(0).getProduct().getPid() + ":"
                    + listItems.get(0).getQuantity();
            for (int i = 1; i < listItems.size(); i++) {
                txt += "-" + listItems.get(i).getProduct().getPid() + ":"
                        + listItems.get(i).getQuantity();
            }
        }
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(60 * 60 * 24);
        response.addCookie(c);
//        request.setAttribute("cart", cart);

        List<Item> listItem = cart.getItems();
        int amountItem;
        if (listItem != null) {
            amountItem = listItem.size();
        } else {
            amountItem = 0;
        }
        HttpSession session = request.getSession();
        session.setAttribute("cart", cart);
        session.setAttribute("amount", amountItem);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
        ProductDAO dao = new ProductDAO();
        List<Product> listProduct = dao.getProductByType(constant.ConstHome.TODAY_SUGGESTION, 0);

        Cookie[] arr = request.getCookies();
        String txt = "";

        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        String id = request.getParameter("id");
        String[] ids = txt.split("-");
        String out = "";
        for (int i = 0; i < ids.length; i++) {
            String[] s = ids[i].split(":");
            if (!s[0].equals(id)) {
                if (out.isEmpty()) {
                    out = ids[i];
                } else {
                    out += "-" + ids[i];
                }
            }
        }
        if (!out.isEmpty()) {
            Cookie c = new Cookie("cart", out);
            c.setMaxAge(60 * 60 * 24);
            response.addCookie(c);
        }

        Cart cart = new Cart();
        cart.initializeCartFromText(out, listProduct);

        List<Item> listItem = cart.getItems();
        int amountItem;
        if (listItem != null) {
            amountItem = listItem.size();
        } else {
            amountItem = 0;
        }
        request.setAttribute("cart", cart);
        request.setAttribute("mount", amountItem);

//        HttpSession session = request.getSession();
//        session.setAttribute("cart", cart);
//        session.setAttribute("amount", amountItem);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
