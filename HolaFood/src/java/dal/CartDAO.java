/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.cart.Item;
import model.cart.Order;
import model.product.Category;
import model.product.Product;
import model.profile.Account;
import service.Cart;

/**
 *
 * @author admin
 */
public class CartDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean addOrder(Account a, Cart cart, String nickname, String phone, String email, String address,
            String note, int is_delivered, int is_accepted, int is_feedback, int is_purchased, int discount) {
        LocalDate curDate = LocalDate.now();
        String date_raw = curDate.toString();
        Date date = Date.valueOf(date_raw);

        try {
            String query = "INSERT INTO [dbo].[Orders] ([acc_id] ,[nickname],[phone] ,[email],[address] ,[note] ,[order_date] \n"
                    + ",[is_delivered] ,[is_accepted] ,[is_feedback], [is_purchased] ,[discount] ,[total_price])"
                    + "  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, a.getAccid());
            ps.setString(2, nickname);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, address);
            ps.setString(6, note);
            ps.setDate(7, date);
            ps.setInt(8, is_delivered);
            ps.setInt(9, is_accepted);
            ps.setInt(10, is_feedback);
            ps.setInt(11, is_purchased);
            ps.setInt(12, discount);
            ps.setInt(13, cart.getTotalMoney());

            ps.executeUpdate();
            // take id of order table that have just added
            String query1 = "SELECT TOP 1 order_id FROM Orders ORDER BY order_id DESC";
            PreparedStatement ps1 = connection.prepareStatement(query1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("order_id");
                for (Item i : cart.getItems()) {
                    String query2 = "INSERT INTO OrderDetail (order_id, product_id, quantity, price, total_price)"
                            + "VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement ps2 = connection.prepareStatement(query2);
                    ps2.setInt(1, oid);
                    ps2.setInt(2, i.getProduct().getPid());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setInt(4, i.getProduct().getCurrent_price());
                    ps2.setInt(5, i.getQuantity() * i.getProduct().getCurrent_price());
                    ps2.executeUpdate();
                }
            }
            // update stock of product 
            String sql3 = "UPDATE product SET number_in_stock = number_in_stock - ?,  "
                    + "amount_of_sold = amount_of_sold + ?\n"
                    + "where id = ?";
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                ps3.setInt(1, i.getQuantity());
                ps3.setInt(2, i.getQuantity());
                ps3.setInt(3, i.getProduct().getPid());
                ps3.executeUpdate();
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

    public static void main(String[] args) {
        LocalDate curDate = LocalDate.now();
        String date_raw = curDate.toString();
        Date date = Date.valueOf(date_raw);
        CartDAO c = new CartDAO();
        Account a = new Account(1, "", "", 0, 0, 0, date);
        Cart cart = new Cart();
        Order order = new Order();

        Category cate = new Category(0, "c");

        List<Product> listProduct = new ArrayList<>();
        Product p2 = new Product(1, 0, cate, date_raw, date_raw, date_raw, date_raw, 0, 0, 0, 0, date_raw, date_raw, 0);
        Product p1 = new Product(2, 0, cate, date_raw, date_raw, date_raw, date_raw, 0, 0, 0, 0, date_raw, date_raw, 0);

        cart.initializeCartFromText("1:1", listProduct);
        listProduct.add(p2);
        listProduct.add(p1);
        
        boolean ok = c.addOrder(a, cart, "0", "","email", "addre", "note", 0, 0, 0, 0, 0);
        if (ok) {
            System.out.println("ok");
        } else {
            System.out.println("no");
        }

    }
}
