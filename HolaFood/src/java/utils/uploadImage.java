/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

/**
 *
 * @author admin
 */
public class uploadImage {
    public String uploadImagePath(Part filePart, String folder) throws IOException{
        
        // Tạo một tên tệp tin duy nhất bằng UUID
        String fileName = UUID.randomUUID().toString() + "-" + filePart.getSubmittedFileName();
        
        // Thay đổi đường dẫn lưu trữ tùy thuộc vào môi trường của bạn
//        String uploadDirectory = "C:\\Code\\JavaWeb\\g5\\g5\\WatchShop\\web\\img\\" + folder +"\\";
            String uploadDirectory = "C:\\Code\\JavaWeb\\ViewInter2 (2)\\ViewInter2\\HolaFood\\web\\assests\\img\\" + folder +"\\";
        // Lưu trữ tệp tin đã tải lên
        filePart.write(uploadDirectory + fileName);
        
        // Tạo đường dẫn đầy đủ của tệp tin đã tải lên
        String uploadedImagePath = "img/" + folder +"/" + fileName;       
        return uploadedImagePath;
    }
}
