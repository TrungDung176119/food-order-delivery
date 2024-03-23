/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.report.ReportRequest;
import model.report.ReportType;

/**
 *
 * @author Dung
 */
public class ReportDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ReportDAO i;

    public static ReportDAO gI() {
        if (i == null) {
            i = new ReportDAO();
        }
        return i;
    }

    public boolean addRequestReport(int acc_id, int report_id, int reportedthing_id, String report_description, String request_timestamp, int status, String proof, int type, int approver_id) {
        String query = "INSERT INTO [dbo].[Report_Requests]\n"
                + "           ([acc_id], [report_id], [reportedthing_id], [report_description], [request_timestamp], [status], [proof], [type], [approver_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, acc_id);
            ps.setInt(2, report_id);
            ps.setInt(3, reportedthing_id);
            ps.setString(4, report_description);
            ps.setString(5, request_timestamp);
            ps.setInt(6, status);
            ps.setString(7, proof);
            ps.setInt(8, type);
            ps.setInt(9, approver_id);
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

    public List<ReportRequest> showAllReportByType(int type) {
        List<ReportRequest> list = new ArrayList<>();
        String query = "SELECT r.[request_id], r.[acc_id], r.[report_id], rt.[report_name], r.[reportedthing_id], r.[report_description], r.[request_timestamp], r.[status], r.[proof], r.[type], r.[approver_id]\n"
                + "FROM [Report_Requests] r\n"
                + "JOIN [Report_Types] rt ON r.report_id = rt.report_id\n"
                + "WHERE r.[type] = ?";
        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReportRequest r = new ReportRequest();
                r.setRequest_id(rs.getInt("request_id"));
                r.setAcc_id(rs.getInt("acc_id"));
                r.setReportedthing_id(rs.getInt("reportedthing_id"));
                r.setReport_description(rs.getString("report_description"));
                r.setRequest_timestamp(rs.getString("request_timestamp"));
                r.setStatus(rs.getInt("status"));
                r.setProof(rs.getString("proof"));
                r.setType(rs.getInt("type"));
                r.setApproved_id(rs.getInt("approver_id"));

                ReportType rt = new ReportType();
                rt.setReport_id(rs.getInt("report_id"));
                rt.setReport_name(rs.getString("report_name"));
                r.setReport_reason(rt);
                list.add(r);
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

    public List<ReportType> showAllReportType() {
        List<ReportType> list = new ArrayList<>();
        String query = "SELECT [report_id], [report_name]\n"
                + "  FROM [dbo].[Report_Types]";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReportType r = new ReportType(rs.getInt(1),
                        rs.getString(2));
                list.add(r);
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

    public int ReportRequestCount(int type) {
        String query = "SELECT COUNT(*) AS count_of_rows\n"
                + "FROM [dbo].[Report_Requests]\n"
                + "WHERE [status] = 0 AND [type] = 1";
        String query2 = "SELECT COUNT(*) AS count_of_rows\n"
                + "FROM [dbo].[Report_Requests]\n"
                + "WHERE [status] = 0 AND [type] = 0";
        int count = 0;
        try {
            if (type == 1) {
                ps = connection.prepareStatement(query);
            } else if (type == 0) {
                ps = connection.prepareStatement(query2);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count_of_rows");
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
        return count;
    }

    public boolean UpdateRequest(int status, int approver_id, int request_id) {
        String query = "UPDATE [dbo].[Report_Requests]\n"
                + "   SET [status] = ?, [approver_id] = ?\n"
                + " WHERE [request_id] = ?";
        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, approver_id);
            ps.setInt(3, request_id);
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

    public int CountTheReportProductOfSeller(int seller_id) {
        int n = 0;
        String query = "SELECT \n"
                + "    COUNT(*) AS num_violations\n"
                + "FROM \n"
                + "    Product p \n"
                + "JOIN \n"
                + "    Seller s ON p.seller_id = s.seller_id\n"
                + "WHERE \n"
                + "    s.seller_id = ?\n"
                + "    AND p.[status] = N'Vi phạm';";
        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, seller_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
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
        return n;
    }

    public static void main(String[] args) {
        List<ReportRequest> r = ReportDAO.gI().showAllReportByType(1);
        for (ReportRequest rr : r) {
            System.out.println(rr);
        }
    }
}
