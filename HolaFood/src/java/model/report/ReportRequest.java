/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.report;

import model.product.Product;
import model.profile.Account;

/**
 *
 * @author Dung
 */
public class ReportRequest {

    private int request_id;
    private int acc_id;  // ng tố cáo
    private ReportType report_reason;
    private Account reportedAccount;   // ng bị tố cáo
    private Product reportedProduct;
    private Account reportAcc;        // ng tố cáo
    private int reportedthing_id;
    private String report_description;
    private String request_timestamp;
    private int status;
    private String proof;
    private int type;
    private int approved_id;
    private Account approvedAcc;          // ng xác nhận tố cáo

    public ReportRequest() {
    }

    public ReportRequest(int request_id, int acc_id, ReportType report_reason, int reportedthing_id, String report_description, String request_timestamp, int status, String proof, int type, int approved_id) {
        this.request_id = request_id;
        this.acc_id = acc_id;
        this.report_reason = report_reason;
        this.reportedthing_id = reportedthing_id;
        this.report_description = report_description;
        this.request_timestamp = request_timestamp;
        this.status = status;
        this.proof = proof;
        this.type = type;
        this.approved_id = approved_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public ReportType getReport_reason() {
        return report_reason;
    }

    public void setReport_reason(ReportType report_reason) {
        this.report_reason = report_reason;
    }

    public int getReportedthing_id() {
        return reportedthing_id;
    }

    public void setReportedthing_id(int reportedthing_id) {
        this.reportedthing_id = reportedthing_id;
    }

    public String getReport_description() {
        return report_description;
    }

    public void setReport_description(String report_description) {
        this.report_description = report_description;
    }

    public String getRequest_timestamp() {
        return request_timestamp;
    }

    public void setRequest_timestamp(String request_timestamp) {
        this.request_timestamp = request_timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getApproved_id() {
        return approved_id;
    }

    public void setApproved_id(int approved_id) {
        this.approved_id = approved_id;
    }

    public Account getReportedAccount() {
        return reportedAccount;
    }

    public void setReportedAccount(Account reportedAccount) {
        this.reportedAccount = reportedAccount;
    }

    // Getter and setter for reportedProduct
    public Product getReportedProduct() {
        return reportedProduct;
    }

    public void setReportedProduct(Product reportedProduct) {
        this.reportedProduct = reportedProduct;
    }

    public Account getReportAcc() {
        return reportAcc;
    }

    public void setReportAcc(Account reportAcc) {
        this.reportAcc = reportAcc;
    }

    public Account getApprovedAcc() {
        return approvedAcc;
    }

    public void setApprovedAcc(Account approvedAcc) {
        this.approvedAcc = approvedAcc;
    }

    @Override
    public String toString() {
        return "ReportRequest{" + "request_id=" + request_id + ", acc_id=" + acc_id + ", report_reason=" + report_reason + ", reportedthing_id=" + reportedthing_id + ", report_description=" + report_description + ", request_timestamp=" + request_timestamp + ", status=" + status + ", proof=" + proof + ", type=" + type + ", approved_id=" + approved_id + '}';
    }

}
