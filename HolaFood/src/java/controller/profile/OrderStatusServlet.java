/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import constant.ConstOrder;
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
import java.util.ArrayList;
import java.util.List;
import model.cart.Item;
import model.cart.Order;
import model.cart.OrderStatus;
import model.product.Product;
import model.profile.Account;
import utils.Cart;
import utils.Validation;

/**
 *
 * @author admin
 */
@WebServlet(name = "OrderStatusServlet", urlPatterns = {"/purchase"})
public class OrderStatusServlet extends HttpServlet {

    private static List<OrderStatus> listOrder = new ArrayList<>();

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
            out.println("<title>Servlet OrderStatusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderStatusServlet at " + request.getContextPath() + "</h1>");
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
        }
        //push product onto cart
        List<Product> listProduct = ProductDAO.gI().getProductByType(constant.ConstHome.TODAY_SUGGESTION, 0);
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart.gI().initializeCartFromText(txt, listProduct);
        request.setAttribute("cart", Cart.gI());
        List<Item> listItem = Cart.gI().getItems();
        int amountItem;
        if (listItem != null) {
            amountItem = listItem.size();
        } else {
            amountItem = 0;
        }
        request.setAttribute("amount", amountItem);

        String action = request.getParameter("action");
        String pid = request.getParameter("pid");
        String order_id = request.getParameter("order_id");
        String detail_id = request.getParameter("detailid");
        String type_order = request.getParameter("type");
        String keyword = request.getParameter("keyword");

        Product p = new Product();
        if (pid != null) {
            p = ProductDAO.gI().getProductByID(pid);
        }
        String ms = "";

        if (type_order == null) {
            type_order = "0";
        }
        if (order_id == null) {          // set value to avoid null
            order_id = "0";
        }
        int type = 0;
        try {
            type = Integer.parseInt(type_order);
            if (action != null && action.equals("received")) {
                boolean set = OrderDAO.gI().setStatusOrder(order_id, ConstOrder.IS_DELIVERED);        // update customer was recieved goods
                boolean setStatus = OrderDAO.gI().setStatusOrder(order_id, ConstOrder.IS_PURCHASED);  // ....  was purchased
                if (set && setStatus) {
                    ms = " <span>Đây là mã nhận hàng của bạn: <span style=\"color: var(--primary-color);\">#" + order_id + "</span></span>\n"
                            + "<span>Vui lòng gửi thêm tiền Ship (nếu có).</span>";
                } else {
                    ms = "Có lỗi xảy ra.";
                }
            }
            if (action != null && action.equals("cancel")) {
                boolean set = OrderDAO.gI().setStatusOrder(order_id, ConstOrder.IS_CANCELED);
                if (set) {
                    ms = " <span>Đơn hàng số <span style=\"color: var(--primary-color);\">#" + order_id + "</span> đã được hủy thành công.</span>\n";
                } else {
                    ms = "Có lỗi xảy ra.";
                }
            }
            if (action != null && action.equals("reupdate")) {
                Order order = OrderDAO.gI().getOrderById(order_id);
                if (order != null) {
                    request.setAttribute("order", order);
                }
            }

            if (action != null && action.equals("undelivered")) {
                ms = " <span>Vui lòng chờ người bán giao hàng! </span>\n";
            }
            if (action != null && action.equals("filter")) {
                listOrder = OrderDAO.gI().getAllOrders(acc.getAccid(), 0, keyword);
            } else {
                listOrder = OrderDAO.gI().getAllOrders(acc.getAccid(), type, keyword);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("listOrder", listOrder);
            request.setAttribute("p", p);
            request.setAttribute("ms", ms);
            request.setAttribute("detailid", detail_id);
            request.setAttribute("order_id", order_id);
            request.setAttribute("tag", type);
            request.getRequestDispatcher("orders.jsp").forward(request, response);
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
        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String action = request.getParameter("action");
        String ms = "";

        if (action != null && action.equals("feedback")) {
            String pid_raw = request.getParameter("proid");
            String selectedRating_raw = request.getParameter("selectedRating");
            String describe = request.getParameter("describe");
            String delivery = request.getParameter("delivery");
            String standard = request.getParameter("standard");
            String content = request.getParameter("content");
            String image = request.getParameter("image");
            String display_raw = request.getParameter("incognito");
            String detailid = request.getParameter("detailid");
            String cname = request.getParameter("cname");
            String sellerid = request.getParameter("sellerid_feed");

            try {
                int pid = Integer.parseInt(pid_raw);
                int rating = Integer.parseInt(selectedRating_raw);
                int display = Integer.parseInt(display_raw);

                boolean feedback = OrderDAO.gI().feedbackByUser(acc.getAccid(), cname, pid, rating, describe, delivery, standard, content, image, display, 1, acc.getUsername());
                if (feedback) {
                    boolean setStatus = OrderDAO.gI().setStatusOrder(detailid, ConstOrder.IS_FEEDBACKED);
                    boolean setRate = ProductDAO.gI().setProductRating(pid, Integer.parseInt(sellerid));
                    ms = "Bạn đã gửi đánh giá thành công.";
                } else {
                    ms = "Đánh giá không thành công.";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (action != null && action.equals("address")) {
            String order_id = request.getParameter("oid_update");
            String name = request.getParameter("logName");
            String phone = request.getParameter("logPhone");
            String address = request.getParameter("logAddress");
            String note = request.getParameter("logNote");

            try {
                if (name.length() > 31) {
                    ms = "Độ dài tên chỉ nằm trong khoảng 0-31 kí tự";
                } else if (!Validation.isValidPhoneNumber(phone)) {
                    ms = "Vui lòng nhập đúng định dạng số điện thoại.";
                } else {
                    int orderid = Integer.parseInt(order_id);
                    boolean update = OrderDAO.gI().updateOrderAddress(orderid, name, phone, address, note);
                    if (update) {
                        ms = "Cập nhật địa chỉ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Không thể cập nhật địa chỉ.";
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
        request.setAttribute("ms_feedback", ms);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
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
