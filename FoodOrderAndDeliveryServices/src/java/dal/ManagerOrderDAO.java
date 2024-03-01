/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstOrder;
import constant.ConstSeller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.cart.Order;
import model.cart.ViewOrderDetail;
import model.profile.Seller;

/**
 *
 * @author admin
 */
public class ManagerOrderDAO extends DBContext {
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    private static ManagerOrderDAO mdao;
    
    public static synchronized ManagerOrderDAO gI() {
        if (mdao == null) {
            mdao = new ManagerOrderDAO();
        }
        return mdao;
    }
    
    public List getOrdersBySellerId(String type, String text, int select, int seller_id) {
        List<Order> list = new ArrayList<>();
        String query = "select DISTINCT o.order_id, o.seller_id, a.acc_id, a.username,\n"
                + "o.phone, o.email, o.address,o.note ,o.total_price, \n"
                + "o.is_shipped, o.is_accepted, o.is_delivered, o.is_purchased,\n"
                + "o.order_date\n"
                + "from OrderDetail od join orders o on od.order_id = o.order_id\n"
                + "join Account a on a.acc_id = o.acc_id\n"
                + "join Seller s on s.acc_id = a.acc_id\n"
                + "where o.seller_id = " + seller_id;
        
        if (type != null) {
            switch (type) {
                case "1":  // order not accepted yet
                    query += " and o.is_accepted = " + ConstOrder.DEFAUT;
                    break;
                case "2":  // order not delivered yet or delivering
                    query += " and o.is_accepted = " + ConstOrder.IS_ACCEPTED + " and (  o.is_delivered = " + ConstOrder.DEFAUT + " or  o.is_delivered = " + ConstOrder.IS_DELIVERING + " ) ";
                    break;
                case "3":    // order delivered 
                    query += " and o.is_accepted = " + ConstOrder.IS_ACCEPTED + " and  o.is_delivered = " + ConstOrder.IS_DELIVERED;
                    break;
                case "4":    // order purchased 
                    query += " and o.is_accepted = " + ConstOrder.IS_ACCEPTED + " and  o.is_purchased = " + ConstOrder.IS_PURCHASED;
                    break;
                case "5":    // order canceled 
                    query += " and( o.is_accepted = " + ConstOrder.IS_CANCELED + " or  o.is_accepted = " + ConstOrder.IS_NO_ACCEPTED_BY_SELLER + " )";
                    break;
                case "6":    // order return or refund 
                    query += " and o.is_delivered = " + ConstOrder.IS_RETURN_OR_REFUND;
                    break;
                case "7":    // order delivered no success 
                    query += " and od.is_delivered = " + ConstOrder.IS_DELIVERED_NO_SUCCESS;
                    break;
            }
        }
        if (text != null && select != -1) {
            switch (select) {
                case 1:
                    query += " and (a.username like ?)";
                    break;
                case 2:
                    query += " and p.title like ?";
                    break;
                case 3:
                    query += " and o.order_id = ?";
                    break;
                default:
                    break;
            }
        }
        query += " order by o.order_id DESC";
        try {
            ps = connection.prepareStatement(query);
            if (text != null && select != -1 && select != 3) {
                ps.setString(1, "%" + text + "%");
            }
            if (text != null && select != -1 && select == 3) {
                ps.setInt(1, Integer.parseInt(text));
            }
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Order(rs.getInt("order_id"),
                        rs.getInt("acc_id"),
                        rs.getInt("seller_id"),
                        rs.getString("username"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("note"),
                        rs.getInt("is_shipped"),
                        rs.getInt("is_delivered"),
                        rs.getInt("is_accepted"),
                        rs.getInt("is_purchased"),
                        rs.getTimestamp("order_date"),
                        rs.getInt("total_price")));
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
    
    
    public List<ViewOrderDetail> viewOrderDetailByOid(String order_id, int seller_id) {
        List<ViewOrderDetail> list = new ArrayList<>();
        String query = "select od.detail_id, o.order_id, o.seller_id, od.product_id, od.product_image, od.product_title, \n"
                + "od.product_status, od.product_catename, od.product_currentprice, od.product_oldprice,\n"
                + "od.quantity, o.nickname, o.phone, o.email, o.[address], o.note, o.is_shipped, o.total_price\n"
                + "from OrderDetail od join orders o on od.order_id = o.order_id\n"
                + "join Account a on a.acc_id = o.acc_id\n"
                + "join Seller s on s.acc_id = a.acc_id\n"
                + "where o.seller_id = " + seller_id + " and o.order_id = " + order_id
                + " order by od.detail_id desc";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new ViewOrderDetail(rs.getInt("detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("seller_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_image"),
                        rs.getString("product_title"),
                        rs.getString("product_status"),
                        rs.getString("product_catename"),
                        rs.getInt("product_currentprice"),
                        rs.getInt("product_oldprice"),
                        rs.getInt("quantity"),
                        rs.getString("nickname"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("note"),
                        rs.getInt("is_shipped"),
                        rs.getInt("total_price")));
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


    
    public boolean deliveryAllOrder(int accid) {
        String query = "UPDATE Orders SET is_delivered = " + ConstOrder.IS_DELIVERING
                + " WHERE acc_id = ? AND is_accepted = " + ConstOrder.IS_ACCEPTED
                + " AND is_delivered = " + ConstOrder.DEFAUT;
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
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
    
    public static void main(String[] args) {
        ManagerOrderDAO mdao = new ManagerOrderDAO();
//        List<Account> list = mdao.getAllAccount();
//        for (Account account : list) {
//            System.out.println(account);
//        }

   
//        List<Order> list = mdao.getOrdersBySellerId("", "", -1, s.getSeller_id());
//        List<ViewOrderDetail> list = mdao.viewOrderDetailByOid("9", 2);
//        for (ViewOrderDetail orderDetail : list) {
//            System.out.println(orderDetail);
//        }
//        LocalDateTime now = LocalDateTime.MAX.now();
//
//        // Định dạng thời gian
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        // Chuyển định dạng và in ra màn hình
//        String formattedDateTime = now.format(formatter);
//        System.out.println("Thời gian hiện tại: " + formattedDateTime);
    }
}
