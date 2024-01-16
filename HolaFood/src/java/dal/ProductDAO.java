/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstHome;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import model.product.Category;
import model.product.Product;

/**
 *
 * @author admin
 */
public class ProductDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct(final int TYPE_LOAD_PRODUCT) {
        List<Product> list = new ArrayList<>();
        String query = "select TOP " + ConstHome.LOAD_NUMBER_PRODUCT + " p.pid, p.seller_id,  s.store_name as seller_name, s.address_store ,\n"
                + " p.[image], p.title, p.old_price, p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                + "from Product p join Category c on p.category_id = c.id\n"
                + "join Seller s on p.seller_id = s.seller_id";

        if (TYPE_LOAD_PRODUCT != ConstHome.DEFAULT) {
            switch (TYPE_LOAD_PRODUCT) {
                case ConstHome.TODAY_SUGGESTION:
                    query = ConstHome.QUERY_LOAD_LIST_PRODUCT;
                    break;
                case ConstHome.MOST_PURCHASED:
                    query += " ORDER BY p.amount_of_sold DESC";
                    break;
                case ConstHome.HIGH_RATING:
                    query += " ORDER BY p.rating DESC";
                    break;
            }
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
            if (ps != null) {             // avoid resource leaks, 
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        // Trộn danh sách sản phẩm ngẫu nhiên sau khi lấy dữ liệu từ cơ sở dữ liệu.
        Collections.shuffle(list, new Random());
        return list;
    }

    /**
     *
     * @return
     */
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "Select * from Category";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null || rs != null) {             // avoid resource leaks, 
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * This method for detail feature. Display Product follow id
     *
     * @param pid
     * @return
     */
    public Product getProductByID(int pid) {
        List<Product> list = this.getAllProduct(ConstHome.TODAY_SUGGESTION);
        for (Product product : list) {
            if (product.getPid() == pid) {
                return product;
            }
        }
        return null;
    }

    public List<Product> searchProductsByCondition(String keyword, String sortBy, String cateid) {
        List<Product> list = new ArrayList<>();
        String query = ConstHome.QUERY_LOAD_LIST_PRODUCT;

        if (cateid != null && keyword == null) {
            query += " where c.id = " + cateid;
        }
        if (keyword != null && cateid == null) {
            query += " where p.title like ? ";
        }
        if (sortBy != null) {
            if (sortBy.equalsIgnoreCase("sale")) {
                query += " and p.[status] = 'sale'";
            } else if (sortBy.equals("newest")) {
                query += " ORDER BY p.pid DESC";
            } else if (sortBy.equals("sellest")) {
                query += " ORDER BY p.amount_of_sold DESC";
            } else if (sortBy.equals("desc")) {
                query += " ORDER BY p.current_price DESC";
            } else if (sortBy.equals("asc")) {
                query += " ORDER BY p.current_price ASC";
            }

        }
        try {
            ps = connection.prepareStatement(query);
            if (keyword != null && cateid == null) {
                ps.setString(1, "%" + keyword + "%");
            }
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
            if (ps != null) {             // avoid resource leaks, 
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO hdao = new ProductDAO();
//        List<Product> list = hdao.searchProductsByCondition(".", "", "");
        List<Product> list = hdao.getAllProduct(ConstHome.TODAY_SUGGESTION);

        for (Product product : list) {
            System.out.println(product.getSeller_id());
        }  

//        Product p = hdao.getProductByID("4");
//        System.out.println(p);
    }
}
