/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstAccount;
import constant.ConstHome;
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
    
    public List<Seller> getAllSeller(int is_active, String keyword, int page_num) {
        List<Seller> list = new ArrayList<>();
        String query = "SELECT * FROM Seller ";
        if (is_active != ConstSeller.DEFALT) {
            query += " WHERE is_active = " + is_active;
            if (keyword != null && keyword.matches("\\D+")) {
                query += " AND store_name like ? ";
            }
            if (keyword != null && keyword.matches("\\d+")) {
                query += " AND ( seller_id = ? OR phone_store = ? )";
            }
        } else {
            if (keyword != null && keyword.matches("\\D+")) {
                query += " WHERE store_name like ? ";
            }
            if (keyword != null && keyword.matches("\\d+")) {
                query += " WHERE ( seller_id = ? OR phone_store = ? )";
            }
        }
        query += " ORDER BY seller_id DESC ";
//        if (page_num != -1) {
//            query += " ORDER BY seller_id offset " + (page_num - 1) * ConstHome.NUMBER_PAGINATION + " ROWS FETCH NEXT " + ConstHome.NUMBER_PAGINATION + " ROWS ONLY ";
//        }
        try {
            ps = connection.prepareStatement(query);
            
            if (is_active != ConstSeller.DEFALT) {
                if (keyword != null && keyword.matches("\\D+")) {
                    ps.setString(1, "%" + keyword.trim() + "%");
                }
                if (keyword != null && keyword.matches("\\d+")) {
                    ps.setInt(1, Integer.parseInt(keyword));
                    ps.setInt(2, Integer.parseInt(keyword));
                }
            } else {
                if (keyword != null && keyword.matches("\\D+")) {
                    ps.setString(1, "%" + keyword.trim() + "%");
                }
                if (keyword != null && keyword.matches("\\d+")) {
                    ps.setInt(1, Integer.parseInt(keyword));
                    ps.setInt(2, Integer.parseInt(keyword));
                }
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
                        rs.getString("image_store"),
                        rs.getString("note")));
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
                + "WHERE acc_id = ?";
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
                        rs.getString("image_store"),
                        rs.getString("note"));
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
    
    public Seller getSellerByAccId(int accid, int TYPE_ROLE) {
        String query = "SELECT * FROM Seller "
                + "WHERE acc_id = ? AND is_active = " + TYPE_ROLE;
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
                        rs.getString("image_store"),
                        rs.getString("note"));
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
    
    public boolean setActiveSeller(int TYPE_ACTIVE_SELLER, int sellerid, String note) {
        String query = "";
        query += "UPDATE Seller SET is_active = " + TYPE_ACTIVE_SELLER
                + " WHERE seller_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, sellerid);
            
            if (TYPE_ACTIVE_SELLER == ConstSeller.IS_ACTIVE) {
                ps.executeUpdate();
            }
            if (TYPE_ACTIVE_SELLER != ConstSeller.IS_ACTIVE) {
                String query2 = "UPDATE seller SET note = ? WHERE seller_id = ?";
                PreparedStatement ps3 = connection.prepareStatement(query2);
                ps3.setString(1, note);
                ps3.setInt(2, sellerid);
                ps.executeUpdate();
                ps3.executeUpdate();
            }
            
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
    
    public boolean changeRole(int TYPE_ROLE, int acc_id) {
        String query = "UPDATE Account SET role_id = " + TYPE_ROLE
                + " WHERE acc_id = " + acc_id;
        try {
            ps = connection.prepareStatement(query);
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
        ManagerSellerDAO mdao = new ManagerSellerDAO();
//        Seller s = mdao.getSellerByAccId(4);
//        Seller saa = mdao.getSellerBySeller_Id(3);
//        if (s != null) {
//            System.out.println(s);
//        } else {
//            System.out.println("no");
//        }
//        boolean set = mdao.changeRole(ConstAccount.ROLE_CUSTOMER, 6);
//        boolean set = mdao.setActiveSeller(ConstSeller.NON_ACTIVE, 7, "acasva");
//        if (set) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }

        List<Seller> l = mdao.getAllSeller(2, "", 2);
        for (Seller seller : l) {
            System.out.println(seller);
        }
    }
}
