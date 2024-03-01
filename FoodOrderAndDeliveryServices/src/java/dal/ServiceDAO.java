/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.services.Notify;

/**
 *
 * @author admin
 */
public class ServiceDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ServiceDAO i;

    public static ServiceDAO gI() {
        if (i == null) {
            i = new ServiceDAO();
        }
        return i;
    }

    /**
     * Notifications
     *
     * @param accid
     * @return
     */
    public List<Notify> getNotificationByAccID(int accid) {
        List<Notify> list = new ArrayList<>();
        String query = "select top 18 * from Notifications where acc_id = ? and is_hide = 0 \n";
        query += " Order By notify_id DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Notify(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)));
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

    public boolean addNewNotify(int accid, String image, String title, String message, int is_read, int is_hide, String url) {
        String query = "INSERT INTO Notifications (acc_id, [image], [title], [message], [timestamp], [is_read], [is_hide], [url]) \n"
                + "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            ps.setString(2, image);
            ps.setString(3, title);
            ps.setString(4, message);
            ps.setInt(5, is_read);
            ps.setInt(6, is_hide);
            ps.setString(7, url);
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

    public boolean setReadAllNotification(int accid) {
        String query = "UPDATE Notifications SET is_read = 1 \n"
                + "WHERE acc_id = ? and is_read = 0";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
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

    /**
     *
     * @param accid
     * @param seacrh_query
     * @return
     */
    public boolean addNewSearchHistory(int accid, String seacrh_query) {
        String query = "INSERT INTO SearchHistory (acc_id, search_query, search_timestamp)\n"
                + "VALUES (?, ?, GETDATE())";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            ps.setString(2, seacrh_query);
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

    public List<String> getListSearchQueries(int accid) {
        List<String> list = new ArrayList<>();

        String query = "SELECT top 4 search_query FROM SearchHistory WHERE acc_id = ? "
                + "ORDER BY his_search_id DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("search_query"));
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
        ServiceDAO ndao = new ServiceDAO();
        List<Notify> l = ndao.getNotificationByAccID(4);
//        List<SearchHistory> l = ndao.getListSearchHis(1);
        if (l != null) {
            System.out.println(l);
        } else {
            System.out.println("no");
        }
//        boolean addNo = ndao.addNewNotify(4, "", "", "", 0, 0, "");
//        boolean addNo = ndao.addNewSearchHistory(1, "bo");
//        if (addNo) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
//        boolean read = ndao.setReadAllNotification(3);
//        if (read) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }

//        List<String> searchQueries = new ArrayList();
//        
//        searchQueries.add("as");
//        searchQueries.add("abfbg");
//        searchQueries.add("rws");
//        searchQueries.add("bfg");
//        
//        String params = String.join(",", Collections.nCopies(searchQueries.size(), "?"));
//        
//        String query = "SELECT * FROM Product WHERE title NOT IN (" + params + ")";
//        
//        System.out.println(query);
    }
}
