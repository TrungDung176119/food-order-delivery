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
import model.profile.Seller;

/**
 *
 * @author admin
 */
public class ProductDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ProductDAO i;

    public static ProductDAO gI() {
        if (i == null) {
            i = new ProductDAO();
        }
        return i;
    }

    public List<Product> getProductByType(final int TYPE_LOAD_PRODUCT, int index) {
        List<Product> list = new ArrayList<>();
        String query = "select TOP " + ConstHome.LOAD_NUMBER_PRODUCT + " p.pid, p.seller_id,  s.store_name as seller_name, s.address_store ,\n"
                + " p.[image], p.title, p.old_price, p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                + "from Product p join Category c on p.category_id = c.id\n"
                + "join Seller s on p.seller_id = s.seller_id\n"
                + "join Menu m on m.seller_id = s.seller_id\n"
                + "where m.status = 1 and s.is_active = 1 ";

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
                case ConstHome.NUMBER_PAGINATION:
                    query = ConstHome.QUERY_LOAD_LIST_PRODUCT + " ORDER BY p.pid offset ? ROWS FETCH NEXT " + ConstHome.NUMBER_PAGINATION + " ROWS ONLY ";
                    break;
            }
        }
        try {
            ps = connection.prepareStatement(query);
            if (TYPE_LOAD_PRODUCT == ConstHome.NUMBER_PAGINATION) {
                ps.setInt(1, (index - 1) * ConstHome.NUMBER_PAGINATION);
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

    public Product getProductByID(int pid) {
        List<Product> list = this.getProductByType(ConstHome.TODAY_SUGGESTION, 1);
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
            query += " and c.id = " + cateid + " order by newid()";
        }
        if (keyword != null && cateid == null) {
            query += " and p.title like ? or s.store_name like ? or s.address_store like ? " + " order by newid()";
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
                ps.setString(1, "%" + keyword.trim() + "%");
                ps.setString(2, "%" + keyword.trim() + "%");
                ps.setString(3, "%" + keyword.trim() + "%");
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
        if (sortBy == null) {
            Collections.shuffle(list, new Random());
        }
        return list;
    }

    // author: Nguyen Anh Duc
    public int getSidFromProduct(String pid) {
        int s_id = 0;
        String query0 = "SELECT seller_id FROM Product WHERE pid = ?";
        try {
            ps = connection.prepareStatement(query0);
            ps.setString(1, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                s_id = rs.getInt("seller_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // avoid resource leaks, 
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return s_id;
    }

    public Product getProductByID(String pid) {
        String query = ConstHome.QUERY_LOAD_LIST_PRODUCT + " and p.pid = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pid);
            rs = ps.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setPid(rs.getInt("pid"));
                p.setSeller_id(rs.getInt("seller_id"));
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

                return p;
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
        return null;
    }

    public List<Product> getBestProductOfSeller(int s_id) {
        List<Product> list = new ArrayList<>();
        String query = "select TOP 6 p.pid, p.seller_id,  s.store_name as seller_name, s.address_store ,\n"
                + " p.[image], p.title, p.old_price, p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                + "from Product p join Category c on p.category_id = c.id\n"
                + "join Seller s on p.seller_id = s.seller_id\n"
                + "where p.seller_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, s_id);
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
        } finally {             // avoid resource leaks, 
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // Trộn danh sách sản phẩm ngẫu nhiên sau khi lấy dữ liệu từ cơ sở dữ liệu.
        Collections.shuffle(list, new Random());
        return list;
    }

    public int getCidFromProduct(String pid) {
        int c_id = 0;
        String query0 = "SELECT category_id FROM Product WHERE pid = ?";
        try {
            ps = connection.prepareStatement(query0);
            ps.setString(1, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                c_id = rs.getInt("category_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // avoid resource leaks, 
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return c_id;
    }

    /**
     * Update new rate of product
     *
     * @param pid
     * @return
     */
    public boolean setProductRating(int pid) {
        String query = "SELECT product_id, AVG(rate) AS average_rating\n"
                + "FROM FeedBack\n"
                + "WHERE product_id = ?\n"
                + "GROUP BY product_id";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                float avg_rating = rs.getFloat("average_rating");
                // Kiểm tra xem avg_rating có giá trị không
                if (!rs.wasNull()) {
                    String query2 = "UPDATE Product SET rating = ? WHERE pid = ?";
                    PreparedStatement ps1 = connection.prepareStatement(query2);
                    ps1.setFloat(1, avg_rating);
                    ps1.setInt(2, pid);
                    ps1.executeUpdate();
                    return true;
                }
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
        return false;
    }

    /**
     *
     *
     * @param pid
     * @return
     */
    public float getAverageRating(String pid) {
        String query = "SELECT AVG(rate) AS average_rating\n"
                + "FROM FeedBack WHERE product_id = ?\n"
                + "GROUP BY product_id";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                float averageRating = rs.getFloat("average_rating");
                return averageRating;
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
        return 0; // or any appropriate default value
    }

    /**
     * Display amount of rating star on Detail page, feedback part.
     *
     * @param pid
     * @param rating
     * @return
     */
    public int getNumberOfRate(String pid, int rating) {
        String query = "SELECT COUNT(feed_id) AS total_ratings\n"
                + "FROM FeedBack WHERE product_id = ? AND rate = ?\n"
                + "GROUP BY product_id";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pid);
            ps.setInt(2, rating);
            rs = ps.executeQuery();

            if (rs.next()) {
                int total_rating = rs.getInt("total_ratings");
                return total_rating;
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
        return 0;
    }    
    
//    public boolean Update
    
    
    
    public static void main(String[] args) {
        ProductDAO hdao = new ProductDAO();
//        List<Product> list = hdao.searchProductsByCondition(null, "", "3");
//        List<Product> list = hdao.getProductByType(ConstHome.TODAY_SUGGESTION, 0);
//
//        for (Product product : list) {
//            System.out.println(product);
//        }

        Product p = hdao.getProductByID("5");
        System.out.println(p.getSeller_id());

//        boolean set = hdao.setProductRating(48);
//        if (set) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
    }
}
