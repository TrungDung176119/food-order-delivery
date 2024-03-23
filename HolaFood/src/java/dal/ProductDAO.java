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
                + "join Menu_Item mi on p.pid = mi.product_id\n"
                + "join Menu m on m.menu_id = mi.menu_id\n"
                + "where m.status = 1  and s.is_active = 1 ";

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
            query += " and c.id = " + cateid ;
        }
        if (keyword != null && cateid == null) {
            query += " and (p.title like ? )";
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
        if (sortBy == null) {
            query += " order by newid()";
        }
        try {
            ps = connection.prepareStatement(query);
            if (keyword != null && cateid == null) {
                ps.setString(1, "%" + keyword.trim() + "%");
//                ps.setString(2, "%" + keyword.trim() + "%");
//                ps.setString(3, "%" + keyword.trim() + "%");
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
     * @param seller_id
     * @return
     */
    public boolean setProductRating(int pid, int seller_id) {
        String productQuery = "UPDATE Product "
                + "SET rating = ROUND((SELECT AVG(CAST(feed.rate AS FLOAT)) "
                + "FROM FeedBack feed "
                + "WHERE feed.product_id = ?), 1) "
                + "WHERE pid = ?;";

        String query = "UPDATE Seller \n"
                + "SET rating_store = ROUND((SELECT AVG(CAST(feed.rate AS FLOAT)) \n"
                + "FROM FeedBack feed INNER JOIN Product p ON feed.product_id = p.pid \n"
                + "WHERE p.seller_id = ?), 1) \n"
                + "WHERE seller_id = ?";
        try {
            // Update Product rating
            ps = connection.prepareStatement(productQuery);
            ps.setInt(1, pid);
            ps.setInt(2, pid);
            int productRowsAffected = ps.executeUpdate();

            // Update Seller rating_store
            ps = connection.prepareStatement(query);
            ps.setInt(1, seller_id);
            ps.setInt(2, seller_id);
            int sellerRowsAffected = ps.executeUpdate();

            // Return true if at least one row was updated in both Product and Seller tables
            return productRowsAffected > 0 && sellerRowsAffected > 0;
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

    
    public void applySaleProductOff(int m_id) {
        ArrayList<Integer> listpid = new ArrayList<>();
        int saleoff = 0;

        String query1 = "select * from menu m join Menu_Item mi \n"
                + "on m.menu_id = mi.menu_id\n"
                + "where m.menu_id = ?";

        String query2 = "update Product set current_price = current_price * (100 - ?) / 100 where pid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query1);
            ps.setInt(1, m_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                saleoff = rs.getInt("sale_off");
                listpid.add(rs.getInt("product_id"));
            }
            try {
                for (Integer i : listpid) {
                    ps = connection.prepareStatement(query2);
                    ps.setInt(1, saleoff);
                    ps.setInt(2, i);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
    }

    public void disableSaleProductOff(int m_id) {
        ArrayList<Integer> listpid = new ArrayList<>();
        int saleoff = 0;

        String query1 = "select * from menu m join Menu_Item mi \n"
                + "on m.menu_id = mi.menu_id\n"
                + "where m.menu_id = ?";

        String query2 = "update Product set current_price = current_price / (100 - ?) * 100 where pid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query1);
            ps.setInt(1, m_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                saleoff = rs.getInt("sale_off");
                listpid.add(rs.getInt("product_id"));
            }
            try {
                for (Integer i : listpid) {
                    ps = connection.prepareStatement(query2);
                    ps.setInt(1, saleoff);
                    ps.setInt(2, i);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
    }
    
//    public boolean Update
    public static void main(String[] args) {
        ProductDAO hdao = new ProductDAO();
        List<Product> list = hdao.searchProductsByCondition(null, "sellest", "3");
//        List<Product> list = hdao.getProductByType(ConstHome.MOST_PURCHASED, 0);
////
        for (Product product : list) {
            System.out.println(product);
        }

//        Product p = hdao.getProductByID("5");
//        System.out.println(p.getSeller_id());
//        boolean set = hdao.setProductRating(1, 1);
//        if (set) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
    }
}
