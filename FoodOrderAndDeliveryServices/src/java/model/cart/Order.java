/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.cart;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author admin
 */
public class Order {

    private int order_id, acc_id, seller_id;
    private String username, phone, email, address, note;
    private int is_ship, is_delivered, is_accepted, is_purchased;
    private Timestamp order_date;
    private int total_price;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(int order_id, int acc_id, int seller_id, String username, String phone, String email, String address, String note, int is_ship, int is_delivered, int is_accepted, int is_purchased, Timestamp order_date, int total_price, List<OrderDetail> orderDetails) {
        this.order_id = order_id;
        this.acc_id = acc_id;
        this.seller_id = seller_id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        this.is_ship = is_ship;
        this.is_delivered = is_delivered;
        this.is_accepted = is_accepted;
        this.is_purchased = is_purchased;
        this.order_date = order_date;
        this.total_price = total_price;
        this.orderDetails = orderDetails;
    }

    public Order(int order_id, int acc_id, int seller_id, String username, String phone, String email, String address, String note, int is_ship, int is_delivered, int is_accepted, int is_purchased, Timestamp order_date, int total_price) {
        this.order_id = order_id;
        this.acc_id = acc_id;
        this.seller_id = seller_id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        this.is_ship = is_ship;
        this.is_delivered = is_delivered;
        this.is_accepted = is_accepted;
        this.is_purchased = is_purchased;
        this.order_date = order_date;
        this.total_price = total_price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIs_ship() {
        return is_ship;
    }

    public void setIs_ship(int is_ship) {
        this.is_ship = is_ship;
    }

    public int getIs_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(int is_delivered) {
        this.is_delivered = is_delivered;
    }

    public int getIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(int is_accepted) {
        this.is_accepted = is_accepted;
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

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", acc_id=" + acc_id + ", seller_id=" + seller_id + ", username=" + username + ", phone=" + phone + ", email=" + email + ", address=" + address + ", note=" + note + ", is_ship=" + is_ship + ", is_delivered=" + is_delivered + ", is_accepted=" + is_accepted + ", is_purchased=" + is_purchased + ", order_date=" + order_date + ", total_price=" + total_price + ", orderDetails=" + orderDetails + '}';
    }
}
