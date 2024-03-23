/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.vnpay;

import constant.ConstHome;
import constant.ConstNotify;
import constant.ConstOrder;
import dal.AccountDAO;
import dal.CartDAO;
import dal.PaymentDAO;
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
import static java.lang.System.out;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.cart.Item;
import model.product.Product;
import model.profile.Account;
import model.profile.AccountAddress;
import model.profile.AccountDetail;
import model.services.Notify;
import utils.Cart;

/**
 *
 * @author Dung
 */
@WebServlet(name = "ReturnInforVnPayServlet", urlPatterns = {"/vnpayreturn"})
public class ReturnInforVnPayServlet extends HttpServlet {

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
            out.println("<title>Servlet ReturnInforVnPayServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReturnInforVnPayServlet at " + request.getContextPath() + "</h1>");
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
        String ms = "";

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

        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);

        String paymentCode = request.getParameter("vnp_TxnRef");
        String amount = request.getParameter("vnp_Amount");
        String info = request.getParameter("vnp_OrderInfo");
        String errorCode = request.getParameter("vnp_ResponseCode");
        String vnpayQRCode = request.getParameter("vnp_TransactionNo");
        String bankCode = request.getParameter("vnp_BankCode");
        String payDate = request.getParameter("vnp_PayDate");
        String status = "";

        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                status = "Thành công";
            } else {
                status = "Không thành công";
            }

        } else {
            status = "Không xác định";
        }

        int is_shipped = 0;
        AccountDetail accd = AccountDAO.gI().getAccDetailByid(acc.getAccid());
        AccountAddress accAddress = AccountDAO.gI().getAccAddressByid(acc.getAccid());
        boolean addOrder = CartDAO.gI().addOrder(acc, cart, accAddress.getNickname(), accd.getPhone(), accd.getEmail(), accAddress.getAddress(), accAddress.getNote(),
                ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, is_shipped);
        boolean addPaymentHistory = PaymentDAO.gI().addPaymentHistory(acc, paymentCode, Integer.parseInt(amount), info, errorCode, vnpayQRCode, bankCode, payDate, status);
        if (addOrder && addPaymentHistory) {
            Cookie c = new Cookie("cart", "");
            c.setMaxAge(0);
            response.addCookie(c);
            ms = "Sản phẩm đã được đặt thành công.";
        } else {
            ms = "Có lỗi xảy ra.";
        }

        List<Integer> uniqueSellerIds = cart.getUniqueSellerIds();
        for (Integer us : uniqueSellerIds) {
            ServiceDAO.gI().addNewNotify(us, ConstNotify.ICON_NEW_ORDER, ConstNotify.TITLE_NEW_ORDER, ConstNotify.MESSAGE_NEW_ORDER, 0, 0, "managerOrder?type=1");
        }
        List<Notify> listNotify = (List<Notify>) session.getAttribute("listNotify");
        if (listNotify != null) {
            session.removeAttribute("listNotify");
            listNotify = ServiceDAO.gI().getNotificationByAccID(acc.getAccid());
        } else {
            listNotify = ServiceDAO.gI().getNotificationByAccID(acc.getAccid());
        }
        session.setAttribute("listNotify", listNotify);

        response.sendRedirect(request.getContextPath() + "/purchase?type=0");
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
