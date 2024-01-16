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

    public static String QUERY_SELECT_ACCOUNT = "Select * from ACCOUNT where acc_id = ";

    public static String QUERY_SELECT_ACCOUNT_DETAIL = "Select * from ACCOUNT_DETAILS where acc_id = ";

    public static String QUERY_SELECT_ACCOUNT_ADDRESS = "Select * from ACCOUNT_ADDRESS where acc_id = ";

    public static String IS_ACCOUNT = "ACCOUNT";

    public static String IS_ACCOUNT_DETAIL = "ACCOUNT_DETAILS";

    public static String IS_ACCOUNT_ADDRESS = "ACCOUNT_ADDRESS";

    public static String ACTION_INSERT = "INSERT";

    public static String ACTION_UPDATE = "UPDATE";

}
