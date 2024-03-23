/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.ConstAccount;
import constant.ConstHome;
import constant.ConstNotify;
import constant.ConstOrder;
import constant.ConstantPrice;
import controller.vnpay.Config;
import dal.AccountDAO;
import dal.CartDAO;
import dal.ProductDAO;
import dal.ServiceDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import model.cart.Item;
import model.product.Product;
import model.profile.Account;
import model.profile.AccountAddress;
import model.profile.AccountDetail;
import model.services.Notify;
import utils.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "ShowCheckoutServlet", urlPatterns = {"/showcheckout"})
public class ShowCheckoutServlet extends HttpServlet {

    private static final String CHECKOUT_PAGE = "checkout.jsp";
    private static int TOTAL_MONEY = 0;

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
        String returnUrl = request.getHeader("referer"); // Lấy địa chỉ URL trước đó
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

        // check acc on session is exist or null
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
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

        // This part for checkout
        String delivery = request.getParameter("delivery");
        String type_checkout = request.getParameter("type_checkout");

        String ms = "";

        // Check type delivery and type ship. Push total money up to interface
        int number = cart.getNumberStore();
        int ship = 0;
        try {
            if (delivery != null && delivery.equals("ship")) {
                ship = ConstantPrice.SHIP_PRICE * number;
            }
            int type = Integer.parseInt(type_checkout);

            TOTAL_MONEY = cart.getTotalMoney() + ship;

            if (TOTAL_MONEY < 0) {
                TOTAL_MONEY = 0;
            }
            request.setAttribute("checkMoney", TOTAL_MONEY);
            request.setAttribute("type", type);
            request.setAttribute("delivery", delivery);
            request.setAttribute("ship", ship);
            request.setAttribute("number", number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Ipdate account address take goods
        AccountDAO adao = new AccountDAO();
        AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
        AccountAddress accAddress = adao.getAccAddressByid(acc.getAccid());
        if (accd != null) {
            request.setAttribute("accd", accd);
        }
        if (accAddress != null) {
            request.setAttribute("accAddress", accAddress);
        }
        request.setAttribute("delivery", delivery);
        request.setAttribute("checkout", type_checkout);
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);

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

        String url = "";
        // Ipdate account address take goods
        AccountDetail accd = AccountDAO.gI().getAccDetailByid(acc.getAccid());
        AccountAddress accAddress = AccountDAO.gI().getAccAddressByid(acc.getAccid());
        if (accd != null) {
            request.setAttribute("accd", accd);
        }
        if (accAddress != null) {
            request.setAttribute("accAddress", accAddress);
        }

        String action = request.getParameter("action");
        String logName = request.getParameter("logName");
        String logEmail = request.getParameter("logEmail");
        String logPhone = request.getParameter("logPhone");
        String logAddress = request.getParameter("logAddress");
        String logDetailAddr = request.getParameter("logDetailAddr");
        String logNote = request.getParameter("logNote");
        String ms = "";

        if (action == null) {
            url = CHECKOUT_PAGE;
        }
        if (action.equals("update_address")) {
            AccountDetail existMail = AccountDAO.gI().getAccDetailByEmail(logEmail, acc.getAccid());
            if (existMail != null) {
                ms = "Tài khoản email của bạn đã được người khác sử dụng.";
                url = CHECKOUT_PAGE;
            } else {
                boolean isExistAcc = AccountDAO.gI().isAccountExist(acc.getAccid(), ConstAccount.IS_ACCOUNT_DETAIL);
                if (isExistAcc) {
                    boolean update = AccountDAO.gI().editProfile(ConstAccount.ACTION_UPDATE, acc.getAccid(), logName, "", logEmail, logPhone, 0, null);
                    if (update) {
                        ms = "Cập nhật hồ sơ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                } else {
                    boolean insert = AccountDAO.gI().editProfile(ConstAccount.ACTION_INSERT, acc.getAccid(), logName, "", logEmail, logPhone, 0, null);
                    if (insert) {
                        ms = "Cập nhật hồ sơ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                }
                boolean isExistAdd = AccountDAO.gI().isAccountExist(acc.getAccid(), ConstAccount.IS_ACCOUNT_ADDRESS);
                if (isExistAdd) {
                    boolean update = AccountDAO.gI().editAddress(ConstAccount.ACTION_UPDATE, acc.getAccid(), logName, logPhone, logAddress, logNote, "");
                    if (update) {
                        ms = "Cập nhật địa chỉ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                } else {
                    boolean insert = AccountDAO.gI().editAddress(ConstAccount.ACTION_INSERT, acc.getAccid(), logName, logPhone, logAddress, logNote, "");
                    if (insert) {
                        ms = "Cập nhật địa chỉ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                }
                url = CHECKOUT_PAGE;
            }
        }

        String delivery = request.getParameter("type_delivery");
        String type_checkout = request.getParameter("type_checkout");
        int is_shipped = 0;
        if (delivery != null && delivery.equals("ship")) {
            is_shipped = ConstantPrice.SHIP_PRICE;
        }
        if (cart.getTotalMoney() == 0) {
            ms = "Giỏ hàng trống. Bạn chưa chọn sản phẩm.";
        } else if (accd == null || accAddress == null || accd.getNickname() == null || accd.getPhone() == null || accAddress.getAddress() == null) {
            ms = "Vui lòng cập nhật đầy đủ thông tin địa chỉ của bạn.";
        } else if (accAddress.getNickname() != null && accd.getPhone() != null && accAddress.getAddress() != null) {
            if ("checkout_now".equals(action) && Integer.parseInt(type_checkout) == 1) {
                String vnp_Version = "2.1.0";
                String vnp_Command = "pay";
                String orderType = "other";
                long amount = cart.getTotalMoney() * 100;

                String vnp_TxnRef = Config.getRandomNumber(8);
                String vnp_IpAddr = Config.getIpAddress(request);

                String vnp_TmnCode = Config.vnp_TmnCode;

                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", vnp_Version);
                vnp_Params.put("vnp_Command", vnp_Command);
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_CurrCode", "VND");
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
                vnp_Params.put("vnp_OrderType", orderType);
                vnp_Params.put("vnp_Locale", "vn");
                vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
                vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String vnp_CreateDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                cld.add(Calendar.MINUTE, 15);
                String vnp_ExpireDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vnp_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        //Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        if (itr.hasNext()) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }
                String queryUrl = query.toString();
                String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
                queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
                response.sendRedirect(paymentUrl);
            } else {
                boolean addOrder = CartDAO.gI().addOrder(acc, cart, accAddress.getNickname(), accd.getPhone(), accd.getEmail(), accAddress.getAddress(), accAddress.getNote(),
                        ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, is_shipped);
                if (addOrder) {
                    Cookie c = new Cookie("cart", "");
                    c.setMaxAge(0);
                    response.addCookie(c);
                    ms = "Sản phẩm đã được đặt thành công.";
                } else {
                    ms = "Có lỗi xảy ra.";
                }

                //set new order notification for se
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
                url = CHECKOUT_PAGE;

                request.setAttribute("ms", ms);
                request.getRequestDispatcher(url).forward(request, response);
            }
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
