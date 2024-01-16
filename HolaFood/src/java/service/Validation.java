/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Trung Dung
 */
public class Validation {
    public boolean isValidEmail(String email) {
        // Implement your email format validation logic here
        // For simplicity, you can use a regular expression
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public boolean isValidPhoneNumber(String phone) {
        // Implement your phone number format validation logic here
        // For simplicity, check if the phone number has exactly 10 digits and starts with 0
        return phone.matches("^0\\d{9}$");
    }

    
}
