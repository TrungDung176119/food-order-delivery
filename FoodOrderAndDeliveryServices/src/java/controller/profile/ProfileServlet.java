/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import constant.ConstAccount;
import dal.AccountDAO;
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
import java.sql.Date;
import java.util.List;
import model.cart.Item;
import model.product.Product;
import model.profile.Account;
import model.profile.AccountDetail;
import utils.Cart;
import utils.Validation;

/**
 *
 * @author Trung Dung
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
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

//        ProductDAO dao = new ProductDAO();
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
        Cart cart = new Cart();
        cart.initializeCartFromText(txt, listProduct);
        request.setAttribute("cart", cart);
        List<Item> listItem = cart.getItems();
        int amountItem;
        if (listItem != null) {
            amountItem = listItem.size();
        } else {
            amountItem = 0;
        }
        request.setAttribute("amount", amountItem);

//        AccountDAO adao = new AccountDAO();
        AccountDetail accd = AccountDAO.gI().getAccDetailByid(acc.getAccid());
        try {
            if (accd != null) {
                session.setAttribute("AccDetail", accd);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                response.sendRedirect("profile.jsp");
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
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

        String logName = request.getParameter("nickname");
        String logImage = request.getParameter("image");
        String logEmail = request.getParameter("email");
        String logPhone = request.getParameter("phone");
        String logGender = request.getParameter("gender");
        String dob_raw = request.getParameter("dob");

        String ms = "";
        AccountDetail accde = AccountDAO.gI().getAccDetailByEmail(logEmail, acc.getAccid());
        try {
            if (!Validation.isValidEmail(logEmail)) {
                ms = "Vui lòng nhập đúng định dạng email.";
            } else if (accde != null) {
                ms = "Email này đã được người khác sử dụng.";
            } else if (!Validation.isValidPhoneNumber(logPhone)) {
                ms = "Vui lòng nhập đúng định dạng số điện thoại.";
            } else {
                Date dob = (dob_raw == null || dob_raw.equals(""))
                        ? null : Date.valueOf(dob_raw);
                int gender = Integer.parseInt(logGender);

                boolean existAccD = AccountDAO.gI().isAccountExist(acc.getAccid(), ConstAccount.IS_ACCOUNT_DETAIL);
                if (existAccD) {
                    boolean update = AccountDAO.gI().editProfile(ConstAccount.ACTION_UPDATE, acc.getAccid(), logName.trim(), logImage, logEmail, logPhone, gender, dob);
                    if (update) {
                        ms = "Cập nhật hồ sơ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                } else {
                    boolean insert = AccountDAO.gI().editProfile(ConstAccount.ACTION_INSERT, acc.getAccid(), logName.trim(), logImage, logEmail, logPhone, gender, dob);
                    if (insert) {
                        ms = "Cập nhật hồ sơ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("msSuccess", ms);
            // Profile updated successfully
            request.getRequestDispatcher("profile.jsp").forward(request, response);
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
