/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import constant.ConstHome;
import dal.CartDAO;
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
import java.util.ArrayList;
import java.util.List;
import model.cart.Item;
import model.product.Category;
import model.product.Product;
import model.profile.Account;
import utils.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "ShowCartServlet", urlPatterns = {"/showcart"})
public class ShowCartServlet extends HttpServlet {

    private final static String CART_PAGE = "cart.jsp";

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
            out.println("<title>Servlet ShowCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowCartServlet at " + request.getContextPath() + "</h1>");
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
        // Push amount of product up cart
        ProductDAO pdao = new ProductDAO();
        List<Product> listProduct = pdao.getProductByType(ConstHome.TODAY_SUGGESTION, 0);

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

        String action = request.getParameter("action");
        String num_raw = request.getParameter("number");
        String id_raw = request.getParameter("pid");
        String url = "";
        try {
            if (num_raw == null || id_raw == null) {
                url = CART_PAGE;
            }
            // this to increase or decrease amount of product in cart
            if (num_raw != null && id_raw != null) {
                int id = 0, num = 0;
                try {
                    id = Integer.parseInt(id_raw);
                    Product p = pdao.getProductByID(id);
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
                url = CART_PAGE;
            }
            if (action == null) {
                url = CART_PAGE;
            }
            // this to delete product out of cart
            if (action != null && action.equals("Xóa")) {
                String[] ids = txt.split("-");
                String out = "";
                if (ids.length >= 2) {
                    for (int i = 0; i < ids.length; i++) {
                        String[] s = ids[i].split(":");
                        if (!s[0].equals(id_raw)) {
                            if (out.isEmpty()) {
                                out = ids[i];
                            } else {
                                out += "-" + ids[i];
                            }
                        }
                    }
                } else if (ids.length <= 1) {
                    Cookie c = new Cookie("cart", out);
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
                if (!out.isEmpty()) {
                    Cookie c = new Cookie("cart", out);
                    c.setMaxAge(60 * 60 * 24);
                    response.addCookie(c);
                }
                cart = new Cart();
                cart.initializeCartFromText(out, listProduct);
                url = CART_PAGE;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int amountItem;
        if (cart.getItems() != null) {
            amountItem = cart.getItems().size();
        } else {
            amountItem = 0;
        }
        request.setAttribute("cart", cart);
        request.setAttribute("mount", amountItem);
        // set cart up session
//        HttpSession session = request.getSession();
//        session.setAttribute("cart", cart);
//        session.setAttribute("amount", amountItem);

        CartDAO cdao = new CartDAO();
        ServiceDAO sd = new ServiceDAO();

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        List<String> searchQueries = null;
        if (acc != null) {
            searchQueries = sd.getListSearchQueries(acc.getAccid());
        }
        List<Product> listSusggest = cdao.getRecommendedProducts(cart, searchQueries);
        request.setAttribute("listSusggest", listSusggest);
        request.getRequestDispatcher(url).forward(request, response);
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
