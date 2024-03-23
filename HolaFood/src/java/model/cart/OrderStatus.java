/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.cart;

import java.sql.Timestamp;

/**
 *
 * @author admin
 */
public class OrderStatus {
    private int order_id, detail_id, pid;
    private String image, title, cname, store_name, status;
    private int old_price, current_price, quantity, total_price;
    private int is_accepted, is_delivered, is_feedback, is_purchased;
    private Timestamp order_date;

    public OrderStatus() {
    }

    public OrderStatus(int order_id, int detail_id, int pid, String image, String title, String cname, String store_name, String status, int old_price, int current_price, int quantity, int total_price, int is_accepted, int is_delivered, int is_feedback, int is_purchased, Timestamp order_date) {
        this.order_id = order_id;
        this.detail_id = detail_id;
        this.pid = pid;
        this.image = image;
        this.title = title;
        this.cname = cname;
        this.store_name = store_name;
        this.status = status;
        this.old_price = old_price;
        this.current_price = current_price;
        this.quantity = quantity;
        this.total_price = total_price;
        this.is_accepted = is_accepted;
        this.is_delivered = is_delivered;
        this.is_feedback = is_feedback;
        this.is_purchased = is_purchased;
        this.order_date = order_date;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(int is_accepted) {
        this.is_accepted = is_accepted;
    }

    public int getIs_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(int is_delivered) {
        this.is_delivered = is_delivered;
    }

    public int getIs_feedback() {
        return is_feedback;
    }

    public void setIs_feedback(int is_feedback) {
        this.is_feedback = is_feedback;
    }

    public int getIs_purchased() {
        return is_purchased;
    }

    public void setIs_purchased(int is_purchased) {
        this.is_purchased = is_purchased;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "OrderStatus{" + "order_id=" + order_id + ", pid=" + pid + ", image=" + image + ", title=" + title + ", cname=" + cname + ", store_name=" + store_name + ", status=" + status + ", old_price=" + old_price + ", current_price=" + current_price + ", quantity=" + quantity + ", total_price=" + total_price + ", is_accepted=" + is_accepted + ", is_delivered=" + is_delivered + ", is_feedback=" + is_feedback + ", is_purchased=" + is_purchased + ", order_date=" + order_date + '}';
    }
    
}
