/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager.seller;

import constant.ConstAccount;
import constant.ConstHome;
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
import java.util.ArrayList;
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
        String keyword = request.getParameter("keyword");
        String action = request.getParameter("action");
        String index_Page = request.getParameter("index");
        if (index_Page == null) {
            index_Page = "1";
        }

        if (type_page == null) {
            type_page = "2";
        }
        try {
            int type = Integer.parseInt(type_page);
            int page_index = Integer.parseInt(index_Page);
            int cnt_unactive = ManagerSellerDAO.gI().getAllSeller(ConstSeller.NON_ACTIVE, null, -1).size();
            int cnt_hidden = ManagerSellerDAO.gI().getAllSeller(ConstSeller.IS_HIDDEN, null, -1).size();
            int cnt_violate = ManagerSellerDAO.gI().getAllSeller(ConstSeller.IS_VIOLATE, null, -1).size();
            int cnt_active = ManagerSellerDAO.gI().getAllSeller(ConstSeller.IS_ACTIVE, null, -1).size();
            int cnt_deny = ManagerSellerDAO.gI().getAllSeller(ConstSeller.IS_DENY, null, -1).size();

            request.setAttribute("active", cnt_active);
            request.setAttribute("unactive", cnt_unactive);
            request.setAttribute("hidden", cnt_hidden);
            request.setAttribute("violate", cnt_violate);
            request.setAttribute("deny", cnt_deny);

            List<Seller> listSeller = null;
            if (action != null && action.equals("filter")) {
                listSeller = ManagerSellerDAO.gI().getAllSeller(type, keyword, -1);
            } else {
                listSeller = ManagerSellerDAO.gI().getAllSeller(type, null, -1);
            }
            int count = listSeller.size();
            int endPage = count / ConstHome.NUMBER_PAGINATION;
            if (endPage % ConstHome.NUMBER_PAGINATION != 0) {
                endPage++;
            }

            request.setAttribute("endP", endPage);
            request.setAttribute("tag", page_index);
            request.setAttribute("listSeller", listSeller);
            request.setAttribute("total_seller", count);
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
            if (action != null && (action.equals("Accept") || action.equals("Unhide"))) {
                boolean set = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.IS_ACTIVE, sellerid, "");
                boolean setRole = ManagerSellerDAO.gI().changeRole(ConstAccount.IS_SELLER, seller.getAcc_id());
                if (set && setRole) {
                    if (action.equals("Accept")) {
                        boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_NEW_SHOP_NOTIFICATION,
                                ConstNotify.MESSAGE_ACCEPT_SHOP_NOTIFICATION, 0, 0, "manager");
                    } else {
                        boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_NEW_SHOP_NOTIFICATION,
                                ConstNotify.MESSAGE_UNHIDE_SHOP_NOTIFICATION, 0, 0, "manager");
                    }

                    ms = "Thao tác đã được thực hiện.";
                } else {
                    ms = "Đã có lỗi xảy ra. Vui lòng thử lại sau.";
                }
            }

            if (action != null && action.equals("Gửi")) {
                String reason = request.getParameter("reason-deny");
                if (reason == null || reason.trim().isEmpty()) {
                    reason = ConstNotify.MESSAGE_DENY_SHOP_NOTIFICATION;
                }
                boolean set = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.IS_DENY, sellerid, reason);
                if (set) {
                    boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_NEW_SHOP_NOTIFICATION,
                            "Lý do: " + reason, 0, 0, "registerSeller");

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
                boolean set = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.IS_HIDDEN, sellerid, reason);
                if (set) {
                    boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_HIDDEN_SHOP_NOTIFICATION,
                            "Lý do: " + reason, 0, 0, "shoppage?sid=" + seller_id);
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
                boolean set = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.IS_VIOLATE, sellerid, reason);
                if (set) {
                    boolean notify = ServiceDAO.gI().addNewNotify(seller.getAcc_id(), ConstNotify.ICON_NEW_NOTIFICATION, ConstNotify.TITLE_WARNING_SHOP_NOTIFICATION,
                            "Lý do: " + reason, 0, 0, "shoppage?sid=" + seller_id);
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
