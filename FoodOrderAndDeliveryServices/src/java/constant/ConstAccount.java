/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package constant;

/**
 *
 * @author admin
 */
public interface ConstAccount {

    public static final String QUERY_SELECT_ACCOUNT = "Select * from ACCOUNT where acc_id = ";

    public static final String QUERY_SELECT_ACCOUNT_DETAIL = "Select * from ACCOUNT_DETAILS where acc_id = ";

    public static final String QUERY_SELECT_ACCOUNT_ADDRESS = "Select * from ACCOUNT_ADDRESS where address_id = ";

    public static final String IS_ACCOUNT = "ACCOUNT";

    public static final String IS_ACCOUNT_DETAIL = "ACCOUNT_DETAILS";

    public static final String IS_ACCOUNT_ADDRESS = "ACCOUNT_ADDRESS";

    public static final String ACTION_INSERT = "INSERT";

    public static final String ACTION_UPDATE = "UPDATE";
    
    public static final String ACTION_DELETE = "DELETE";

    public static final int ROLE_CUSTOMER = 1;

    public static final int DEFAULT = 0;

    public static final String ACCTION_BLOCK = "block";
    
    public static final String ACCTION_INSERT = "insert";
    
    public static final String ACCTION_UPDATE = "update";

}
