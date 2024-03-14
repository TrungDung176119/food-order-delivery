/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import constant.ConstAccount;
import constant.ConstHome;
import constant.ConstNotify;
import constant.ConstOrder;
import constant.ConstantPrice;
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
import java.util.List;
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
            if (type == 0) {
                TOTAL_MONEY = cart.getTotalMoney() + ship;
            } else {
                TOTAL_MONEY = cart.getTotalMoney() + ship - 15000;
            }
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
        List<AccountAddress> listA = AccountDAO.gI().getAllAccountAddress(acc.getAccid());
        if (listA != null) {
            request.setAttribute("listA", listA);
        }
        request.setAttribute("delivery", delivery);
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
        AccountAddress accAddress = AccountDAO.gI().getAccAddressByAddressid(null);
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

        try {
            if (action == null) {
                url = CHECKOUT_PAGE;
            }
            if (action.equals("update_address")) {
                AccountDetail existMail = AccountDAO.gI().getAccDetailByEmail(logEmail, acc.getAccid());
                if (existMail != null) {
                    ms = "Tài khoản email của bạn đã được người khác sử dụng.";
                    url = CHECKOUT_PAGE;
                } else {
                    boolean isExistAdd = AccountDAO.gI().isAccountExist(acc.getAccid(), ConstAccount.IS_ACCOUNT_ADDRESS);
                    if (isExistAdd) {
                        boolean update = AccountDAO.gI().manageAddress(ConstAccount.ACTION_UPDATE, acc.getAccid(), logName, logPhone, logAddress, logNote, "", null);
                        if (update) {
                            ms = "Cập nhật địa chỉ thành công.";
                        } else {
                            ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                        }
                    } else {
                        boolean insert = AccountDAO.gI().manageAddress(ConstAccount.ACTION_INSERT, acc.getAccid(), logName, logPhone, logAddress, logNote, "", null);
                        if (insert) {
                            ms = "Cập nhật địa chỉ thành công.";
                        } else {
                            ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                        }
                    }
                    url = CHECKOUT_PAGE;
                }
            }

            // This part for check condition checkout
            if (action.equals("checkout_now")) {
                String delivery = request.getParameter("type_delivery");
                int is_shipped = 0;
                if (delivery != null && delivery.equals("ship")) {
                    is_shipped = ConstantPrice.SHIP_PRICE;
                }
                if (cart.getTotalMoney() == 0) {
                    ms = "Giỏ hàng trống. Bạn chưa chọn sản phẩm.";
                } else if (accd == null || accAddress == null || accd.getNickname() == null || accd.getPhone() == null || accAddress.getAddress() == null) {
                    ms = "Vui lòng cập nhật đầy đủ thông tin địa chỉ của bạn.";
                } else if (accAddress.getNickname() != null && accd.getPhone() != null && accAddress.getAddress() != null) {
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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ms", ms);
        request.getRequestDispatcher(url).forward(request, response);

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
