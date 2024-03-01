/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
}
