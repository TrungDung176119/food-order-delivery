/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

import constant.ConstAccount;
import constant.ConstHome;
import constant.ConstOrder;
import dal.AccountDAO;
import dal.CartDAO;
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
import model.profile.AccountAddress;
import model.profile.AccountDetail;
import service.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "ShowCheckoutServlet", urlPatterns = {"/showcheckout"})
public class ShowCheckoutServlet extends HttpServlet {

    private static final String CHECKOUT_PAGE = "checkout.jsp";

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
        String url = "";

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
        ProductDAO pdao = new ProductDAO();

        //push product up to cart
        List<Product> listProduct = pdao.getAllProduct(ConstHome.TODAY_SUGGESTION);
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
        String action = request.getParameter("action");

        String delivery = request.getParameter("delivery");
        String type_checkout = request.getParameter("type_checkout");

        String logName = request.getParameter("logName");
        String logEmail = request.getParameter("logEmail");
        String logPhone = request.getParameter("logPhone");
        String logAddress = request.getParameter("logAddress");
        String logDetailAddr = request.getParameter("logDetailAddr");
        String logNote = request.getParameter("logNote");

        String ms = "";

        // Check type delivery and type ship. Push total money up to interface
        int ship = 0;
        try {
            if (delivery != null && delivery.equals("ship")) {
                ship = 40000;
            }
            int type = Integer.parseInt(type_checkout);
            if (type == 0) {
                request.setAttribute("checkMoney", cart.getTotalMoney() + ship);
            } else {
                request.setAttribute("checkMoney", cart.getTotalMoney() + ship - 15000);
            }
            request.setAttribute("type", type);
            request.setAttribute("delivery", delivery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ipdate account address take goods
        AccountDAO adao = new AccountDAO();
        AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
        List<AccountAddress> accAddress = adao.getAccAddressByid(acc.getAccid());
        if (accd != null) {
            request.setAttribute("accd", accd);
        }
        if (accAddress != null) {
            request.setAttribute("accAddress", accAddress);
        }

        // This part for check condition checkout
        CartDAO cd = new CartDAO();
        try {
            if (action.equals("checkout_now")) {
                if (accd == null || accAddress == null) {
                    ms = "Vui lòng cập nhật thông tin địa chỉ của bạn trước.";
                } else if (accd != null && accAddress != null) {
                    boolean addOrder = cd.addOrder(acc, cart, logName, logPhone, logEmail, logAddress, logNote,
                            ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT);
                    if (addOrder) {
                        Cookie c = new Cookie("cart", "");
                        c.setMaxAge(0);
                        response.addCookie(c);
                        ms = "Sản phẩm đã được đặt thành công.";
                    } else {
                        ms = "Có lỗi xảy ra.";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        ProductDAO pdao = new ProductDAO();

        //push product up to cart
        List<Product> listProduct = pdao.getAllProduct(ConstHome.TODAY_SUGGESTION);
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
        AccountDAO adao = new AccountDAO();
        AccountDetail accd = adao.getAccDetailByid(acc.getAccid());
        List<AccountAddress> accAddress = adao.getAccAddressByid(acc.getAccid());
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
                boolean isExistAcc = adao.isAccountExist(acc.getAccid(), ConstAccount.IS_ACCOUNT_DETAIL);
                if (isExistAcc) {
                    boolean update = adao.editProfile(ConstAccount.ACTION_UPDATE, acc.getAccid(), logName, "", logEmail, logPhone, 0, null);
                    if (update) {
                        ms = "Cập nhật hồ sơ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                } else {
                    boolean insert = adao.editProfile(ConstAccount.ACTION_INSERT, acc.getAccid(), logName, "", logEmail, logPhone, 0, null);
                    if (insert) {
                        ms = "Cập nhật hồ sơ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                }
                boolean isExistAdd = adao.isAccountExist(acc.getAccid(), ConstAccount.IS_ACCOUNT_ADDRESS);
                if (isExistAdd) {
                    boolean update = adao.editAddress(ConstAccount.ACTION_UPDATE, acc.getAccid(), logName, logPhone, logAddress, logNote, "");
                    if (update) {
                        ms = "Cập nhật địa chỉ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                } else {
                    boolean insert = adao.editAddress(ConstAccount.ACTION_INSERT, acc.getAccid(), logName, logPhone, logAddress, logNote, "");
                    if (insert) {
                        ms = "Cập nhật địa chỉ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                }
                url = CHECKOUT_PAGE;
            }

            // This part for check condition checkout
            CartDAO cd = new CartDAO();

            if (action.equals("checkout_now")) {
                if (accd == null || accAddress == null) {
                    ms = "Vui lòng cập nhật thông tin địa chỉ của bạn trước.";
                } else if (accd != null && accAddress != null) {
                    boolean addOrder = cd.addOrder(acc, cart, logName, logPhone, logEmail, logAddress, logNote,
                            ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT, ConstOrder.DEFAUT);
                    if (addOrder) {
                        Cookie c = new Cookie("cart", "");
                        c.setMaxAge(0);
                        response.addCookie(c);
                        ms = "Sản phẩm đã được đặt thành công.";
                    } else {
                        ms = "Có lỗi xảy ra.";
                    }
                }
            }

        } catch (Exception e) {
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
