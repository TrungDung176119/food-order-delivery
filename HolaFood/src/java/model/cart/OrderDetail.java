/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.cart;

/**
 *
 * @author admin
 */
public class OrderDetail {

    private int detail_id, order_id, pid;
    private String title, image, cname, store_name, status;
    private int old_price, current_price, quantity, total_price;

    public OrderDetail() {
    }

    public OrderDetail(int detail_id, int order_id, int pid, String title, String image, String cname, String store_name, String status, int old_price, int current_price, int quantity, int total_price) {
        this.detail_id = detail_id;
        this.order_id = order_id;
        this.pid = pid;
        this.title = title;
        this.image = image;
        this.cname = cname;
        this.store_name = store_name;
        this.status = status;
        this.old_price = old_price;
        this.current_price = current_price;
        this.quantity = quantity;
        this.total_price = total_price;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public String toString() {
        return "OrderDetail{" + "detail_id=" + detail_id + ", order_id=" + order_id + ", pid=" + pid + ", title=" + title + ", image=" + image + ", cname=" + cname + ", store_name=" + store_name + ", status=" + status + ", old_price=" + old_price + ", current_price=" + current_price + ", quantity=" + quantity + ", total_price=" + total_price + '}';
    }
}
