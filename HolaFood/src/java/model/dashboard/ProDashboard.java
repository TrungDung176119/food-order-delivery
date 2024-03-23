/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dashboard;

/**
 *
 * @author anhdu
 */
public class ProDashboard {

    private int pid;
    private int revenue;
    private int quantity;
    private int goodFB;
    private int badFB;

    public ProDashboard() {
    }

    public ProDashboard(int pid, int revenue, int quantity, int goodFB, int badFB) {
        this.pid = pid;
        this.revenue = revenue;
        this.quantity = quantity;
        this.goodFB = goodFB;
        this.badFB = badFB;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getGoodFB() {
        return goodFB;
    }

    public void setGoodFB(int goodFB) {
        this.goodFB = goodFB;
    }

    public int getBadFB() {
        return badFB;
    }

    public void setBadFB(int badFB) {
        this.badFB = badFB;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProDashboard{" + "pid=" + pid + ", revenue=" + revenue + ", quantity=" + quantity + ", goodFB=" + goodFB + ", badFB=" + badFB + '}';
    }

}
