/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.acc;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.profile.AccountDetail;
import utils.Validation;

/**
 *
 * @author admin
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/reset_password"})
public class ResetPasswordServlet extends HttpServlet {

    private final static String PAGE_RESET_PASS = "reset_password.jsp";
    private final static String RETURN_PAGE = "reset_password?email=";

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
        request.getRequestDispatcher("reset_password.jsp").forward(request, response);

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
        AccountDetail accd = (AccountDetail) session.getAttribute("accDetail1");
//        AccountDAO dao = new AccountDAO();

        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");

        String ms = "";
        String msRe = "";

        try {
            boolean checkPassword = Validation.isValidPassword(pass);
            if (!checkPassword) {
                ms = "Mật khẩu mới nhập chưa đúng yêu cầu.";
            } else if (!repass.equals(pass)) {
                msRe = "Mật khẩu nhập lại chưa chính xác.";
            } else {
                boolean change = AccountDAO.gI().changePassword(accd.getAccid(), repass);
                if (change) {
                    msRe = "Bạn đã đổi mật khẩu thành công.";
                    session.removeAttribute("otpCode");
                    session.removeAttribute("accDetail1");
                } else {
                    msRe = "Có lỗi xảy ra. Vui lòng thử lại sau.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("ms", ms);
        request.setAttribute("msRe", msRe);

        request.getRequestDispatcher("reset_password.jsp").forward(request, response);
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
