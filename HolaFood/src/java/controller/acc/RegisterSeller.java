/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.acc;

import constant.ConstAccount;
import constant.ConstSeller;
import dal.AccountDAO;
import dal.ManagerSellerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.profile.Account;
import model.profile.Seller;
import utils.Mail;
import utils.Validation;

/**
 *
 * @author truon
 */
@WebServlet(name = "RegisterSeller", urlPatterns = {"/registerseller"})
public class RegisterSeller extends HttpServlet {

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
            out.println("<title>Servlet RegisterSeller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterSeller at " + request.getContextPath() + "</h1>");
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
        AccountDAO accDAO = new AccountDAO();

        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Seller seller = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid());
        if (seller == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "null seller");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (seller.getIs_active() == ConstSeller.DEFALT && acc.getRoleid() == ConstAccount.ROLE_CUSTOMER) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Đang đợi Admin duyệt đơn");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (acc.getRoleid() == ConstAccount.IS_SELLER) {
            response.sendRedirect("managerOrder");
        } else if (acc.getRoleid() == ConstAccount.ROLE_CUSTOMER || seller.getIs_active() == ConstSeller.IS_DENY) {
            request.getRequestDispatcher("registerSeller.jsp").forward(request, response);
        } else if (acc.getRoleid() == ConstAccount.IS_ADMIN) {
            request.getRequestDispatcher("managerSeller").forward(request, response);
        } else if ( seller.getIs_active() != ConstSeller.IS_ACTIVE) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", seller.getNote());
            request.getRequestDispatcher("login.jsp").forward(request, response);
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

        String storeName = request.getParameter("store_name");
        String address = request.getParameter("address_store");
        String email = request.getParameter("email_store");
        String phoneNumber = request.getParameter("phone_store");
        String action = request.getParameter("action");
        String rule = request.getParameter("rule");

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String ms = "";
        String confirmationCode = null;
        if (action != null && action.equals("Đăng Kí")) {
            if (storeName == null || storeName.length() > 30 || storeName.length() < 5) {
                request.setAttribute("msName", "Độ dài tên Shop phải nằm trong khoảng 5-30 ký tự.");
            } else if (email == null || !Validation.isValidEmail(email)) {
                request.setAttribute("msEmail", "Vui lòng nhập đúng định dạng email.");
            } else if (address == null || address.length() < 0 || address.length() > 80) {
                request.setAttribute("msAddress", "Địa chỉ phải có độ dài từ 10-80 kí tự.");
            } else if (phoneNumber == null || !Validation.isValidPhoneNumber(phoneNumber)) {
                request.setAttribute("msPhone", "Vui lòng nhập đúng định dạng số điện thoại");
            } else {
                confirmationCode = Validation.getRandomNumber();

                session.setAttribute("acc_id", acc.getAccid());
                session.setAttribute("store_name", storeName);
                session.setAttribute("address_store", address);
                session.setAttribute("email_store", email);
                session.setAttribute("phone_store", phoneNumber);

                boolean sendMail = Mail.sendMail(email, "Confirm Password Reset - HolaFood", "<div>Dear " + acc.getUsername() + ",</div>\n"
                        + "<div>There is a requirement to register your Account to become a Seller </div>\n"
                        + "<div>If you did not make this request then please ignore this email.</div>\n"
                        + "<p>Otherwise, this is your OTP code:  " + confirmationCode + "</p> \n"
                        + "<div>Your OTP code just exist for 10 minutes!</div> \n"
                        + "<br/>\n"
                        + "<div>Sincerely, </div>"
                        + "<div>HolaFood Team </div>"
                        + "<h5 style=\"textalign: center;\">------------------- HolaFood - Group 2--------------------</h5>");
                if (sendMail) {
                    session.setAttribute("otpRegisterSeller", confirmationCode);
                    session.setMaxInactiveInterval(600); // set time active of otp code (seconds)
                    ms = "Mã OTP đã được gửi về tài khoản của bạn.";
                } else {
                    ms = "Đã có lỗi xảy ra. Vui lòng không ấn spam nút đăng kí.";
                }
            }
        }
        if (action != null && action.equals("Xác Nhận")) {
            String otpcode = request.getParameter("otpCode");
            String otpRegister = (String) session.getAttribute("otpRegisterSeller");
            if (otpRegister != null && otpcode.trim().equals(otpRegister)) {
                AccountDAO dao = new AccountDAO();
                boolean register = dao.Seller(acc.getAccid(), storeName, email, address, phoneNumber);
                if (register) {
                    session.removeAttribute("otpRegisterSeller");
                    session.removeAttribute("acc_id");
                    session.removeAttribute("store_name");
                    session.removeAttribute("address_store");
                    session.removeAttribute("email_store");
                    session.removeAttribute("phone_store");

                    Seller seller = ManagerSellerDAO.gI().getSellerByAccId(acc.getAccid(), ConstSeller.IS_ACTIVE);
                    boolean setActive = ManagerSellerDAO.gI().setActiveSeller(ConstSeller.DEFALT, seller.getSeller_id(), "");

                    request.setAttribute("ms", "message");
                    request.setAttribute("message", "Form đã được gửi đến Admin. Tài khoản sẽ được phê duyệt sau 48h.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                } else {
                    ms = "Đã có lỗi xảy ra. Vui lòng thử lại sau.";
                }
            } else if (!otpcode.equals(otpRegister)) {
                ms = "Mã OTP chưa chính xác.";
            } else if (otpRegister == null) {
                ms = "Mã OTP đã hết hiệu lực. Vui lòng đăng ký lại";
            }
        }
        request.setAttribute("ms", ms);
        request.getRequestDispatcher("registerSeller.jsp").forward(request, response);
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
