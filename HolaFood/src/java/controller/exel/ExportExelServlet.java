/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.exel;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import dal.ExportExelDAO;
import dal.ExportExelDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import model.cart.Order;
import model.profile.Account;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ExportExelServlet", urlPatterns = {"/exportexel"})
public class ExportExelServlet extends HttpServlet {

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
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("acc");
        
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        
        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(dateStart);
            Date endDate = dateFormat.parse(dateEnd);
            
            if(startDate.compareTo(endDate)>0){
                request.setAttribute("ms", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
                request.getRequestDispatcher("managerOrder").forward(request, response);
                return;
            }
            List<Order> list = ExportExelDAO.gI().getOrderByAcc_Id(acc.getAccid(), dateStart, dateEnd);

            // Tạo workbook và sheet
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet workSheet = workbook.createSheet("1");

            // Tạo hàng và ô cell tiêu đề
            XSSFRow headerRow = workSheet.createRow(0);
            String[] columns = {"Order ID", "Account ID", "Nickname", "Phone", "Email", "Address", "Note", "Order Date", "Discount", "Total Price"};
            for (int i = 0; i < columns.length; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Điền dữ liệu vào các ô cell
            int rowNum = 1;
            for (Order order : list) {
                XSSFRow row = workSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(order.getOrder_id());
                row.createCell(1).setCellValue(order.getAcc_id());
                row.createCell(2).setCellValue(order.getUsername());
                row.createCell(3).setCellValue(order.getPhone());
                row.createCell(4).setCellValue(order.getEmail());
                row.createCell(5).setCellValue(order.getAddress());
                row.createCell(6).setCellValue(order.getNote());
                row.createCell(7).setCellValue(order.getOrder_date().toString());
                row.createCell(8).setCellValue(order.getDiscount());
                row.createCell(9).setCellValue(order.getTotal_price());
            }

            // Điều chỉnh kích thước của các cột
            for (int i = 0; i < columns.length; i++) {
                workSheet.autoSizeColumn(i);
            }
            
            String fileName = "orders" + Integer.toString(randomNum) + ".xlsx";

            // Ghi workbook vào file và đóng file
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();

            // Gửi file xuống client
            File excelFile = new File(fileName);
            FileInputStream inputStream = new FileInputStream(excelFile);
            response.setContentLength((int) excelFile.length());
            response.setHeader("Content-Disposition", "attachment; filename=" + excelFile.getName());
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        request.getRequestDispatcher("managerOrder").forward(request, response);

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
