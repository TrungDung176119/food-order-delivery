/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.ConstAccount;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.profile.Account;
import model.profile.AccountAddress;
import model.profile.AccountDetail;
import model.profile.Seller;

/**
 *
 * @author admin
 */
public class AccountDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static AccountDAO i;

    public static AccountDAO gI() {
        if (i == null) {
            i = new AccountDAO();
        }
        return i;
    }

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
                        rs.getDate(7),
                        rs.getInt(8));
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

    public boolean signUp(String user, String pass, int roleid, int isBlock, int coin, int status) {
        String query = "INSERT INTO ACCOUNT (username, [password], [role_id], is_block, coin, [create_time], [status])"
                + " VALUES (?, ?, ?, ?, ?,GETDATE(),?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, roleid);
            ps.setInt(4, isBlock);
            ps.setInt(5, coin);
            ps.setInt(6, status);
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

    public boolean editAddress(final String TYPE_ACTION, int accid, String nickname, String phone_addr, String address, String note, String status) {

        String query = "";
        if (TYPE_ACTION.equals(ConstAccount.ACTION_INSERT)) {
            query = "INSERT INTO Account_Address ( nickname, phone_addr, address, note, status, acc_id)\n"
                    + "VALUES ( ?, ?, ?, ?, ?, ?)";
        } else if (TYPE_ACTION.equals(ConstAccount.ACTION_UPDATE)) {
            query = "UPDATE Account_Address SET nickname = ?, phone_addr = ?, address = ?, note = ?, status = ?\n"
                    + "WHERE acc_id = ?";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, nickname);
            ps.setString(2, phone_addr);
            ps.setString(3, address);
            ps.setString(4, note);
            ps.setString(5, status);
            ps.setInt(6, accid);

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
                        rs.getDate(7),
                        rs.getInt(8));
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

    public AccountAddress getAccAddressByid(int accid) {
        String query = ConstAccount.QUERY_SELECT_ACCOUNT_ADDRESS + accid;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new AccountAddress(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
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

    public boolean isUsernameExist(String user) {
        String query = "Select * from ACCOUNT where username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
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

    public boolean changePassword(int accid, String password) {
        String query = "UPDATE ACCOUNT SET [password] = ? "
                + "WHERE acc_id = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setInt(2, accid);
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

    public AccountDetail getAccDetailByEmail(String email, int accid) {
        String query = "Select * from ACCOUNT_DETAILS where email = ? ";

        if (accid != 0) {
            query += "and acc_id != ?";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            if (accid != 0) {
                ps.setInt(2, accid);
            }
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

    public boolean isGoogleAccountExist(String user) {
        String query = "Select * from ACCOUNT where username = ? and [status] = 1";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
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

    public boolean Seller(int acc_id, String store_name, String email_store, String address_store, String phone_store) {
        String query = "INSERT INTO Seller (acc_id, store_name, email_store, address_store, phone_store, store_opentime) \n"
                + "VALUES (?,?, ?, ?, ?,GETDATE())";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, acc_id);
            ps.setString(2, store_name);
            ps.setString(3, email_store);
            ps.setString(4, address_store);
            ps.setString(5, phone_store);
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

    public boolean isSeller(int acc_id) {
        String query = "SELECT * FROM Seller WHERE acc_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, acc_id);
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

    public static void main(String[] args) {
        AccountDAO ad = new AccountDAO();

//        Account acc = ad.login("admin", "password123");
//        if (acc != null) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
//        boolean a = ad.editProfile(ConstAccount.ACCTION_UPDATE, 2, "avdvsdv", "â", "sac", "sa", 0, new Date(12 - 11 - 2003));
//        AccountAddress a = ad.getAccAddressByid(3);
//        if (a) {
//            System.out.println(a);
//        } else {
//            System.out.println("no");
//        }
//        AccountDetail acc = ad.getAccDetailByEmail("hoanpham12112003@gmail.com", 0);
    AccountDetail acc = ad.getAccDetailByid(3);
        if (acc != null) {
            System.out.println(acc);
        } else {
            System.out.println("null");
        }
//        boolean change = ad.changePassword(11, "123");
//        if (change) {
//            System.out.println("ok");
//        } else {
//            System.out.println("none");
//        }
    }
}
