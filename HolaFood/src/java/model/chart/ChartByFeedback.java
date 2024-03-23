/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.chart;

/**
 *
 * @author anhdu
 */
public class ChartByFeedback {

    private int goodFB;
    private int badFB;

    public ChartByFeedback() {
    }

    public ChartByFeedback(int goodFB, int badFB) {
        this.goodFB = goodFB;
        this.badFB = badFB;
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

    @Override
    public String toString() {
        return "ChartByFeedback{" + "goodFB=" + goodFB + ", badFB=" + badFB + '}';
    }
    
    
}
