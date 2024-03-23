/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstHome;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import model.cart.Item;
import model.product.Category;
import model.product.Product;
import model.profile.Account;
import utils.Cart;

/**
 *
 * @author admin
 */
public class CartDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static CartDAO ins;

    public static CartDAO gI() {
        if (ins == null) {
            ins = new CartDAO();
        }
        return ins;
    }

//    public boolean addOrder(Account a, Cart cart, int totalMoney, String nickname, String phone, String email, String address,
//            String note, int is_delivered, int is_accepted, int is_feedback, int is_purchased, int discount, int is_ship) {
//    
//        try {
//            String query = "INSERT INTO [dbo].[Orders] ([acc_id] ,[nickname],[phone] ,[email],[address] ,[note] ,[order_date] \n"
//                    + ",[discount] ,[total_price], [is_shipped])"
//                    + "  VALUES(?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?)";
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, a.getAccid());
//            ps.setString(2, nickname);
//            ps.setString(3, phone);
//            ps.setString(4, email);
//            ps.setString(5, address);
//            ps.setString(6, note);
//            ps.setInt(7, discount);
//            ps.setInt(8, totalMoney);
//            ps.setInt(9, is_ship);
//
//            ps.executeUpdate();
//            // take id of order table that have just added
//            String query1 = "SELECT TOP 1 order_id FROM Orders WHERE acc_id = ? ORDER BY order_id DESC";
//            PreparedStatement ps1 = connection.prepareStatement(query1);
//            ps1.setInt(1, a.getAccid());
//            rs = ps1.executeQuery();
//            if (rs.next()) {
//                int oid = rs.getInt("order_id");
//                for (Item i : cart.getItems()) {
//                    String query2 = "INSERT INTO OrderDetail (order_id, product_id, quantity, seller_id, price, total_price, "
//                            + "[is_delivered] ,[is_accepted] ,[is_feedback], [is_purchased] )"
//                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                    PreparedStatement ps2 = connection.prepareStatement(query2);
//                    ps2.setInt(1, oid);
//                    ps2.setInt(2, i.getProduct().getPid());
//                    ps2.setInt(3, i.getQuantity());
//                    ps2.setInt(4, i.getProduct().getSeller_id());
//                    ps2.setInt(5, i.getProduct().getCurrent_price());
//                    ps2.setInt(6, i.getQuantity() * i.getProduct().getCurrent_price());
//                    ps2.setInt(7, is_delivered);
//                    ps2.setInt(8, is_accepted);
//                    ps2.setInt(9, is_feedback);
//                    ps2.setInt(10, is_purchased);
//                    ps2.executeUpdate();
//                }
//            }
//            // update stock of product 
//            String sql3 = "UPDATE product SET number_in_stock = number_in_stock - ?,  "
//                    + "amount_of_sold = amount_of_sold + ?\n"
//                    + "where pid = ?";
//            PreparedStatement ps3 = connection.prepareStatement(sql3);
//            for (Item i : cart.getItems()) {
//                ps3.setInt(1, i.getQuantity());
//                ps3.setInt(2, i.getQuantity());
//                ps3.setInt(3, i.getProduct().getPid());
//                ps3.executeUpdate();
//            }
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (ps != null) {             // avoid resource leaks, 
//                try {
//                    ps.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        return false;
//    }
    public boolean addOrder(Account a, Cart cart, String nickname, String phone, String email, String address,
            String note, int is_delivered, int is_accepted, int is_feedback, int is_purchased, int discount, int is_ship) {
        try {
            Map<Integer, List<Item>> sellerItemsMap = new HashMap<>();

            for (Item i : cart.getItems()) {
                int sellerId = i.getProduct().getSeller_id();
                if (!sellerItemsMap.containsKey(sellerId)) {
                    sellerItemsMap.put(sellerId, new ArrayList<>());
                }
                sellerItemsMap.get(sellerId).add(i);
            }

            // Duyệt qua danh sách người bán và tạo đơn hàng cho mỗi người bán
            for (Map.Entry<Integer, List<Item>> entry : sellerItemsMap.entrySet()) {
                int sellerId = entry.getKey();
                List<Item> sellerItems = entry.getValue();
                int orderTotal = 0;  // Biến để tính tổng giá trị của đơn hàng

                // Tính tổng giá trị của đơn hàng
                for (Item item : sellerItems) {
                    orderTotal += item.getQuantity() * item.getProduct().getCurrent_price();
                }
                if (is_ship != 0) {
                    orderTotal += is_ship;
                }
                String query = "INSERT INTO [dbo].[Orders] ([acc_id], [seller_id], [nickname], [phone], [email], [address], \n"
                        + " [note], [is_shipped], [is_delivered], [is_accepted], [is_purchased],"
                        + " [order_date] ,[discount] ,[total_price])"
                        + "  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?)";
                ps = connection.prepareStatement(query);
                ps.setInt(1, a.getAccid());
                ps.setInt(2, sellerId);
                ps.setString(3, nickname);
                ps.setString(4, phone);
                ps.setString(5, email);
                ps.setString(6, address);
                ps.setString(7, note);
                ps.setInt(8, is_ship);
                ps.setInt(9, is_delivered);
                ps.setInt(10, is_accepted);
                ps.setInt(11, is_purchased);
                ps.setInt(12, discount);
                ps.setInt(13, orderTotal);

                ps.executeUpdate();
                // take id of order table that have just added
                String query1 = "SELECT TOP 1 order_id FROM Orders WHERE acc_id = ? ORDER BY order_id DESC";
                PreparedStatement ps1 = connection.prepareStatement(query1);
                ps1.setInt(1, a.getAccid());
                rs = ps1.executeQuery();
                if (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    // Thêm các sản phẩm vào bảng OrderDetail cho từng người bán
                    for (Item item : sellerItems) {
                        String query2 = "INSERT INTO OrderDetail (order_id, product_id, product_title, product_image, product_catename,"
                                + "product_storename, product_status, product_oldprice, product_currentprice, "
                                + " quantity, total_price, is_feedback )"
                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps2 = connection.prepareStatement(query2);
                        ps2.setInt(1, orderId);
                        ps2.setInt(2, item.getProduct().getPid());
                        ps2.setString(3, item.getProduct().getTitle());
                        ps2.setString(4, item.getProduct().getImage());
                        ps2.setString(5, item.getProduct().getCategory().getName());
                        ps2.setString(6, item.getProduct().getSeller_name());
                        ps2.setString(7, item.getProduct().getStatus());
                        ps2.setInt(8, item.getProduct().getOld_price());
                        ps2.setInt(9, item.getProduct().getCurrent_price());
                        ps2.setInt(10, item.getQuantity());
                        ps2.setInt(11, item.getQuantity() * item.getProduct().getCurrent_price());
                        ps2.setInt(12, is_feedback);
                        ps2.executeUpdate();
                    }
                    // Cập nhật số lượng tồn kho và số lượng bán của mỗi sản phẩm
                    String sql3 = "UPDATE product SET number_in_stock = number_in_stock - ?,  "
                            + "amount_of_sold = amount_of_sold + ?\n"
                            + "WHERE pid = ?";
                    PreparedStatement ps3 = connection.prepareStatement(sql3);
                    for (Item item : sellerItems) {
                        ps3.setInt(1, item.getQuantity());
                        ps3.setInt(2, item.getQuantity());
                        ps3.setInt(3, item.getProduct().getPid());
                        ps3.executeUpdate();
                    }
                }
            }
            return true;
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
        return false;
    }

    /**
     * This method for cart page
     *
     * @param cart
     * @param searchQueries
     * @return
     */
    public List<Product> getRecommendedProducts(Cart cart, List<String> searchQueries) {
        List<Product> list = new ArrayList<>();

        String cateid = "", proid = "";
        if (cart == null || cart.getItems().isEmpty()) {
            proid = "-1";
            cateid = "-1";
        } else {
            for (int i = 0; i < cart.getItems().size(); i++) {
                cateid += cart.getItems().get(i).getProduct().getCategory().getId();
                if (i != cart.getItems().size() - 1) {
                    cateid += ", ";
                }
            }
            for (int i = 0; i < cart.getItems().size(); i++) {
                proid += cart.getItems().get(i).getProduct().getPid();
                if (i != cart.getItems().size() - 1) {
                    proid += ", ";
                }
            }
        }
        String params = "";
        if (searchQueries != null) {
            params = String.join(" OR p.title LIKE ? ", Collections.nCopies(searchQueries.size(), ""));
        }

        String query = ConstHome.QUERY_LOAD_LIST_PRODUCT
                + " AND c.id IN (" + cateid + ")\n"
                + " AND p.pid NOT IN (" + proid + ")\n";
        query += "UNION \n"
                + ConstHome.QUERY_LOAD_LIST_PRODUCT
                + " AND c.id IN (3,4)\n"
                + " AND p.pid NOT IN (" + proid + ") \n";
        if (searchQueries != null) {
            query += "UNION \n"
                    + "select top 7  p.pid, p.seller_id, s.store_name as seller_name, s.address_store , p.[image], p.title, p.old_price,\n"
                    + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                    + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
                    + "from Product p join Category c on p.category_id = c.id\n"
                    + "join Seller s on p.seller_id = s.seller_id\n"
                    + "join Menu_Item mi on p.pid = mi.product_id\n"
                    + "join Menu m on m.menu_id = mi.menu_id\n"
                    + "where m.status = 1 and s.is_active = 1  "
                    + " AND p.title LIKE ? " + params
                    + " AND p.pid NOT IN (" + proid + ")";
        }

        try {
            ps = connection.prepareStatement(query);
            // Đặt giá trị cho các parameters
            if (searchQueries != null) {
                for (int i = 0; i < searchQueries.size(); i++) {
                    ps.setString(i + 1, "%" + searchQueries.get(i) + "%");
                }
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
        Collections.shuffle(list, new Random());
        return list;
    }

//    public List<Category> listCateInCart(Cart cart) {
//        Set<Category> uniqueCategories = new HashSet<>();
//
//        for (Item item : cart.getItems()) {
//            Category category = item.getProduct().getCategory();
//            // Thêm đối tượng Category vào Set để loại bỏ các giá trị trùng lặp
//            uniqueCategories.add(category);
//        }
//        // Chuyển Set thành List nếu cần thiết
//        List<Category> listCate = new ArrayList<>(uniqueCategories);
//        return listCate;
//    }
    public static void main(String[] args) {
        LocalDate curDate = LocalDate.now();
        String date_raw = curDate.toString();
        Date date = Date.valueOf(date_raw);
        CartDAO c = new CartDAO();
        Account a = new Account(1, "", "", 0, 0, 0, date, 0);
        Cart cart = new Cart();

        Category cate = new Category(1, "c");
        Category cate1 = new Category(2, "c");
        Category cate2 = new Category(3, "c");

        List<Product> listProduct = new ArrayList<>();
        Product p2 = new Product(41, 1, cate, "abc", "store", "image", "title", 0, 0, 0, 0, date_raw, date_raw, 0);
        Product p1 = new Product(42, 2, cate1, "abc", "store", "image", "title", 0, 0, 0, 0, date_raw, date_raw, 0);
        Product p3 = new Product(43, 3, cate2, "abc", "store", "image", "title", 0, 0, 0, 0, date_raw, date_raw, 0);
        Product p4 = new Product(45, 5, cate2, "seller", "store", "image", "title", 0, 0, 0, 0, "status", "des", 0);

        Item i1 = new Item(p4, 1);

        cart.initializeCartFromText("44:3-41:1-42:2", listProduct);
        cart.addItemIntoCart(i1);
        listProduct.add(p2);
        listProduct.add(p1);
        listProduct.add(p3);
        listProduct.add(p4);

        List<Category> listCate = new ArrayList<>();
        listCate.add(cate);
        listCate.add(cate1);
        listCate.add(cate2);

//        boolean ok = c.addOrder(a, cart, "0", "", "email", "addre", "note", 0, 0, 0, 0, 0, 0);
//        if (ok) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
//        List<String> searchQueries = new ArrayList();
//        searchQueries.add("as");
//        searchQueries.add("abfbg");
//        searchQueries.add("dưa");
//        searchQueries.add("bfg");
        List<Product> list = c.getRecommendedProducts(cart, null);
        for (Product product : list) {
            System.out.println(product);
        }
        String proid = "";
//
        if (cart.getItems().isEmpty()) {
            System.out.println("np");
        }

        for (int i = 0; i < cart.getItems().size(); i++) {
            proid += cart.getItems().get(0).getProduct().getPid();
            if (i != cart.getItems().size() - 1) {
                proid += ", ";
            }
        }
        System.out.println(proid);
    }
}
