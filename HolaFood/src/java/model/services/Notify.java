/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.sql.Timestamp;

/**
 *
 * @author admin
 */
public class Notify {

    private int notify_id, acc_id;
    private String no_img, no_title, no_message;
    private Timestamp no_timestamp;
    private String url;
    private int is_read, is_hide;

    public Notify() {
    }

    public Notify(int notify_id, int acc_id, String no_img, String no_title, String no_message, Timestamp no_timestamp, String url, int is_read, int is_hide) {
        this.notify_id = notify_id;
        this.acc_id = acc_id;
        this.no_img = no_img;
        this.no_title = no_title;
        this.no_message = no_message;
        this.no_timestamp = no_timestamp;
        this.url = url;
        this.is_read = is_read;
        this.is_hide = is_hide;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(int notify_id) {
        this.notify_id = notify_id;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getNo_img() {
        return no_img;
    }

    public void setNo_img(String no_img) {
        this.no_img = no_img;
    }

    public String getNo_title() {
        return no_title;
    }

    public void setNo_title(String no_title) {
        this.no_title = no_title;
    }

    public String getNo_message() {
        return no_message;
    }

    public void setNo_message(String no_message) {
        this.no_message = no_message;
    }

    public Timestamp getNo_timestamp() {
        return no_timestamp;
    }

    public void setNo_timestamp(Timestamp no_timestamp) {
        this.no_timestamp = no_timestamp;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public int getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(int is_hide) {
        this.is_hide = is_hide;
    }

    @Override
    public String toString() {
        return "Notify{" + "notify_id=" + notify_id + ", acc_id=" + acc_id + ", no_img=" + no_img + ", no_title=" + no_title + ", no_message=" + no_message + ", no_timestamp=" + no_timestamp + ", is_read=" + is_read + ", is_hide=" + is_hide + ", url=" + url + '}';
    }
}
