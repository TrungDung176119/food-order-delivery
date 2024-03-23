/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.chart;

/**
 *
 * @author anhdu
 */
public class ChartByCategory {

    private int cate1;
    private int cate2;
    private int cate3;
    private int cate4;
    private int cate5;

    public ChartByCategory() {
    }

    public ChartByCategory(int cate1, int cate2, int cate3, int cate4, int cate5) {
        this.cate1 = cate1;
        this.cate2 = cate2;
        this.cate3 = cate3;
        this.cate4 = cate4;
        this.cate5 = cate5;
    }

    public int getCate1() {
        return cate1;
    }

    public void setCate1(int cate1) {
        this.cate1 = cate1;
    }

    public int getCate2() {
        return cate2;
    }

    public void setCate2(int cate2) {
        this.cate2 = cate2;
    }

    public int getCate3() {
        return cate3;
    }

    public void setCate3(int cate3) {
        this.cate3 = cate3;
    }

    public int getCate4() {
        return cate4;
    }

    public void setCate4(int cate4) {
        this.cate4 = cate4;
    }

    public int getCate5() {
        return cate5;
    }

    public void setCate5(int cate5) {
        this.cate5 = cate5;
    }

    @Override
    public String toString() {
        return "ChartByCategory{" + "cate1=" + cate1 + ", cate2=" + cate2 + ", cate3=" + cate3 + ", cate4=" + cate4 + ", cate5=" + cate5 + '}';
    }

   
    
    
}
