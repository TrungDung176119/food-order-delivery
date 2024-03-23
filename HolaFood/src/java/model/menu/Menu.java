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

    private int menuId, sellerId, status;
    private Date menuDate;
    private String menuName;
    private Integer saleOff;
    private Integer isSale;

    public Menu() {
    }

    public Menu(int menuId, int sellerId, int status, Date menuDate, String menuName, Integer saleOff, Integer isSale) {
        this.menuId = menuId;
        this.sellerId = sellerId;
        this.status = status;
        this.menuDate = menuDate;
        this.menuName = menuName;
        this.saleOff = saleOff;
        this.isSale = isSale;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(Date menuDate) {
        this.menuDate = menuDate;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(Integer saleOff) {
        this.saleOff = saleOff;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    @Override
    public String toString() {
        return "Menu{" + "menuId=" + menuId + ", sellerId=" + sellerId + ", status=" + status + ", menuDate=" + menuDate + ", menuName=" + menuName + ", saleOff=" + saleOff + '}';
    }

}
