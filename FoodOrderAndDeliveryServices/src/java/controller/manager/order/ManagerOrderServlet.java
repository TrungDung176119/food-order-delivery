/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager.order;

import constant.ConstNotify;
import constant.ConstOrder;
import constant.ConstantPrice;
import dal.ManagerOrderDAO;
import dal.ManagerSellerDAO;
import dal.ServiceDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.List;
import model.cart.Order;
import model.cart.OrderStatus;
import model.cart.ViewOrderDetail;
import model.product.Product;
import model.profile.Account;
import model.services.Notify;
import model.profile.Seller;

/**
 *
 * @author admin
 */
@WebServlet(name = "ManagerOrderServlet", urlPatterns = {"/managerOrder"})
public class ManagerOrderServlet extends HttpServlet {

    private static List<Order> listOrder = null;

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
            out.println("<title>Servlet ManagerOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerOrderServlet at " + request.getContextPath() + "</h1>");
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
        } else if (acc.getRoleid() != 2) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Tài khoản của bạn không được phép vào trang này.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String type_raw = request.getParameter("type");
        String text = request.getParameter("text");
        String select_raw = request.getParameter("select");
        if (select_raw == null || select_raw.isEmpty()) {
            select_raw = "1";
        }
        if (type_raw == null) {
            type_raw = "0";
        }
        int type = 0, select = 0, sizeOfList = 0;

        Seller s = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid());
        try {
            type = Integer.parseInt(type_raw);
            select = Integer.parseInt(select_raw);
            listOrder = ManagerOrderDAO.gI().getOrdersBySellerId(type_raw, text, select, s.getSeller_id());
            if (listOrder != null) {
                sizeOfList = listOrder.size();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // view order detail 
        String orderid = request.getParameter("orderid");
        request.setAttribute("oid", orderid);

        try {
            List<ViewOrderDetail> listView = ManagerOrderDAO.gI().viewOrderDetailByOid(orderid, s.getSeller_id());
            int totalprice = 0;
            for (ViewOrderDetail view : listView) {
                totalprice += view.getCurrent_price() * view.getQuantity();
            }
            request.setAttribute("totalprice", totalprice);
            request.setAttribute("listView", listView);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        request.setAttribute("size", sizeOfList);
        request.setAttribute("listO", listOrder);
        request.setAttribute("tag", type);
        request.getRequestDispatcher("managerOrder.jsp").forward(request, response);
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

        String accid = request.getParameter("accid");
        String order_id = request.getParameter("oid");
        String action = request.getParameter("action");

        int acc_id = 0, proid = 0, type_ship = 0;
        String ms = "", pro_image = "";
        try {
            acc_id = Integer.parseInt(accid);
            List<OrderStatus> listOrderStatus = OrderDAO.gI().getAllOrders(acc_id, 0, order_id);

            if (!listOrderStatus.isEmpty()) {
                proid = listOrderStatus.get(0).getPid();
            }
            Product product = ProductDAO.gI().getProductByID(proid);
            if (product != null) {
                pro_image = product.getImage();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //this for create notification
        Seller s = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid());

        if (action.equals("Nhận Đơn")) {
            type_ship = ManagerOrderDAO.gI().viewOrderDetailByOid(order_id, s.getSeller_id()).get(0).getIs_shipped();
            boolean set = OrderDAO.gI().setStatusOrder(order_id, ConstOrder.IS_ACCEPTED);
            if (set) {
                if (type_ship == 1) {
                    boolean notify = ServiceDAO.gI().addNewNotify(Integer.parseInt(accid), pro_image, ConstNotify.TITLE_ACCEPTED_ORDER, ConstNotify.MESSAGE_ACCEPTED_ORDER_SHIP, 0, 0, "purchase?type=2");
                } else {
                    boolean notify = ServiceDAO.gI().addNewNotify(Integer.parseInt(accid), pro_image, ConstNotify.TITLE_ACCEPTED_ORDER, ConstNotify.MESSAGE_ACCEPTED_ORDER_NOSHIP, 0, 0, "purchase?type=2");
                }
                ms = "Đơn hàng đã được xác nhận.";
            } else {
                ms = "Đã có lỗi xảy ra. Không thể xác nhận đơn hàng.";
            }
        }
        if (action.equals("Giao Hàng")) {
            type_ship = ManagerOrderDAO.gI().viewOrderDetailByOid(order_id, s.getSeller_id()).get(0).getIs_shipped();
            boolean set = OrderDAO.gI().setStatusOrder(order_id, ConstOrder.IS_DELIVERING);
            if (set) {
                if (type_ship == 1) {
                    boolean notify = ServiceDAO.gI().addNewNotify(Integer.parseInt(accid), pro_image, ConstNotify.TITLE_DELIVERING_ORDER_SHIP, ConstNotify.MESSAGE_DELIVERING_ORDER_SHIP, 0, 0, "purchase?type=2");
                } else {
                    boolean notify = ServiceDAO.gI().addNewNotify(Integer.parseInt(accid), pro_image, ConstNotify.TITLE_DELIVERING_ORDER_NOSHIP, ConstNotify.MESSAGE_DELIVERING_ORDER_NOSHIP, 0, 0, "purchase?type=2");
                }
                ms = "Đơn hàng đã được xác nhận giao.";
            } else {
                ms = "Đã có lỗi xảy ra. Không thể xác nhận đơn hàng.";
            }
        }
        if (action.equals("Gửi")) {
            String ms_cancel = request.getParameter("reason-cancel");
            if (ms_cancel == null || ms_cancel.trim().isEmpty()) {
                ms_cancel = ConstNotify.MESSAGE_FORGOT_WRITE_REASON;
            }
            boolean set = OrderDAO.gI().setStatusOrder(order_id, ConstOrder.IS_NO_ACCEPTED_BY_SELLER);
            if (set) {
                boolean notify = ServiceDAO.gI().addNewNotify(Integer.parseInt(accid), pro_image, ConstNotify.TITLE_CANCEL_ORDER, "Lý do: " + ms_cancel, 0, 0, "purchase?type=5");
                ms = "Đơn hàng đã bị hủy bởi bạn.";
            } else {
                ms = "Đã có lỗi xảy ra. Không thể hủy đơn.";
            }
        }
        if (action.equals("accept-all")) {
            boolean deliver = ManagerOrderDAO.gI().deliveryAllOrder(acc.getAccid());
            if (deliver) {
                ms = "Tất cả đơn hàng đang ở trạng thái giao hàng";
            } else {
                ms = "Đã có lỗi xảy ra. Không thể giao hàng.";
            }
        }

        String type_raw = request.getParameter("type");
        int sizeOfList = 0;
        listOrder = ManagerOrderDAO.gI().getOrdersBySellerId(type_raw, "", 1, s.getSeller_id());
        if (listOrder != null) {
            sizeOfList = listOrder.size();
        }

        List<Notify> listNotify = (List<Notify>) session.getAttribute("listNotify");
        if (listNotify != null) {
            session.removeAttribute("listNotify");
            listNotify = ServiceDAO.gI().getNotificationByAccID(acc.getAccid());
        } else {
            listNotify = ServiceDAO.gI().getNotificationByAccID(acc.getAccid());
        }
        session.setAttribute("listNotify", listNotify);

        request.setAttribute("size", sizeOfList);
        request.setAttribute("listO", listOrder);
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("managerOrder.jsp").forward(request, response);
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
