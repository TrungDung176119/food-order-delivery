/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import dal.AccountDAO;
import dal.CartDAO;
import dal.ManagerOrderDAO;
import dal.ManagerSellerDAO;
import dal.ServiceDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.cart.Item;
import model.product.Category;
import model.product.Product;

/**
 *
 * @author admin
 */
public class Cart {

    private List<Item> listItems;
    private static Cart instance;

    public static Cart gI() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public Cart() {
        listItems = new ArrayList<>();
    }

    public Cart(List<Item> listItems) {
        this.listItems = listItems;
    }

    public List<Item> getItems() {
        return listItems;
    }

    public void setItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    /**
     * Get item product in the cart by product id
     *
     * @param pid
     * @return
     */
    public Item getItemByPId(int pid) {
        for (Item i : listItems) {
            if (i.getProduct().getPid() == pid) {
                return i;
            }
        }
        return null;
    }

    /**
     * get quantity left of product
     *
     * @param pid
     * @return
     */
    public int getQuantityByPId(int pid) {
        Item item = getItemByPId(pid);
        return (item != null) ? item.getQuantity() : 0;
    }

    /**
     * Adds a new item product to the list of items.
     *
     * @param newItem
     * @return
     */
    public boolean addItemIntoCart(Item newItem) {
        Item currentItem = this.getItemByPId(newItem.getProduct().getPid());
        if (currentItem != null) {
            currentItem.setQuantity(currentItem.getQuantity() + newItem.getQuantity());
        } else {
            listItems.add(newItem);
            return true;
        }
        return false;
    }

    /**
     * Return true if delete success, otherwise if delete failed or item is null
     *
     * @param pid
     * @return
     */
    public boolean deleteItem(int pid) {
        Item item = getItemByPId(pid);
        return (item != null) && listItems.remove(item);
    }

    public int getTotalMoney() {
        int total = 0;
        for (Item i : listItems) {
            total += i.getQuantity() * i.getProduct().getCurrent_price();
        }
        return total;
    }

    public int getAmountItems() {
        int amountItem;
        if (listItems != null) {
            amountItem = listItems.size();
        } else {
            amountItem = 0;
        }
        return amountItem;
    }

    private Product getProductById(int pid, List<Product> listProduct) {
        for (Product p : listProduct) {
            if (p.getPid() == pid) {
                return p;
            }
        }
        return null;
    }

    /**
     * This method return list seller id unique from list cart 
     * 
     * @return 
     */
    public List<Integer> getUniqueSellerIds() {
        List<Integer> uniqueSellerIdsList = new ArrayList<>();
        List<Integer> addedSellerIds = new ArrayList<>();

        for (Item item : listItems) {
            int sellerId = item.getProduct().getSeller_id();
            int accid = ManagerSellerDAO.gI().getSellerBySeller_Id(sellerId).getAcc_id();
            // Kiểm tra xem sellerId đã được thêm vào danh sách chưa
            if (!addedSellerIds.contains(accid)) {
                uniqueSellerIdsList.add(accid);
                // Đánh dấu sellerId đã được thêm vào danh sách
                addedSellerIds.add(accid);
            }
        }
        return uniqueSellerIdsList;
    }

    /**
     * This method use to calculate total of ship price
     *
     * @return
     */
    public int getNumberStore() {
        List<Integer> sellerIds = new ArrayList<>();
        for (int i = 0; i < listItems.size(); i++) {
            sellerIds.add(listItems.get(i).getProduct().getSeller_id());
        }
        // use Set to store sellerid unique
        Set<Integer> uniqueSellerID = new HashSet<>(sellerIds);
        return uniqueSellerID.size();
    }

    public void initializeCartFromText(String txt, List<Product> listProduct) {
        if (txt != null) {
            String[] prod = txt.split("-");
            for (String p : prod) {
                String[] ent = p.split(":");
                try {
                    int id = Integer.parseInt(ent[0]);
                    int quantity = Integer.parseInt(ent[1]);
                    Product product = getProductById(id, listProduct);
                    if (product != null) {
                        Item item = new Item(product, quantity);
                        this.addItemIntoCart(item);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Product> listProduct = new ArrayList<>();

        CartDAO cdao = new CartDAO();

        Category c = new Category(0, "c");
        Product p = new Product(1, 1, c, "", "", "", "", 0, 0, 0, 0, "", "", 0);
        Item i = new Item(p, 1);

        Product p3 = new Product(4, 2, c, "", "", "", "", 0, 0, 0, 0, "", "", 0);
        Product p2 = new Product(2, 4, c, "", "", "", "", 0, 0, 0, 0, "", "", 0);
        Product p1 = new Product(3, 4, c, "", "", "", "", 0, 0, 0, 0, "", "", 0);
        Item i1 = new Item(p1, 1);
        Item i2 = new Item(p2, 1);
        Item i3 = new Item(p3, 1);

        listProduct.add(p);
        listProduct.add(p1);
        listProduct.add(p2);

//        Cart cart = new Cart();
//        Cart.gI().initializeCartFromText("1:1", listProduct);
//        cart.initializeCartFromText("0:2:1-1:1:3", listProduct);

//        Item i2 = new Item(p, 1, cp);
        Cart.gI().addItemIntoCart(i1);
        Cart.gI().addItemIntoCart(i2);
//        Cart.gI().addItemIntoCart(i3);
//        Cart.gI().addItemIntoCart(i);

//        List<Item> listC = cart.getItems();
//        for (Item item : listC) {
//            System.out.println(item);
//        }
        System.out.println("Size of listItems: " + Cart.gI().listItems.size());

        List<Integer> listS = Cart.gI().getUniqueSellerIds();
        System.out.println("list: " + listS.size());
        for (Integer integer : listS) {
            System.out.println(integer);
        }
    }
}
