/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.cart.Item;
import model.product.Product;

/**
 *
 * @author admin
 */
public class Cart {

    private List<Item> listItems;

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
}
