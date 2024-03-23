/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dashboard.ProDashboard;
import model.product.Category;
import model.product.Product;

/**
 *
 * @author admin
 */
public class DashboardDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static DashboardDAO i;

    public static DashboardDAO gI() {
        if (i == null) {
            i = new DashboardDAO();
        }
        return i;
    }
    
    public List<Product> getAllProStatist() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.pid,p.category_id,p.seller_id,p.image,p.title,p.old_price,p.current_price,p.amount_of_sold,\n"
                + "p.number_in_stock,p.status,p.describe,p.rating, c.id cid,c.name cname FROM Product p inner join Category c \n"
                + "on  p.category_id= c.id";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();

                p.setPid(rs.getInt("pid"));
                p.setSeller_id(rs.getInt("seller_id"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOld_price(rs.getInt("old_price"));
                p.setCurrent_price(rs.getInt("current_price"));
                p.setAmount_of_sold(rs.getInt("amount_of_sold"));
                p.setNumber_in_stock(rs.getInt("number_in_stock"));
                p.setStatus(rs.getString("status"));
                p.setDescribe(rs.getString("describe"));
                p.setRating(rs.getFloat("rating"));

//                Category c= new Category(rs.getInt("cid"), rs.getString("cname"));
                Category c = new Category();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                p.setCategory(c);

                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
      public List<ProDashboard> getAllProDashboard(Date startDate, Date endDate) {
        List<ProDashboard> list = new ArrayList<>();

        String query1 = "SELECT p.pid AS pid,od.quantity,\n"
                + "    SUM(od.quantity * od.product_currentprice) AS total_revenue\n"
                + "FROM product p left join OrderDetail od on p.pid = od.product_id\n"
                + "GROUP BY p.pid,od.quantity\n"
                + "order by pid ASC";

        String query2 = "SELECT p.pid AS pid,\n"
                + "    SUM(CASE WHEN rate >= 4 THEN 1 ELSE 0 END) AS good_reviews,\n"
                + "    SUM(CASE WHEN rate <= 3 THEN 1 ELSE 0 END) AS bad_reviews\n"
                + "FROM product p left join FeedBack fb on p.pid = fb.product_id\n"
                + "GROUP BY p.pid\n"
                + "order by pid ASC";
        if (startDate != null && endDate != null) {
            query1 = "SELECT p.pid AS pid,od.quantity,\n"
                    + "    SUM(od.quantity * od.product_currentprice) AS total_revenue\n"
                    + "FROM product p left join OrderDetail od on p.pid = od.product_id\n"
                    + "left join Orders o on o.order_id = od.order_id\n"
                    + "where o.order_date between ? and ?\n"
                    + "GROUP BY p.pid,od.quantity\n"
                    + "order by pid ASC";
            query2 = "SELECT p.pid AS pid,\n"
                    + "    SUM(CASE WHEN rate >= 4 THEN 1 ELSE 0 END) AS good_reviews,\n"
                    + "    SUM(CASE WHEN rate <= 3 THEN 1 ELSE 0 END) AS bad_reviews\n"
                    + "FROM product p left join FeedBack fb on p.pid = fb.product_id\n"
                    + "left join Orders o on o.order_id = od.order_id\n"
                    + "where o.order_date between ? and ?\n"
                    + "GROUP BY p.pid\n"
                    + "order by pid ASC";
        } else {
            if (startDate != null) {
                query1 = "SELECT p.pid AS pid,od.quantity,\n"
                        + "    SUM(od.quantity * od.product_currentprice) AS total_revenue\n"
                        + "FROM product p left join OrderDetail od on p.pid = od.product_id\n"
                        + "left join Orders o on o.order_id = od.order_id\n"
                        + "where o.order_date >= ?\n"
                        + "GROUP BY p.pid,od.quantity\n"
                        + "order by pid ASC";
                query2 = "SELECT p.pid AS pid,\n"
                        + "    SUM(CASE WHEN rate >= 4 THEN 1 ELSE 0 END) AS good_reviews,\n"
                        + "    SUM(CASE WHEN rate <= 3 THEN 1 ELSE 0 END) AS bad_reviews\n"
                        + "FROM product p left join FeedBack fb on p.pid = fb.product_id\n"
                        + "left join Orders o on o.order_id = od.order_id\n"
                        + "where o.order_date >= ?\n"
                        + "GROUP BY p.pid\n"
                        + "order by pid ASC";
            }
            if (endDate != null) {
                query1 = "SELECT p.pid AS pid,od.quantity,\n"
                        + "    SUM(od.quantity * od.product_currentprice) AS total_revenue\n"
                        + "FROM product p left join OrderDetail od on p.pid = od.product_id\n"
                        + "left join Orders o on o.order_id = od.order_id\n"
                        + "where o.order_date <= ?\n"
                        + "GROUP BY p.pid,od.quantity\n"
                        + "order by pid ASC";
                query2 = "SELECT p.pid AS pid,\n"
                        + "    SUM(CASE WHEN rate >= 4 THEN 1 ELSE 0 END) AS good_reviews,\n"
                        + "    SUM(CASE WHEN rate <= 3 THEN 1 ELSE 0 END) AS bad_reviews\n"
                        + "FROM product p left join FeedBack fb on p.pid = fb.product_id\n"
                        + "left join Orders o on o.order_id = od.order_id\n"
                        + "where o.order_date <= ?\n"
                        + "GROUP BY p.pid\n"
                        + "order by pid ASC";
            }
        }
        try {
            ps = connection.prepareStatement(query1);
            if (startDate != null && endDate != null) {
                ps.setDate(1, startDate);
                ps.setDate(2, endDate);
            } else {
                if (startDate != null) {
                    ps.setDate(1, startDate);
                }
                if (endDate != null) {
                    ps.setDate(1, endDate);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                ProDashboard p = new ProDashboard();
                p.setPid(rs.getInt(1));
                p.setQuantity(rs.getInt(2));
                p.setRevenue(rs.getInt(3));
                list.add(p);
            }
            try {
                ps = connection.prepareStatement(query2);
                rs = ps.executeQuery();
                while (rs.next()) {
                    for (ProDashboard p : list) {
                        if (p.getPid() == rs.getInt(1)) {
                            p.setGoodFB(rs.getInt(2));
                            p.setBadFB(rs.getInt(3));
                        }
                    }
                }

                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

}
