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
public class AccountDetail {
    private int accid;
    private String nickname, image, email, phone;
    private int gender;
    private Date dob;

    public AccountDetail() {
    }

    public AccountDetail(int accid, String nickname, String image, String email, String phone, int gender, Date dob) {
        this.accid = accid;
        this.nickname = nickname;
        this.image = image;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "AccountDetail{" + "accid=" + accid + ", nickname=" + nickname + ", image=" + image + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", dob=" + dob + '}';
    }
    
    
}
