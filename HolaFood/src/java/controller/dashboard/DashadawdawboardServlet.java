/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.dashboard;

import java.io.IOException;
import java.util.List;
import dal.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DashadawdawboardServlet", urlPatterns = {"/dashboard-manager"})
public class DashadawdawboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get revenue data
        List<Integer> revenueData = OrderDAO.gI().getRevenueData();
        // Get order count data
        List<Integer> orderCountData = OrderDAO.gI().getOrderCountData();

        // Set attributes in request scope
        request.setAttribute("revenueData", revenueData);
        request.setAttribute("orderCountData", orderCountData);

        // Forward the request to the dashboard.jsp
        request.getRequestDispatcher("dashboard-manager.jsp").forward(request, response);
    }
}
