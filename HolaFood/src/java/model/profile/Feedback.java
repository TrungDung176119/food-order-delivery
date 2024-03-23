/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.profile;

/**
 *
 * @author admin
 */
public class Feedback {

    private int feed_id, product_id;
    private String cate_name;
    private int acc_id;
    private String feed_date;
    private int rating;
    private String describe, delivery, standard, content, image;
    private int incognito, status;
    private String username;

    public Feedback() {
    }

    public Feedback(int feed_id, int product_id, String cate_name, int acc_id, String feed_date, int rating, String describe, String delivery, String standard, String content, String image, int incognito, int status, String username) {
        this.feed_id = feed_id;
        this.product_id = product_id;
        this.cate_name = cate_name;
        this.acc_id = acc_id;
        this.feed_date = feed_date;
        this.rating = rating;
        this.describe = describe;
        this.delivery = delivery;
        this.standard = standard;
        this.content = content;
        this.image = image;
        this.incognito = incognito;
        this.status = status;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public int getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(int feed_id) {
        this.feed_id = feed_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getFeed_date() {
        return feed_date;
    }

    public void setFeed_date(String feed_date) {
        this.feed_date = feed_date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIncognito() {
        return incognito;
    }

    public void setIncognito(int incognito) {
        this.incognito = incognito;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feed_id=" + feed_id + ", product_id=" + product_id + ", cate_name=" + cate_name + ", acc_id=" + acc_id + ", feed_date=" + feed_date + ", rating=" + rating + ", describe=" + describe + ", delivery=" + delivery + ", standard=" + standard + ", content=" + content + ", image=" + image + ", incognito=" + incognito + ", status=" + status + ", username=" + username + '}';
    }

}
