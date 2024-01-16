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
public class Account {

    private int accid;
    private String username;
    private String password;
    private int roleid;
    private int isBlock;
    private int coin;
    private Date createTime;

    public Account() {
    }

    public Account(int accid, String username, String password, int roleid, int isBlock, int coin, Date createTime) {
        this.accid = accid;
        this.username = username;
        this.password = password;
        this.roleid = roleid;
        this.isBlock = isBlock;
        this.coin = coin;
        this.createTime = createTime;
    }
    
    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }



    @Override
    public String toString() {
        return "Account{" + "accid=" + accid + ", username=" + username + ", password=" + password
                + ", roleid=" + roleid + ", isBlock=" + isBlock + ", createTime=" + createTime + ", coin=" + coin + '}';
    }

}
