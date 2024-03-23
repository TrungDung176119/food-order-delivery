/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.profile;

/**
 *
 * @author admin
 */
public class AccountAddress {
    private int acc_id;
    private String nickname, phone_address, address, note, status;

    public AccountAddress() {
    }

    public AccountAddress(int acc_id, String nickname, String phone_address, String address, String note, String status) {
        this.acc_id = acc_id;
        this.nickname = nickname;
        this.phone_address = phone_address;
        this.address = address;
        this.note = note;
        this.status = status;
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

    public String getPhone_address() {
        return phone_address;
    }

    public void setPhone_address(String phone_address) {
        this.phone_address = phone_address;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountAddress{" + "acc_id=" + acc_id + ", nickname=" + nickname + ", phone_address=" + phone_address + ", address=" + address + ", note=" + note + ", status=" + status + '}';
    }
    
     
}
