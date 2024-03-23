/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

import java.sql.Timestamp;

/**
 *
 * @author admin
 */
public class SearchHistory {
    private int his_search_id, acc_id;
    private String search_query;
    private Timestamp search_timestamp;

    public SearchHistory() {
    }

    public SearchHistory(int his_search_id, int acc_id, String search_query, Timestamp search_timestamp) {
        this.his_search_id = his_search_id;
        this.acc_id = acc_id;
        this.search_query = search_query;
        this.search_timestamp = search_timestamp;
    }

    public int getHis_search_id() {
        return his_search_id;
    }

    public void setHis_search_id(int his_search_id) {
        this.his_search_id = his_search_id;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public String getSearch_query() {
        return search_query;
    }

    public void setSearch_query(String search_query) {
        this.search_query = search_query;
    }

    public Timestamp getSearch_timestamp() {
        return search_timestamp;
    }

    public void setSearch_timestamp(Timestamp search_timestamp) {
        this.search_timestamp = search_timestamp;
    }

    @Override
    public String toString() {
        return "SearchHistory{" + "his_search_id=" + his_search_id + ", acc_id=" + acc_id + ", search_query=" + search_query + ", search_timestamp=" + search_timestamp + '}';
    }
    
}
