/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Random;

/**
 *
 * @author Trung Dung
 */
public class Validation {

    public static boolean isValidEmail(String email) {
        // Regular expression for a more permissive email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)*[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPhoneNumber(String phone) {
        // Implement your phone number format validation logic here
        // For simplicity, check if the phone number has exactly 10 digits and starts with 0
        return phone.matches("^0\\d{9}$");
    }

    public static String getRandomNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    
    public static boolean isValidPassword(String password) {
        // Biểu thức chính quy:
        // ^               // Bắt đầu chuỗi
        // (?=.*[A-Z])     // Chứa ít nhất 1 chữ cái in hoa
        // (?=.*[a-z])     // Chứa ít nhất 1 chữ cái in thường
        // (?=.*[@#$%^&+=]) // Chứa ít nhất 1 kí tự đặc biệt
        // .{8,16}         // Có độ dài từ 8 đến 16 kí tự
        // $               // Kết thúc chuỗi
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=]).{8,16}$";

//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(password);
        return password.matches(regex);
    }
}
