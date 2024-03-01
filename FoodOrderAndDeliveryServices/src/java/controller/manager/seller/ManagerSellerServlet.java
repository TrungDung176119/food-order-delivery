/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager.seller;

import constant.ConstNotify;
import constant.ConstSeller;
import dal.AccountDAO;
import dal.ManagerSellerDAO;
import dal.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.profile.Seller;

/**
 *
 * @author admin
 */
@WebServlet(name = "ManagerSellerServlet", urlPatterns = {"/managerSeller"})
public class ManagerSellerServlet extends HttpServlet {

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
            out.println("<title>Servlet ManagerSellerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerSellerServlet at " + request.getContextPath() + "</h1>");
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
        String type_page = request.getParameter("is_active");
        if (type_page == null) {
            type_page = "2";
        }
        try {
            int type = Integer.parseInt(type_page);
            List<Seller> listSeller = ManagerSellerDAO.gI().getAllSeller(type);
            int cnt_unactive = ManagerSellerDAO.gI().getAllSeller(ConstSeller.NON_ACTIVE).size();
            int cnt_hidden = ManagerSellerDAO.gI().getAllSeller(ConstSeller.IS_HIDDEN).size();
            int cnt_violate = ManagerSellerDAO.gI().getAllSeller(ConstSeller.IS_VIOLATE).size();
            int cnt_active = ManagerSellerDAO.gI().getAllSeller(ConstSeller.IS_ACTIVE).size();

            request.setAttribute("active", cnt_active);
            request.setAttribute("unactive", cnt_unactive);
            request.setAttribute("hidden", cnt_hidden);
            request.setAttribute("violate", cnt_violate);

            request.setAttribute("listSeller", listSeller);
            request.setAttribute("tag_page", type);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("managerSeller.jsp").forward(request, response);

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
        String action = request.getParameter("action");
        String seller_id = request.getParameter("seller_id");
        String ms = "";
        try {
            int sellerid = Integer.parseInt(seller_id);
            Seller seller = ManagerSellerDAO.gI().getSellerBySeller_Id(sellerid);

            if (action != null && action.equals("Accept")) {
                boolean set = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.IS_ACTIVE, sellerid);
                if (set) {
                    boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_NEW_SHOP_NOTIFICATION,
                            ConstNotify.MESSAGE_ACCEPT_SHOP_NOTIFICATION, 0, 0, "manager");
                    ms = "Chập nhận thành công.";
                } else {
                    ms = "Đã có lỗi xảy ra. Vui lòng thử lại sau.";
                }
            }

            if (action != null && action.equals("Gửi")) {
                String reason = request.getParameter("reason-deny");
                if (reason == null || reason.trim().isEmpty()) {
                    reason = ConstNotify.MESSAGE_DENY_SHOP_NOTIFICATION;
                }
                boolean set = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.NON_ACTIVE, sellerid);
                if (set) {
                    boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_NEW_SHOP_NOTIFICATION,
                            reason, 0, 0, "registerSeller");
                    ms = "Thao tác đã được thực hiện.";
                } else {
                    ms = "Đã có lỗi xảy ra. Vui lòng thử lại sau.";
                }
            }

            if (action != null && action.equalsIgnoreCase("Xác nhận")) {
                String reason = request.getParameter("reason-hide");
                if (reason == null || reason.trim().isEmpty()) {
                    reason = ConstNotify.MESSAGE_HIDDEN_SHOP_NOTIFICATION;
                }
                boolean set = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.IS_HIDDEN, sellerid);
                if (set) {
                    boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_NEW_SHOP_NOTIFICATION,
                            reason, 0, 0, "shoppage?sid=" + seller_id);
                    ms = "Thao tác đã được thực hiện.";
                } else {
                    ms = "Đã có lỗi xảy ra. Vui lòng thử lại sau.";
                }
            }
            if (action != null && action.equalsIgnoreCase("Báo Xấu")) {
                String reason = request.getParameter("reason-report");
                if (reason == null || reason.trim().isEmpty()) {
                    reason = ConstNotify.MESSAGE_VIOLATE_SHOP_NOTIFICATION;
                }
                boolean set = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.IS_HIDDEN, sellerid);
                if (set) {
                    boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_WARNING_SHOP_NOTIFICATION,
                            reason, 0, 0, "shoppage?sid=" + seller_id);
                    ms = "Gửi báo xấu thành công.";
                } else {
                    ms = "Đã có lỗi xảy ra. Vui lòng thử lại sau.";
                }
            }
        } catch (NumberFormatException e) {
        }
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("managerSeller.jsp").forward(request, response);

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
