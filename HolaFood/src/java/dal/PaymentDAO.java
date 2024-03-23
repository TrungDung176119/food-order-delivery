/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.profile.Account;

/**
 *
 * @author Dung
 */
public class PaymentDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static PaymentDAO i;

    public static PaymentDAO gI() {
        if (i == null) {
            i = new PaymentDAO();
        }
        return i;
    }

    public boolean addPaymentHistory(Account a, String payment_code, int amount, String info, String error_code, String vnpayQRCode, String bankCode, String payDate, String status) {
        int order_id = 0;
        String query1 = "SELECT TOP 1 order_id FROM Orders WHERE acc_id = ? ORDER BY order_id DESC";

        String query2 = "INSERT INTO [dbo].[Payment_History]\n"
                + "           ([order_id], [payment_code], [amount], [info], [error_code], [vnpayQRCode], [bankCode], [payDate], [status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps1 = connection.prepareStatement(query1);
            ps1.setInt(1, a.getAccid());
            rs = ps1.executeQuery();
            if (rs.next()) {
               order_id = rs.getInt("order_id");
            }
            ps = connection.prepareStatement(query2);
            ps.setInt(1, order_id);
            ps.setString(2, payment_code);
            ps.setInt(3, amount);
            ps.setString(4, info);
            ps.setString(5, error_code);
            ps.setString(6, vnpayQRCode);
            ps.setString(7, bankCode);
            ps.setString(8, payDate);
            ps.setString(9, status);
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
}
