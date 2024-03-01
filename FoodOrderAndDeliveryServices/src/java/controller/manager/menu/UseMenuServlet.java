/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager.menu;

import dal.ManagerOrderDAO;
import dal.ManagerMenuDAO;
import dal.ManagerSellerDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.menu.Menu;
import model.product.Product;
import model.profile.Account;

/**
 *
 * @author anhdu
 */
@WebServlet(name = "UseMenuServlet", urlPatterns = {"/usemenu"})
public class UseMenuServlet extends HttpServlet {

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
            out.println("<title>Servlet UseMenuServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UseMenuServlet at " + request.getContextPath() + "</h1>");
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
        Account acc_raw = (Account) session.getAttribute("acc");
        String m_id_raw = request.getParameter("m_id");
        int m_id;
        int s_id = ManagerSellerDAO.gI().getSellerByAccId(acc_raw.getAccid()).getSeller_id();
        if (m_id_raw == null) {
            m_id = ManagerMenuDAO.gI().getMenuIsShow(s_id).getMenuId();
        } else {
            m_id = Integer.parseInt(m_id_raw);
        }
        ManagerMenuDAO.gI().updateMenuStatus(m_id, s_id);
        int m_id_isShow = ManagerMenuDAO.gI().getMenuIsShow(s_id).getMenuId();
        List<Menu> listm = ManagerMenuDAO.gI().getAllMenuOfSeller(s_id);
        List<Product> listp = ManagerMenuDAO.gI().getProductFromMenuId(m_id);
        request.setAttribute("m_id_show", m_id_isShow);
        request.setAttribute("m_id", m_id);
        request.setAttribute("listPro", listp);
        request.setAttribute("listMenu", listm);
        request.setAttribute("sum", listp.size());
        response.sendRedirect("managerMenu?m_id="+m_id);
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
