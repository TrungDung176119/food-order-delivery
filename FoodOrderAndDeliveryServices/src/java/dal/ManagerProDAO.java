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
public class ManagerProDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ManagerProDAO i;

    public static ManagerProDAO gI() {
        if (i == null) {
            i = new ManagerProDAO();
        }
        return i;
    }

    public boolean manageProduct(final String TYPE_ACTION, int pid, int category, int seller_id, String image,
            String title, int old_price, int current_price, int amount_of_sold, int number_in_stock, String status, String describe, float rating) {
        String query = "";

        if (TYPE_ACTION.equals("INSERT")) {
            query = "INSERT INTO [dbo].[Product]([category_id],[seller_id],[image],[title],[old_price],[current_price],[amount_of_sold],[number_in_stock],[status],[describe],[rating])\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (TYPE_ACTION.equals("UPDATE")) {
            query = "UPDATE [dbo].[Product] SET [category_id] = ?, [seller_id] = ?, [image] = ?, [title] = ?, [old_price] = ?, [current_price] = ?, [amount_of_sold] = ?, [number_in_stock] = ?, [status] = ?, [describe] = ?, [rating] = ?\n"
                    + " WHERE [pid] = ?";
        } else if (TYPE_ACTION.equals("HIDE")) {
            query = "UPDATE [dbo].[Product] SET [status] = ?\n"
                    + "      WHERE [pid] = ?";
        }
        try {
            ps = connection.prepareStatement(query);
            if (TYPE_ACTION.equals("HIDE")) {
                ps.setString(1, status);
                ps.setInt(2, pid);
            } else {
                ps.setInt(1, category);
                ps.setInt(2, seller_id);
                ps.setString(3, image);
                ps.setString(4, title);
                ps.setInt(5, old_price);
                ps.setInt(6, current_price);
                ps.setInt(7, amount_of_sold);
                ps.setInt(8, number_in_stock);
                ps.setString(9, status);
                ps.setString(10, describe);
                ps.setFloat(11, rating);
                if (TYPE_ACTION.equals("UPDATE")) {
                    ps.setInt(12, pid);
                }
            }
            ps.executeUpdate();
            return true;
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
        return false;
    }

    public List<Product> searchbyCondition(String name, int s_id) {
        List<Product> list = new ArrayList<>();
        String query = "select p.pid, s.store_name as seller_name , p.[image], p.title, p.old_price,\n"
                + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                + "from Product p join Category c on p.category_id = c.id\n"
                + "join Seller s on p.seller_id = s.seller_id\n"
                + "where p.title like ? and s.seller_id = ?\n"
                + "ORDER BY p.amount_of_sold DESC";
        String query2 = "select p.pid, s.store_name as seller_name , p.[image], p.title, p.old_price,\n"
                + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                + "from Product p join Category c on p.category_id = c.id\n"
                + "join Seller s on p.seller_id = s.seller_id\n"
                + "where p.title like ?\n"
                + "ORDER BY p.amount_of_sold DESC";
        try {

            if (s_id == 0) {
                ps = connection.prepareStatement(query2);
                ps.setString(1, "%" + name.trim() + "%");
            } else {
                ps = connection.prepareStatement(query);
                ps.setString(1, "%" + name.trim() + "%");
                ps.setInt(2, s_id);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setPid(rs.getInt("pid"));
                p.setSeller_name(rs.getString("seller_name"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOld_price(rs.getInt("old_price"));
                p.setCurrent_price(rs.getInt("current_price"));
                p.setAmount_of_sold(rs.getInt("amount_of_sold"));
                p.setNumber_in_stock(rs.getInt("number_in_stock"));
                p.setStatus(rs.getString("status"));
                p.setDescribe(rs.getString("describe"));
                p.setRating(rs.getFloat("rating"));

                Category c = new Category();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                p.setCategory(c);

                list.add(p);
            }
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
        return list;
    }

    public List<Product> filterProduct(final String TYPE_ACTION, final String TYPE_ACTION2, String cateid, String status, int seller_id) {
        String query;
        List<Product> list = new ArrayList<>();
        try {
            if (TYPE_ACTION.equals("CATE")) {
                if (TYPE_ACTION2.equals("ADMIN")) {
                    query = "select p.pid, s.store_name as seller_name , p.[image], p.title, p.old_price,\n"
                            + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                            + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                            + "from Product p join Category c on p.category_id = c.id\n"
                            + "join Seller s on p.seller_id = s.seller_id\n"
                            + "where p.category_id = ?\n"
                            + "ORDER BY p.amount_of_sold DESC";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, cateid);
                } else if (TYPE_ACTION2.equals("SELLER")) {
                    query = "select p.pid, s.store_name as seller_name , p.[image], p.title, p.old_price,\n"
                            + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                            + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                            + "from Product p join Category c on p.category_id = c.id\n"
                            + "join Seller s on p.seller_id = s.seller_id\n"
                            + "where p.category_id = ? and s.seller_id = ?\n"
                            + "ORDER BY p.amount_of_sold DESC";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, cateid);
                    ps.setInt(2, seller_id);
                }
            } else if (TYPE_ACTION.equals("STATUS")) {
                if (TYPE_ACTION2.equals("ADMIN")) {
                    query = "select p.pid, s.store_name as seller_name , p.[image], p.title, p.old_price,\n"
                            + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                            + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                            + "from Product p join Category c on p.category_id = c.id\n"
                            + "join Seller s on p.seller_id = s.seller_id\n"
                            + "where p.[status] = ?\n"
                            + "ORDER BY p.amount_of_sold DESC";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, status);
                } else if (TYPE_ACTION2.equals("SELLER")) {
                    query = "select p.pid, s.store_name as seller_name , p.[image], p.title, p.old_price,\n"
                            + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                            + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                            + "from Product p join Category c on p.category_id = c.id\n"
                            + "join Seller s on p.seller_id = s.seller_id\n"
                            + "where p.[status] = ? and s.seller_id= ?\n"
                            + "ORDER BY p.amount_of_sold DESC";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, status);
                    ps.setInt(2, seller_id);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setPid(rs.getInt("pid"));
                p.setSeller_name(rs.getString("seller_name"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOld_price(rs.getInt("old_price"));
                p.setCurrent_price(rs.getInt("current_price"));
                p.setAmount_of_sold(rs.getInt("amount_of_sold"));
                p.setNumber_in_stock(rs.getInt("number_in_stock"));
                p.setStatus(rs.getString("status"));
                p.setDescribe(rs.getString("describe"));
                p.setRating(rs.getFloat("rating"));

                Category c = new Category();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                p.setCategory(c);

                list.add(p);
            }
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
        return list;
    }
}
