/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.cart;

/**
 *
 * @author admins
 */
public class ViewOrderDetail {
    
    private int detail_id, order_id, seller_id, product_id;
    private String image, title, status, cname;
    private int current_price, old_price, quantity;
    private String nickname, phone, email, address, note;
    private int is_shipped, total_price;

    public ViewOrderDetail() {
    }

    public ViewOrderDetail(int detail_id, int order_id, int seller_id, int product_id, String image, String title, String status, String cname, int current_price, int old_price, int quantity, String nickname, String phone, String email, String address, String note, int is_shipped, int total_price) {
        this.detail_id = detail_id;
        this.order_id = order_id;
        this.seller_id = seller_id;
        this.product_id = product_id;
        this.image = image;
        this.title = title;
        this.status = status;
        this.cname = cname;
        this.current_price = current_price;
        this.old_price = old_price;
        this.quantity = quantity;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        this.is_shipped = is_shipped;
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

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(int current_price) {
        this.current_price = current_price;
    }

    public int getOld_price() {
        return old_price;
    }

    public void setOld_price(int old_price) {
        this.old_price = old_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getIs_shipped() {
        return is_shipped;
    }

    public void setIs_shipped(int is_shipped) {
        this.is_shipped = is_shipped;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "ViewOrderDetail{" + "detail_id=" + detail_id + ", order_id=" + order_id + ", seller_id=" + seller_id + ", product_id=" + product_id + ", image=" + image + ", title=" + title + ", status=" + status + ", cname=" + cname + ", current_price=" + current_price + ", old_price=" + old_price + ", quantity=" + quantity + ", nickname=" + nickname + ", phone=" + phone + ", email=" + email + ", address=" + address + ", note=" + note + ", is_shipped=" + is_shipped + ", total_price=" + total_price + '}';
    }
}
