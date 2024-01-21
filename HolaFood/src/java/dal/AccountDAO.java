/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstAccount;
import constant.ConstHome;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.profile.Account;
import model.profile.AccountAddress;
import model.profile.AccountDetail;

/**
 *
 * @author admin
 */
public class AccountDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     *
     * @param user
     * @param pass
     * @return
     */
    public Account login(String user, String pass) {
        String query = "select * from account\n"
                + "where username = ? and [password] = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDate(7));
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

    public boolean signUp(String user, String pass, int isAdmin, int isBlock) {

        return false;
    }

    public boolean editProfile(final String TYPE_ACTION, int accid, String nickname, String image, String email, String phone, int gender, Date dob) {
        String query = "";

        if (TYPE_ACTION.equals(ConstAccount.ACTION_INSERT)) {
            query = "INSERT INTO ACCOUNT_DETAILS ( nickname, image, email, phone, gender, dateOfBirth, acc_id)\n"
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        } else if (TYPE_ACTION.equals(ConstAccount.ACTION_UPDATE)) {
            query = "UPDATE ACCOUNT_DETAILS SET nickname = ?, image= ?, email = ?, phone = ?, gender = ?, dateOfBirth = ?\n"
                    + "WHERE acc_id = ?";
        }
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nickname);
            ps.setString(2, image);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setInt(5, gender);
            ps.setDate(6, dob);
            ps.setInt(7, accid);

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

    public boolean manageAddress(final String TYPE_ACTION, String addressId, int accId, String nickname, String phoneAddr, String address, String note, String status) {

        String query = "";
        if (TYPE_ACTION.equals(ConstAccount.ACTION_INSERT)) {
            query = "INSERT INTO Account_Address (nickname, phone_addr, address, note, status, acc_id)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
        } else if (TYPE_ACTION.equals(ConstAccount.ACTION_UPDATE)) {
            query = "UPDATE Account_Address SET nickname = ?, phone_addr = ?, address = ?, note = ?, status = ?\n"
                    + "WHERE address_id = ?";
        } else if (TYPE_ACTION.equals(ConstAccount.ACTION_DELETE)) {
            query = "DELETE FROM Account_Address WHERE address_id = ?";
        }

        try {
            ps = connection.prepareStatement(query);
            if (TYPE_ACTION.equals(ConstAccount.ACTION_DELETE)) {
                ps.setString(1, addressId);
            } else {
                ps.setString(1, nickname);
                ps.setString(2, phoneAddr);
                ps.setString(3, address);
                ps.setString(4, note);
                ps.setString(5, status);

                if (TYPE_ACTION.equals(ConstAccount.ACTION_INSERT)) {
                    ps.setInt(6, accId);
                } else if (TYPE_ACTION.equals(ConstAccount.ACTION_UPDATE)) {
                    ps.setString(6, addressId);
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

    public boolean isAccountExist(int accid, final String TYPE_ACCOUNT) {
        String query = "";
        if (TYPE_ACCOUNT.equals(ConstAccount.IS_ACCOUNT)) {
            query = ConstAccount.QUERY_SELECT_ACCOUNT + accid;
        } else if (TYPE_ACCOUNT.equals(ConstAccount.IS_ACCOUNT_DETAIL)) {
            query = ConstAccount.QUERY_SELECT_ACCOUNT_DETAIL + accid;
        } else if (TYPE_ACCOUNT.equals(ConstAccount.IS_ACCOUNT_ADDRESS)) {
            query = ConstAccount.QUERY_SELECT_ACCOUNT_ADDRESS + accid;
        }
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
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

    public Account getAccountByid(int accid) {
        String query = ConstAccount.QUERY_SELECT_ACCOUNT + accid;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDate(7));
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

    public AccountDetail getAccDetailByid(int accid) {
        String query = ConstAccount.QUERY_SELECT_ACCOUNT_DETAIL + accid;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new AccountDetail(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7));
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

    public List<AccountAddress> getAccAddressByid(int accid) {
        List<AccountAddress> addressList = new ArrayList<>();
        String query = ConstAccount.QUERY_SELECT_ACCOUNT_ADDRESS + accid;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                AccountAddress address = new AccountAddress(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
                addressList.add(address);
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
        return addressList;
    }

    public AccountAddress getAccAddressByAddressId(int address_id) {
        String query = "Select * from ACCOUNT_ADDRESS where address_id = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, address_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new AccountAddress(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
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

    public static void main(String[] args) {
        AccountDAO ad = new AccountDAO();

//        Account acc = ad.login("admin", "password123");
//        if (acc != null) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
//        boolean a = ad.editAddress(ConstAccount.ACTION_INSERT, 3, "asasd", "", "", "22", "");
    }
}
