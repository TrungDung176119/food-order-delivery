/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstSeller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.cart.Order;
import model.profile.Seller;

/**
 *
 * @author admin
 */
public class ManagerSellerDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ManagerSellerDAO i;

    public static ManagerSellerDAO gI() {
        if (i == null) {
            i = new ManagerSellerDAO();
        }
        return i;
    }

    public List<Seller> getAllSeller(int is_active) {
        List<Seller> list = new ArrayList<>();
        String query = "SELECT * FROM Seller ";
        if (is_active != 2) {
            query += " WHERE is_active = ?";
        }
        query += " ORDER BY seller_id DESC ";
        try {
            ps = connection.prepareStatement(query);
            if (is_active != 2) {
                ps.setInt(1, is_active);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Seller(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getFloat(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getString("image_store")));
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

    /**
     *
     * @param accid
     * @return
     */
    public Seller getSellerByAccId(int accid) {
        String query = "SELECT * FROM Seller "
                + "WHERE acc_id = ? AND is_active = " + ConstSeller.IS_ACTIVE;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Seller(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getFloat(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getString("image_store"));
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

    public Seller getSellerBySeller_Id(int seller_Id) {
        String query = "select * from Seller where seller_id = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, seller_Id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Seller(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getFloat(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getString("image_store"));
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

    public boolean setActiveSeller(int TYPE_ACTIVE_SELLER, int sellerid) {
        String query = "";
        if (TYPE_ACTIVE_SELLER != ConstSeller.NON_ACTIVE) {
            query += "UPDATE Seller SET is_active = " + TYPE_ACTIVE_SELLER
                    + " WHERE seller_id = ?";
        } else {
            query += "DELETE Seller WHERE seller_id = ?";
        }
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, sellerid);
//            ps.executeUpdate();
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
        ManagerSellerDAO mdao = new ManagerSellerDAO();
        Seller s = mdao.getSellerByAccId(4);
        Seller saa = mdao.getSellerBySeller_Id(3);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("no");
        }
    }
}
