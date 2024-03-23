/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package constant;

/**
 *
 * @author admin
 */
public interface ConstOrder {

    public static final int IS_RETURN_OR_REFUND = -4;   // trả hàng\hoàn tiền delivered = -4

    public static final int IS_DELIVERED_NO_SUCCESS = -3;

    public static final int IS_NO_ACCEPTED_BY_SELLER = -2;

    public static final int IS_CANCELED = -1;             // đã hủy đơn accepted = -1

    public static final int DEFAUT = 0;                   // chờ xác nhận deliver = 0

    public static final int IS_ACCEPTED = 1;            // đã xác nhận accepted = 1

    public static final int IS_DELIVERING = 2;           // đang giao hàng deliver = 2

    public static final int IS_DELIVERED = 3;           // đã giao hàng deliver = 3

    public static final int IS_FEEDBACKED = 4;          // đã feedback feedback = 4

    public static final int IS_PURCHASED = 5;           // Đã thanh toán purchased = 5
}
