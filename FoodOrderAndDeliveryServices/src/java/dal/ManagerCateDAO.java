/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstHome;
import constant.ConstShop;
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
public class ManagerCateDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ManagerCateDAO i;

    public static ManagerCateDAO gI() {
        if (i == null) {
            i = new ManagerCateDAO();
        }
        return i;
    }

    public List<Product> getProductBySeller(final int TYPE_LOAD_PRODUCT, int cid, int sid) {
        List<Product> list = new ArrayList<>();
        String query = ConstHome.QUERY_LOAD_LIST_PRODUCT;
        switch (TYPE_LOAD_PRODUCT) {
            case ConstShop.MENU_TODAY:
                query += " and p.seller_id = " + sid + " order by newid()";
                break;
            case ConstShop.OUTSTANDING_PRODUCT:
                query += (" and p.seller_id = " + sid + " order by p.amount_of_sold desc");
                break;
            case ConstShop.SUGGESTION_OF_SELLER:
                query += (" and c.id = " + cid + " and p.seller_id = " + sid + " order by newid()");
        }
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setPid(rs.getInt("pid"));
                p.setSeller_id(rs.getInt("seller_id"));
                p.setSeller_name(rs.getString("seller_name"));
                p.setAddress_store(rs.getString("address_store"));
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

    public boolean insertCategory(String cateName) {
        boolean checkSuccessful = false;
        String query = "insert into Category values (?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cateName);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                checkSuccessful = true;
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
        return checkSuccessful;
    }

    public boolean deleteCategory(int cateId) {
        String query = "delete Category where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cateId);
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

    public boolean updateCategory(String name, int cateId) {
        boolean checkSuccessful = false;
        String query = "update Category set [name] = ? where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, cateId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                checkSuccessful = true;
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
        return checkSuccessful;
    }

    public Category getCategoryById(int cateId) {
        String query = "select * from Category where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cateId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1),
                        rs.getString(2));
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
        return null;
    }

    public static void main(String[] args) {
        List<Product> list = ManagerCateDAO.gI().getProductBySeller(ConstShop.OUTSTANDING_PRODUCT, 0, 3);
        if (list != null) {
            System.out.println(list);
        } else {
            System.out.println("no");
        }
    }

}
