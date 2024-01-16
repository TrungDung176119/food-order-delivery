/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

/**
 *
 * @author admin
 */
public class Product {
    
    private int pid, seller_id;
    private Category category;
    private String seller_name, address_store;
    private String image, title;
    private int old_price, current_price, amount_of_sold, number_in_stock;
    private String status, describe;
    private float rating;

    public Product() {
    }

    public Product(int pid, int seller_id, Category category, String seller_name, String address_store, String image, String title, int old_price, int current_price, int amount_of_sold, int number_in_stock, String status, String describe, float rating) {
        this.pid = pid;
        this.seller_id = seller_id;
        this.category = category;
        this.seller_name = seller_name;
        this.address_store = address_store;
        this.image = image;
        this.title = title;
        this.old_price = old_price;
        this.current_price = current_price;
        this.amount_of_sold = amount_of_sold;
        this.number_in_stock = number_in_stock;
        this.status = status;
        this.describe = describe;
        this.rating = rating;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

 
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getAddress_store() {
        return address_store;
    }

    public void setAddress_store(String address_store) {
        this.address_store = address_store;
    }

   
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOld_price() {
        return old_price;
    }

    public void setOld_price(int old_price) {
        this.old_price = old_price;
    }

    public int getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(int current_price) {
        this.current_price = current_price;
    }

    public int getAmount_of_sold() {
        return amount_of_sold;
    }

    public void setAmount_of_sold(int amount_of_sold) {
        this.amount_of_sold = amount_of_sold;
    }

    public int getNumber_in_stock() {
        return number_in_stock;
    }

    public void setNumber_in_stock(int number_in_stock) {
        this.number_in_stock = number_in_stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" + "pid=" + pid + ", seller_id=" + seller_id + ", category=" + category + ", seller_name=" + seller_name + ", address_store=" + address_store + ", image=" + image + ", title=" + title + ", old_price=" + old_price + ", current_price=" + current_price + ", amount_of_sold=" + amount_of_sold + ", number_in_stock=" + number_in_stock + ", status=" + status + ", describe=" + describe + ", rating=" + rating + '}';
    }

   

   
}
