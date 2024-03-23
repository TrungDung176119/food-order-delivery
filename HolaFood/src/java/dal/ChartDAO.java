/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.chart.ChartByCategory;
import model.chart.ChartByFeedback;
import model.chart.ChartByMonth;

/**
 *
 * @author admin
 */
public class ChartDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ChartDAO ins;

    public static ChartDAO gI() {
        if (ins == null) {
            ins = new ChartDAO();
        }
        return ins;
    }

    public ChartByMonth getMothTotalInvoice(int s_id) {
        String sql = "WITH MonthlyRevenue AS (SELECT \n"
                + "	  MONTH(o.order_date) AS Month,\n"
                + "	  SUM(od.quantity * od.product_currentprice) AS TotalRevenue\n"
                + "      FROM product p\n"
                + "      LEFT JOIN OrderDetail od ON p.pid = od.product_id\n"
                + "      LEFT JOIN Orders o ON o.order_id = od.order_id\n"
                + "      WHERE o.order_date >= DATEADD(MONTH, -12, GETDATE()) \n"
                + "      and p.seller_id = ?\n"
                + "       GROUP BY MONTH(o.order_date))\n"
                + "      SELECT\n"
                + "      COALESCE(MAX(CASE WHEN Month = 1 THEN TotalRevenue END), 0) AS Month1,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 2 THEN TotalRevenue END), 0) AS Month2,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 3 THEN TotalRevenue END), 0) AS Month3,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 4 THEN TotalRevenue END), 0) AS Month4,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 5 THEN TotalRevenue END), 0) AS Month5,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 6 THEN TotalRevenue END), 0) AS Month6,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 7 THEN TotalRevenue END), 0) AS Month7,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 8 THEN TotalRevenue END), 0) AS Month8,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 9 THEN TotalRevenue END), 0) AS Month9,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 10 THEN TotalRevenue END), 0) AS Month10,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 11 THEN TotalRevenue END), 0) AS Month11,\n"
                + "      COALESCE(MAX(CASE WHEN Month = 12 THEN TotalRevenue END), 0) AS Month12\n"
                + "     FROM MonthlyRevenue";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, s_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ChartByMonth m = new ChartByMonth(
                        rs.getInt("Month1"), rs.getInt("Month2"),
                        rs.getInt("Month3"), rs.getInt("Month4"),
                        rs.getInt("Month5"), rs.getInt("Month6"),
                        rs.getInt("Month7"), rs.getInt("Month8"),
                        rs.getInt("Month9"), rs.getInt("Month10"),
                        rs.getInt("Month11"), rs.getInt("Month12"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChartByCategory getCategoryTotalInvoice(int s_id) {
        String sql = "SELECT\n"
                + "SUM(CASE WHEN p.category_id = 1 THEN i.quantity * i.product_currentprice ELSE 0 END) AS totalCate1,\n"
                + "SUM(CASE WHEN p.category_id = 2 THEN i.quantity * i.product_currentprice ELSE 0 END) AS totalCate2,\n"
                + "SUM(CASE WHEN p.category_id = 3 THEN i.quantity * i.product_currentprice ELSE 0 END) AS totalCate3,\n"
                + "SUM(CASE WHEN p.category_id = 4 THEN i.quantity * i.product_currentprice ELSE 0 END) AS totalCate4,\n"
                + "SUM(CASE WHEN p.category_id = 5 THEN i.quantity * i.product_currentprice ELSE 0 END) AS totalCate5\n"
                + "FROM [OrderDetail] i JOIN\n"
                + "Product p ON i.product_id = p.pid\n"
                + "WHERE\n"
                + "p.category_id IN (1, 2, 3, 4, 5) AND\n"
                + "p.seller_id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, s_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ChartByCategory m = new ChartByCategory(
                        rs.getInt("totalCate1"),
                        rs.getInt("totalCate2"),
                        rs.getInt("totalCate3"),
                        rs.getInt("totalCate4"),
                        rs.getInt("totalCate5"));

                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChartByFeedback getFeedBackTotal(int s_id) {
        String sql = "SELECT p.seller_id as s_id , \n"
                + "SUM(CASE WHEN rate >= 4 THEN 1 ELSE 0 END) AS good_reviews,\n"
                + "SUM(CASE WHEN rate <= 3 THEN 1 ELSE 0 END) AS bad_reviews\n"
                + "FROM product p left join FeedBack fb on p.pid = fb.product_id\n"
                + "where p.seller_id = ?\n"
                + "GROUP BY p.seller_id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, s_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ChartByFeedback m = new ChartByFeedback(
                        rs.getInt("good_reviews"),
                        rs.getInt("bad_reviews"));

                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ChartByFeedback m = ChartDAO.gI().getFeedBackTotal(1);
        System.out.println(m);

    }
}
