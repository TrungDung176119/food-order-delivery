/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.menu.Menu;
import model.product.Category;
import model.product.Product;

/**
 *
 * @author anhdu
 */
public class ManagerMenuDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private static ManagerMenuDAO i;

    public static ManagerMenuDAO gI() {
        if (i == null) {
            i = new ManagerMenuDAO();
        }
        return i;
    }

    public List<Menu> getAllMenuOfSeller(int s_id) {
        List<Menu> list = new ArrayList<>();
        String query = "select * from Menu where seller_id = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, s_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Menu(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
                
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

    public void CreateNewMenu(int s_id) {
        try {
            String sql = "INSERT INTO [dbo].[Menu]\n"
                    + "           ([seller_id]\n"
                    + "           ,[status]\n"
                    + "           ,[menu_date]"
                    + "           ,[menu_name])\n"
                    + "     VALUES (?,0,GETDATE(),'New Menu')";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, s_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddToMenu(int p_id, int m_id) {
        try {
            String sql = "INSERT INTO [dbo].Menu_Item\n"
                    + "           (menu_id\n"
                    + "           ,product_id)\n"
                    + "     VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, p_id);
            ps.setInt(2, m_id);
            ps.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public Menu getMenuIsShow(int s_id) {
        String query = "select * from Menu where status = 1 and seller_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, s_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setMenuId(rs.getInt("menu_id"));
                m.setMenuDate(rs.getDate("menu_date"));
                m.setSellerId(rs.getInt("seller_id"));
                m.setStatus(rs.getInt("status"));
                m.setMenuName(rs.getString("menu_name"));
                m.setSaleOff(rs.getInt("sale_off"));
                return m;
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

    public List<Product> getProductFromMenuId(int m_id) {
        List<Product> list = new ArrayList<>();

        String query = "select p.pid, p.seller_id, s.store_name as seller_name, s.address_store,\n"
                + "p.[image], p.title, p.old_price, p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
                + "p.describe, p.rating, p.category_id as cid, c.[name] as cname \n"
                + "from Menu as m join Menu_Item as mi \n"
                + "on m.menu_id = mi.menu_id\n"
                + "join Product as p on p.pid = mi.product_id \n"
                + "join Seller as s on s.seller_id = m.seller_id\n"
                + "join Category c on p.category_id = c.id "
                + "where m.menu_id = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, m_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setPid(rs.getInt("pid"));
                p.setSeller_id(rs.getInt("seller_id"));
                p.setSeller_name(rs.getString("seller_name"));
                p.setAddress_store(rs.getString("address_store"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOld_price(rs.getInt("old_price"));
                p.setCurrent_price(rs.getInt("current_price"));
                p.setAmount_of_sold(rs.getInt("amount_of_sold"));
                p.setNumber_in_stock(rs.getInt("number_in_stock"));
                p.setStatus(rs.getString("status"));
                p.setDescribe(rs.getString("describe"));
                p.setRating(rs.getFloat("rating"));

                Category c = new Category();
                c.setId(rs.getInt("cid"));
                c.setName(rs.getString("cname"));
                p.setCategory(c);
               
                
                list.add(p);
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

    public void updateMenuStatus(int m_id, int s_id) {
        String sql = "UPDATE Menu SET [status] = CASE WHEN menu_id = ? THEN 1 ELSE 0 END where seller_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, m_id);
            ps.setInt(2, s_id);
            ps.executeUpdate();
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
    }
    
    public void updateMenuName(int m_id, String name) {
        String sql = "UPDATE Menu SET [menu_name] = ? where menu_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, m_id);
            ps.executeUpdate();
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
    }
    
     public void updateMenuSaleOff(int m_id, int sale) {
        String sql = "UPDATE Menu SET [sale_off] = ? where menu_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sale);
            ps.setInt(2, m_id);
            ps.executeUpdate();
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
    }
     
    public void MenuSaleNow(int m_id) {
        String sql = "UPDATE Menu SET [is_sale] = 1 where menu_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, m_id);
            ps.executeUpdate();
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
    }
    public void disableMenuSaleNow(int m_id) {
        String sql = "UPDATE Menu SET [is_sale] = 0 where menu_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, m_id);
            ps.executeUpdate();
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
    } 
     
    public void delete1ProductFromMenu(int m_id, int p_id) {
        String sql = "delete from Menu_Item where menu_id=? and product_id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, m_id);
            ps.setInt(2, p_id);
            ps.executeUpdate();
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
    }

    public void deleteAllProductFromMenu(int m_id) {
        String sql = "delete from Menu_Item where menu_id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, m_id);
            ps.executeUpdate();
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
    }

    public void deleteMenu(int m_id) {
        String sql = "delete from Menu where menu_id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, m_id);
            ps.executeUpdate();
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
    }

    public void AddListProductToMenu(int m_id, List<String> listP) {
        String sql = "INSERT INTO Menu_Item (menu_id, product_id)\n"
                + "VALUES(?, ?)";

        try {
            for (int i = 0; i < listP.size(); i++) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, m_id);
                ps.setInt(2, Integer.parseInt(listP.get(i)));
                ps.executeUpdate();
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
    }
    
    
       
    public static void main(String[] args) {
        ManagerMenuDAO mdao = new ManagerMenuDAO();
        Menu me = mdao.getMenuIsShow(1);
        System.out.println(me);
    }
}
