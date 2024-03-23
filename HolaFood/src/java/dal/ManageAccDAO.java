/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.profile.Account;

/**
 *
 * @author admin
 */
public class ManageAccDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ManageAccDAO mdao;

    public static synchronized ManageAccDAO gI() {
        if (mdao == null) {
            mdao = new ManageAccDAO();
        }
        return mdao;
    }

    public List<Account> getAllNormalAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account\n"
                + "Where role_id = 1 or role_id = 2";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getInt(8)));
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

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from account";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getInt(8)));
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
    
     public boolean manageAccount(final String TYPE_ACTION, String username, String password, String role, String isBlock, String coin, String createTime, String acc_id) {
        String query = "";
        switch (TYPE_ACTION) {
            case ConstAccount.ACTION_INSERT:
                query = "INSERT INTO Account (username, password, role_id, is_block, coin, create_time)\n"
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                break;
            case ConstAccount.ACTION_UPDATE:
                query = "UPDATE Account SET username = ?, password = ?, role_id = ?, is_block = ?\n"
                        + "WHERE acc_id = ?";
                break;
            case ConstAccount.ACCTION_BLOCK:
                query = "UPDATE Account SET is_block = ?\n"
                        + "WHERE acc_id = ?";
                break;
            default:
                break;
        }
        
        try {
            ps = connection.prepareStatement(query);
            if (TYPE_ACTION.equals(ConstAccount.ACCTION_BLOCK)) {
                ps.setString(1, isBlock);
                ps.setString(2, acc_id);
            } else {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);
                ps.setString(4, isBlock);
                if (TYPE_ACTION.equals(ConstAccount.ACTION_UPDATE)) {
                    ps.setString(5, acc_id);
                } else if (TYPE_ACTION.equals(ConstAccount.ACTION_INSERT)) {
                    ps.setString(5, coin);
                    ps.setString(6, createTime);
                }
            }
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
    
}
