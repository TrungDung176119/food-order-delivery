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

    public static final int DEFAULT = 0;               // 

    public static final int TODAY_SUGGESTION = 1;      // sản phẩm gợi ý hôm nay = load all 

    public static final int MOST_PURCHASED = 2;      // load product đc bán nhiều nhất

    public static final int HIGH_RATING = 3;        // load product được đánh giá cao nhất

    public static final int LOAD_NUMBER_PRODUCT = 5;    // load số lượng sản phẩm trong 1 group (1 hàng) 

     public static final String QUERY_LOAD_LIST_PRODUCT = "select p.pid, p.seller_id, s.store_name as seller_name, s.address_store , p.[image], p.title, p.old_price,\n"
            + "p.current_price, p.amount_of_sold, p.number_in_stock, p.[status],\n"
            + "p.describe, p.rating,p.category_id as cid, c.[name] as cname\n"
            + "from Product p join Category c on p.category_id = c.id\n"
            + "join Seller s on p.seller_id = s.seller_id\n"
            + "join Menu_Item mi on p.pid = mi.product_id\n"
            + "join Menu m on m.menu_id = mi.menu_id\n"
            + "where m.status = 1 and s.is_active = " + ConstSeller.IS_ACTIVE + "  \n";

    public static final int NUMBER_PAGINATION = 25;          // number of product in a page
}
