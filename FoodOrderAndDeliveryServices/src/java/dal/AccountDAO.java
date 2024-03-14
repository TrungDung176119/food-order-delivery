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
import java.util.ArrayList;
import java.util.List;
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

    public boolean manageAddress(final String TYPE_ACTION, int accid, String nickname, String phone_addr, String address, String note, String status, String address_id) {

        String query = "";
        if (TYPE_ACTION.equals(ConstAccount.ACTION_INSERT)) {
            query = "INSERT INTO [dbo].[Account_Address]([acc_id], [nickname], [phone_addr], [address], [note], [status])\n"
                    + "     VALUES(?, ?, ?, ?, ?, ?)";
        } else if (TYPE_ACTION.equals(ConstAccount.ACTION_UPDATE)) {
            query = "UPDATE [dbo].[Account_Address]\n"
                    + "   SET [acc_id] = ?\n"
                    + "      ,[nickname] = ?\n"
                    + "      ,[phone_addr] = ?\n"
                    + "      ,[address] = ?\n"
                    + "      ,[note] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE [address_id] = ?";
        } else if (TYPE_ACTION.equals(ConstAccount.ACTION_DELETE)) {
            query = "DELETE FROM [dbo].[Account_Address]\n"
                    + "      WHERE [address_id] = ?";
        }

        try {
            ps = connection.prepareStatement(query);
            if (TYPE_ACTION.equals(ConstAccount.ACTION_DELETE)) {
                ps.setString(1, address_id);
            } else {
                ps.setInt(1, accid);
                ps.setString(2, nickname);
                ps.setString(3, phone_addr);
                ps.setString(4, address);
                ps.setString(5, note);
                ps.setString(6, status);
                if (TYPE_ACTION.equals(ConstAccount.ACTION_UPDATE)) {
                    ps.setString(7, address_id);
                }
            }
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

    public List<AccountAddress> getAllAccountAddress(int accid) {
        List<AccountAddress> list = new ArrayList<>();
        String query = "SELECT [address_id], [acc_id], [nickname], [phone_addr], [address], [note], [status]\n"
                + "FROM [dbo].[Account_Address]\n"
                + "WHERE [acc_id] = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();

            while (rs.next()) {
                AccountAddress a = new AccountAddress(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                list.add(a);
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

    public AccountAddress getAccAddressByAddressid(String address_id) {
        String query = ConstAccount.QUERY_SELECT_ACCOUNT_ADDRESS + address_id;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new AccountAddress(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
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

    public void Seller(String store_name, String email_store, String address_store, String phone_store, int acc_id) {
        try {
            String query = "INSERT INTO Seller (store_name, email_store, address_store, phone_store, store_opentime, acc_id) \n"
                    + "VALUES (?, ?, ?, ?,GETDATE())";

            ps = connection.prepareStatement(query);
            ps.setString(1, store_name);
            ps.setString(2, email_store);
            ps.setString(3, address_store);
            ps.setString(4, phone_store);
            ps.setInt(5, acc_id);

            ps.execute();
        } catch (Exception e) {
            System.out.println("add:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AccountDAO ad = new AccountDAO();
        ad.manageAddress(ConstAccount.ACTION_DELETE, 0, null, null, null, null, null, "3");
    }
}
