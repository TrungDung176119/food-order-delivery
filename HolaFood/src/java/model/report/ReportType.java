/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.report;

/**
 *
 * @author Dung
 */
public class ReportType {
    private int report_id;
    private String report_name;

    public ReportType() {
    }

    public ReportType(int report_id, String report_name) {
        this.report_id = report_id;
        this.report_name = report_name;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    @Override
    public String toString() {
        return "ReportType{" + "report_id=" + report_id + ", report_name=" + report_name + '}';
    }

   
    
}
