/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import com.google.gson.Gson;
import constant.ConstHome;
import dal.ServiceDAO;
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
import model.services.Notify;
import utils.CacheManager;
import utils.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        // load accDetail
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        //list all product 
        List<Product> listProToday = ProductDAO.gI().getProductByType(ConstHome.TODAY_SUGGESTION, 0);
        List<Product> listProMostP = ProductDAO.gI().getProductByType(ConstHome.MOST_PURCHASED, 0);
        List<Product> listProHighRating = ProductDAO.gI().getProductByType(ConstHome.HIGH_RATING, 0);

//        request.setAttribute("listPToday", listProToday);
        request.setAttribute("listProMostP", listProMostP);
        request.setAttribute("listProHigh", listProHighRating);

        // filter pagination
        String index_Page = request.getParameter("index");
        if (index_Page == null) {
            index_Page = "1";
        }
        int index = Integer.parseInt(index_Page);
        int count = listProToday.size();
        int endPage = count / ConstHome.NUMBER_PAGINATION;
        if (endPage % ConstHome.NUMBER_PAGINATION != 0) {
            endPage++;
        }
        List<Product> listProByNum = ProductDAO.gI().getProductByType(ConstHome.NUMBER_PAGINATION, index);
        request.setAttribute("listPToday", listProByNum);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);

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

        // push notification of user on session
        if (acc != null) {
            List<Notify> listNotify = (List<Notify>) session.getAttribute("listNotify");
            if (listNotify == null) {
                listNotify = ServiceDAO.gI().getNotificationByAccID(acc.getAccid());
                session.setAttribute("listNotify", listNotify);
            }
            int cnt = 0;
            for (int i = 0; i < listNotify.size(); i++) {
                if (listNotify.get(i).getIs_read() == 0) {
                    cnt++;
                }
            }
            session.setAttribute("sumNotify", cnt);
//                // Lấy dữ liệu thông báo từ cơ sở dữ liệu
//                List<Notify> notifications = ServiceDAO.gI().getNotificationByAccID(acc.getAccid()); // Thay thế 1 bằng accid thực tế
//
//                // Chuyển đổi danh sách thông báo thành chuỗi JSON
//                String json = new Gson().toJson(notifications);
//
//                // Thiết lập kiểu nội dung và gửi chuỗi JSON về client
//                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(json);
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);

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
