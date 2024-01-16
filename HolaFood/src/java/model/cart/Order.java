/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.cart;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Order {
    private int order_id, acc_id;
    private String nickname, phone, email, address, note;
    private Date order_date;
    private int is_delivered, is_accepted, is_feedback, is_purchased, discount, total_price;

    public Order() {
    }

    public Order(int order_id, int acc_id, String nickname, String phone, String email, String address, String note, Date order_date, int is_delivered, int is_accepted, int is_feedback, int is_purchased, int discount, int total_price) {
        this.order_id = order_id;
        this.acc_id = acc_id;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        this.order_date = order_date;
        this.is_delivered = is_delivered;
        this.is_accepted = is_accepted;
        this.is_feedback = is_feedback;
        this.is_purchased = is_purchased;
        this.discount = discount;
        this.total_price = total_price;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
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

    
    
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", acc_id=" + acc_id + ", nickname=" + nickname + ", phone=" + phone + ", email=" + email + ", address=" + address + ", note=" + note + ", order_date=" + order_date + ", is_delivered=" + is_delivered + ", is_accepted=" + is_accepted + ", is_feedback=" + is_feedback + ", is_purchased=" + is_purchased + ", discount=" + discount + ", total_price=" + total_price + '}';
    }
    
}
