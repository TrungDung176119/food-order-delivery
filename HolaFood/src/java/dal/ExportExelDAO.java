/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.cart.Order;
import dal.DBContext;
import java.sql.SQLException;

/**
 *
 * @author hoang
 */
public class ExportExelDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    
    private static ExportExelDAO i;
    
    public static ExportExelDAO gI(){
        if(i == null){
            i = new ExportExelDAO();
        }
        return i;
    }

    public List getOrderByAcc_Id(int accid, String startDate, String endDate) {
        List<Order> list = new ArrayList<>();
        String query = "select order_id, acc_id, nickname, phone, email, [address], note, order_date, discount, total_price from Orders "
                + "where acc_id = " + accid + " and is_purchased = " + constant.ConstOrder.IS_PURCHASED+ " and (order_date >= '" + startDate + "' and order_date < dateadd(day, 1, '" + endDate +"'))";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Order(rs.getInt("order_id"),
                        rs.getInt("acc_id"),
                        rs.getString("nickname"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("note"),
                        rs.getTimestamp("order_date"),
                        rs.getInt("discount"),
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
    
    public static void main(String[] args) {
        List<Order> list = ExportExelDAO.gI().getOrderByAcc_Id(3, "2024-03-15", "2024-03-16");
        for (Order order : list) {
            System.out.println(order);
        }
    }

}
