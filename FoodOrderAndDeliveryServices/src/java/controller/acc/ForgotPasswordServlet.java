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
import model.profile.Account;
import model.profile.AccountDetail;
import utils.Mail;
import utils.Validation;

/**
 *
 * @author admin
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forget_password"})
public class ForgotPasswordServlet extends HttpServlet {

    private final static String PAGE_FORGET_PASS = "forget_password.jsp";

    private static AccountDetail accDe= null;
    
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
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        String usermail = request.getParameter("email");
        String otp = request.getParameter("otp");

        String ms = "", url = "", msOtp = "";
         accDe = AccountDAO.gI().getAccDetailByEmail(usermail, 0);
        try {
            if (action == null) {
                url = PAGE_FORGET_PASS;
            }
            if (action.equals("submitEmail")) {
                if (accDe == null) {
                    ms = "Tài khoản email này không tồn tại.";
                } else {
                    String otpRand = Validation.getRandomNumber();
                    boolean sendMail = Mail.sendMail(usermail, "Reset password - HolaFood", "<div>Dear " + accDe.getNickname() + ",</div>\n"
                            + "<div>There was a request to change your password! </div>\n"
                            + "<div>If you did not make this request then please ignore this email.</div>\n"
                            + "<p>Otherwise, this is your OTP code:  " + otpRand + "</p> \n"
                            + "<div>Your OTP code just exist for 10 minutes!</div> \n"
                            + "<br/>\n"
                            + "<div>Sincerely, </div>"
                            + "<div>HolaFood Team </div>"
                            + "<h5 style=\"textalign: center;\">---------- HolaFood - Group 2----------</h5>");
                    if (sendMail) {
                        session.setAttribute("otpCode", otpRand);
                        session.setMaxInactiveInterval(600); // set time active of otp code (seconds)
                        ms = "Mã OTP đã được gửi về tài khoản của bạn.";
                        session.setAttribute("accDetail1", accDe);
                    } else {
                        ms = "Đã có lỗi xảy ra. Vui lòng thử lại sau.";
                    }
                }
                url = PAGE_FORGET_PASS;
            }
            if (action.equals("submitOTP")) {
                String otpCode = (String) session.getAttribute("otpCode");
                if (otp.trim().equals(otpCode)) {
                    url = "reset_password.jsp";
                } else {
                    msOtp = "Mã OTP nhập chưa chính xác.";
                    url = PAGE_FORGET_PASS;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("ms", ms);
        request.setAttribute("msOtp", msOtp);
        request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
