/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstOrder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.cart.Order;
import model.cart.OrderStatus;
import model.profile.Feedback;

/**
 *
 * @author hoan
 */
public class OrderDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static OrderDAO i;

    private OrderDAO() {
    }

//    /**
//     * Use synchronized to avoid race condition but it reduces performances
//     * 
//     * @return 
//     */
//    public static synchronized OrderDAO gI() {
//        if (i == null) {
//            i = new OrderDAO();
//        }
//        return i;
//    }
    /**
     * Use Double-Checked Locking was used when instance has not initialize this
     * help reduce effect to performance
     *
     * @return
     */
    public static OrderDAO gI() {
        synchronized (OrderDAO.class) {
            if (i == null) {
                i = new OrderDAO();
            }
        }
        return i;
    }

    public List getAllOrders(int accid, int type, String keyword) {
        List<OrderStatus> list = new ArrayList<>();
        String query = "select o.order_id, od.detail_id, od.product_id, od.product_image, od.product_title, od.product_catename, \n"
                + "od.product_storename, od.product_oldprice, od.product_currentprice, od.product_status, \n"
                + "od.quantity, od.total_price, o.[is_accepted], o.is_delivered, od.is_feedback, o.is_purchased, o.order_date\n"
                //                + ",s.seller_id, a.username, o.is_shipped, od.detail_id, o.acc_id "
                + "from Orders o join OrderDetail od on o.order_id = od.order_id\n"
                + "join Product p on p.pid = od.product_id\n"
                + "join Account a on a.acc_id = o.acc_id\n"
                + "join Seller s on s.seller_id = p.seller_id\n"
                + "join Category c on c.id = p.category_id\n"
                + " where o.acc_id = ?\n";
        switch (type) {
            case 0:
                query += "";
                break;
            case 1:
                query += "AND o.is_accepted = " + ConstOrder.DEFAUT;
                break;
            case 2:
                query += "AND o.is_accepted = " + ConstOrder.IS_ACCEPTED + " and (o.is_delivered = " + ConstOrder.DEFAUT + " or o.is_delivered = " + ConstOrder.IS_DELIVERING + ")";
                break;
            case 3:
                query += "AND o.is_accepted = " + ConstOrder.IS_ACCEPTED + " and o.is_delivered = " + ConstOrder.IS_DELIVERED;
                break;
            case 4:
                query += "AND o.is_accepted = " + ConstOrder.IS_ACCEPTED + " and o.is_delivered = " + ConstOrder.IS_DELIVERED + " and od.is_feedback = " + ConstOrder.DEFAUT;
                break;
            case 5:
                query += "AND o.is_accepted = " + ConstOrder.IS_CANCELED + "  or o.is_accepted = " + ConstOrder.IS_NO_ACCEPTED_BY_SELLER;
                break;
            case 6:
                query += "AND o.is_accepted = " + ConstOrder.IS_ACCEPTED + " and o.is_delivered = " + ConstOrder.IS_DELIVERED + " and o.is_delivery =  " + ConstOrder.IS_RETURN_OR_REFUND;
                break;
        }
        if (keyword != null && keyword.matches("\\D+")) {
            query += " AND ( od.product_title like ? or od.product_storename like ? )  ";
        }
        if (keyword != null && keyword.matches("\\d+")) {
            query += " AND  o.order_id = ? ";
        }
        query += " ORDER BY o.order_id DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            if (keyword != null && keyword.matches("\\D+")) {
                ps.setString(2, "%" + keyword.trim() + "%");
                ps.setString(3, "%" + keyword.trim() + "%");
            }
            if (keyword != null && keyword.matches("\\d+")) {
                ps.setInt(2, Integer.parseInt(keyword));
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new OrderStatus(rs.getInt("order_id"),
                        rs.getInt("detail_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_image"),
                        rs.getString("product_title"),
                        rs.getString("product_catename"),
                        rs.getString("product_storename"),
                        rs.getString("product_status"),
                        rs.getInt("product_oldprice"),
                        rs.getInt("product_currentprice"),
                        rs.getInt("quantity"),
                        rs.getInt("total_price"),
                        rs.getInt("is_accepted"),
                        rs.getInt("is_delivered"),
                        rs.getInt("is_feedback"),
                        rs.getInt("is_purchased"),
                        rs.getTimestamp("order_date")));
            }

//            Map<Integer, Order> orderMap = new HashMap<>();
//            while (rs.next()) {
//                int orderId = rs.getInt("order_id");
//                if (!orderMap.containsKey(orderId)) {
//                    Order order = new Order(
//                            orderId,
//                            rs.getInt("acc_id"),
//                            rs.getInt("seller_id"),
//                            rs.getString("username"),
//                            rs.getInt("is_shipped"),
//                            rs.getInt("is_delivered"),
//                            rs.getInt("is_accepted"),
//                            rs.getInt("is_feedback"),
//                            rs.getInt("is_purchased"),
//                            rs.getTimestamp("order_date"),
//                            rs.getInt("total_price"),
//                            new ArrayList<>()
//                    );
//                    orderMap.put(orderId, order);
//                    list.add(order);
//                }
//                OrderDetail orderDetail = new OrderDetail(
//                        rs.getInt("detail_id"),
//                        rs.getInt("order_id"),
//                        rs.getInt("product_id"),
//                        rs.getString("product_title"),
//                        rs.getString("product_image"),
//                        rs.getString("product_catename"),
//                        rs.getString("product_storename"),
//                        rs.getString("product_status"),
//                        rs.getInt("product_oldprice"),
//                        rs.getInt("product_currentprice"),
//                        rs.getInt("quantity"),
//                        rs.getInt("total_price")
//                );
//                orderMap.get(orderId).getOrderDetails().add(orderDetail);
//            }
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

    public Order getOrderById(String orderid) {
        String query = "SELECT * FROM Orders WHERE order_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, orderid);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Order(rs.getInt("order_id"),
                        rs.getInt("acc_id"),
                        rs.getInt("seller_id"),
                        rs.getString("nickname"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("note"),
                        rs.getInt("is_shipped"),
                        rs.getInt("is_delivered"),
                        rs.getInt("is_accepted"),
                        rs.getInt("is_purchased"),
                        rs.getTimestamp("order_date"),
                        rs.getInt("total_price"));
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

    public boolean setStatusOrder(String orderid, final int TYPE_STATUS) {
        String query = "";

        if (TYPE_STATUS != ConstOrder.IS_FEEDBACKED) {
            query = "UPDATE Orders ";

            switch (TYPE_STATUS) {
                case ConstOrder.DEFAUT:
                    query += "SET [is_feedback] = " + ConstOrder.DEFAUT;
                    break;
                case ConstOrder.IS_CANCELED:
                    query += "SET [is_accepted] = " + ConstOrder.IS_CANCELED;
                    break;
                case ConstOrder.IS_NO_ACCEPTED_BY_SELLER:
                    query += "SET [is_accepted] = " + ConstOrder.IS_NO_ACCEPTED_BY_SELLER;
                    break;
                case ConstOrder.IS_ACCEPTED:
                    query += "SET [is_accepted] = " + ConstOrder.IS_ACCEPTED;
                    break;
                case ConstOrder.IS_DELIVERING:
                    query += "SET [is_delivered] = " + ConstOrder.IS_DELIVERING;
                    break;
                case ConstOrder.IS_DELIVERED:
                    query += "SET [is_delivered] = " + ConstOrder.IS_DELIVERED;
                    break;
                case ConstOrder.IS_PURCHASED:
                    query += "SET [is_purchased] = " + ConstOrder.IS_PURCHASED;
                    break;
                default:
                    break;
            }
            query += " WHERE order_id = ?";
        } else {
            query += "UPDATE OrderDetail SET [is_feedback] = " + ConstOrder.IS_FEEDBACKED + " WHERE detail_id = ?";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, orderid);
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

    public boolean feedbackByUser(int accid, String cname, int pid, int rating, String describe, String delivery,
            String standard, String content, String image, int incognito, int status, String username) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = currentDateTime.format(formatter);

        String query = "INSERT INTO [dbo].[FeedBack]\n"
                + " ([product_id],[cate_name],[acc_id] ,[feed_date] ,[rate]\n"
                + " ,[describe] ,[delivery] ,[standard] ,[content]\n"
                + " ,[image] ,[incognito], [status], [username])\n"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setString(2, cname);
            ps.setInt(3, accid);
            ps.setString(4, dateTime);
            ps.setInt(5, rating);
            ps.setString(6, describe);
            ps.setString(7, delivery);
            ps.setString(8, standard);
            ps.setString(9, content);
            ps.setString(10, image);
            ps.setInt(11, incognito);
            ps.setInt(12, status);
            ps.setString(13, username);
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

    public List<Feedback> getAllFeedback(String pid, int rating, int roleid) {
        List<Feedback> list = new ArrayList<>();
        String query = "select * from FeedBack where product_id = ? ";

        if (roleid != 3 || roleid == 0) {
            query += " and status = 1";
        }
        if (rating != 0) {
            query += " and rate = " + rating;
        }
        query += " Order By feed_id DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pid);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Feedback(rs.getInt("feed_id"),
                        rs.getInt("product_id"),
                        rs.getString("cate_name"),
                        rs.getInt("acc_id"),
                        rs.getString("feed_date"),
                        rs.getInt("rate"),
                        rs.getString("describe"),
                        rs.getString("delivery"),
                        rs.getString("standard"),
                        rs.getString("content"),
                        rs.getString("image"),
                        rs.getInt("incognito"),
                        rs.getInt("status"),
                        rs.getString("username")));
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

    public boolean setStatusFeedback(String feed_id, int typeStatus) {
        String query = "UPDATE Feedback";

        if (typeStatus == 0) {
            query += " SET status = 0 WHERE feed_id = ?";
        } else {
            query += " SET status = 1 WHERE feed_id = ?";
        }
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, feed_id);
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

    public boolean updateOrderAddress(int orderid, String nickname,  String phone, String address, String note) {
        String query = "UPDATE Orders SET nickname = ?, phone = ?, [address] = ?, note = ? "
                + " WHERE order_id = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nickname);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, note);
            ps.setInt(5, orderid);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
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
        OrderDAO od = new OrderDAO();
//        List<Order> list = od.getAllOrders(7, 0, null);
//        for (Order orderStatus : list) {
//            System.out.println(orderStatus);
//        }

//        boolean setO = od.setStatusOrder("21", ConstOrder.IS_FEEDBACKED);
//        if (setO) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
//        List<Feedback> list1 = od.getAllFeedback("20", 5, 3);
//        for (Feedback feedback : list1) {
//            System.out.println(feedback);
//        }
//        int cnt = od.getNumberOfRate("36", 5);
//        boolean set = od.setAvgRatingPro(cnt, 40);
//        if (set) {
//            System.out.println("ok");
//        } else {
//            System.out.println("none");
//        }
//        System.out.println(cnt);
//        boolean hide = od.setStatusFeedback("19", 1);
//        if (hide) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
        Order o = od.getOrderById("4");
        if (o != null) {
            System.out.println(o);
        } else {
            System.out.println("no");
        }
    }
}
