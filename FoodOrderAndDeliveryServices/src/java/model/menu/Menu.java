/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.menu;

import java.sql.Date;

/**
 *
 * @author anhdu
 */
public class Menu {
    private int menuId,sellerId,status;
    private Date menuDate;
    
     public Menu() {
    }

    public Menu(int menuId, int sellerId, int status, Date menuDate) {
        this.menuId = menuId;
        this.sellerId = sellerId;
        this.status = status;
        this.menuDate = menuDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public Date getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(Date menuDate) {
        this.menuDate = menuDate;
    }

    @Override
    public String toString() {
        return "Menu{" + "menuId=" + menuId + ", sellerId=" + sellerId + ", status=" + status + ", menuDate=" + menuDate + '}';
    }
    
}
