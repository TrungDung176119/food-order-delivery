/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package constant;

/**
 *
 * @author admin
 */
public interface ConstHome {

    public static int DEFAULT = 0;               // 

    public static int TODAY_SUGGESTION = 1;      // sản phẩm gợi ý hôm nay = load all 

    public static int MOST_PURCHASED = 2;      // load product đc bán nhiều nhất

    public static int HIGH_RATING = 3;        // load product được tìm kiếm nhiều nhất

    public static int LOAD_NUMBER_PRODUCT = 5;    // load số lượng sản phẩm trong 1 group (1 hàng) 

    public static String QUERY_LOAD_LIST_PRODUCT = "select p.pid, p.seller_id, s.store_name as seller_name, s.address_store , p.[image], p.title, p.old_price,\n"
            + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
            + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
            + "from Product p join Category c on p.category_id = c.id\n"
            + "join Seller s on p.seller_id = s.seller_id";

}
