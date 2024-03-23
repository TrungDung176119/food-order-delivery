/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.profile;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Seller {

    private int seller_id, acc_id;
    private String phone_store, email_store, address_store, store_name;
    private int number_of_foods;
    private Date store_opentime;
    private float rating_store;
    private int follower, is_active;
    private String image_store;
    private String note;
    
    public Seller() {
    }

    public Seller(int seller_id, int acc_id, String phone_store, String email_store, String address_store, String store_name, int number_of_foods, Date store_opentime, float rating_store, int follower, int is_active, String image_store) {
        this.seller_id = seller_id;
        this.acc_id = acc_id;
        this.phone_store = phone_store;
        this.email_store = email_store;
        this.address_store = address_store;
        this.store_name = store_name;
        this.number_of_foods = number_of_foods;
        this.store_opentime = store_opentime;
        this.rating_store = rating_store;
        this.follower = follower;
        this.is_active = is_active;
        this.image_store = image_store;
    }

    public Seller(int seller_id, int acc_id, String phone_store, String email_store, String address_store, String store_name, int number_of_foods, Date store_opentime, float rating_store, int follower, int is_active, String image_store, String note) {
        this.seller_id = seller_id;
        this.acc_id = acc_id;
        this.phone_store = phone_store;
        this.email_store = email_store;
        this.address_store = address_store;
        this.store_name = store_name;
        this.number_of_foods = number_of_foods;
        this.store_opentime = store_opentime;
        this.rating_store = rating_store;
        this.follower = follower;
        this.is_active = is_active;
        this.image_store = image_store;
        this.note = note;
    }

    public String getImage_store() {
        return image_store;
    }

    public void setImage_store(String image_store) {
        this.image_store = image_store;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getPhone_store() {
        return phone_store;
    }

    public void setPhone_store(String phone_store) {
        this.phone_store = phone_store;
    }

    public String getEmail_store() {
        return email_store;
    }

    public void setEmail_store(String email_store) {
        this.email_store = email_store;
    }

    public String getAddress_store() {
        return address_store;
    }

    public void setAddress_store(String address_store) {
        this.address_store = address_store;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getNumber_of_foods() {
        return number_of_foods;
    }

    public void setNumber_of_foods(int number_of_foods) {
        this.number_of_foods = number_of_foods;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public Date getStore_opentime() {
        return store_opentime;
    }

    public void setStore_opentime(Date store_opentime) {
        this.store_opentime = store_opentime;
    }

    public float getRating_store() {
        return rating_store;
    }

    public void setRating_store(float rating_store) {
        this.rating_store = rating_store;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Seller{" + "seller_id=" + seller_id + ", acc_id=" + acc_id + ", phone_store=" + phone_store + ", email_store=" + email_store + ", address_store=" + address_store + ", store_name=" + store_name + ", number_of_foods=" + number_of_foods + ", store_opentime=" + store_opentime + ", rating_store=" + rating_store + ", follower=" + follower + ", is_active=" + is_active + ", image_store=" + image_store + '}';
    }

}
