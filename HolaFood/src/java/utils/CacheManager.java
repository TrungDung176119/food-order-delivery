/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.LoadingCache;
import constant.ConstHome;
import dal.ProductDAO;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import model.product.Product;

/**
 *
 * @author admin
 */
public class CacheManager {

    /**
     * Don't let anyone instantiate this class.
     */
    private CacheManager() {
    }
//    private static final ProductDAO pdao = new ProductDAO();

//    private static final LoadingCache<String, List<Product>> myCache = Caffeine.newBuilder()
//            .expireAfterWrite(30, TimeUnit.MINUTES)
//            .build(new CacheLoader<String, List<Product>>() {
//                @Override
//                public List<Product> load(String key) throws Exception {
//                    try {
//                        System.out.println("Loading data from database for key: " + key);
//                        List<Product> data = pdao.getProductByType(ConstHome.TODAY_SUGGESTION, 0);
//                        System.out.println("Data loaded from database: " + data);
//                        return data;
//                    } catch (Exception e) {
//                        System.out.println("Error loading data from database: " + e.getMessage());
//                        throw e; // Rethrow the exception to indicate that loading failed
//                    }
//                }
//            });

//    public static void refreshCache() {
//        System.out.println("Manually triggering cache refresh...");
//        List<Product> refreshedProducts = pdao.getProductByType(ConstHome.TODAY_SUGGESTION, 0);
//        System.out.println("Data refreshed from database: " + refreshedProducts);
//        myCache.put("products", refreshedProducts);
//        System.out.println("Cache updated.");
//    }
//
//    public static List<Product> getProducts() {
//        List<Product> cachedProducts = myCache.getIfPresent("products");
//
//        if (cachedProducts != null) {
//            System.out.println("Data found in cache!");
//            return cachedProducts;
//        } else {
//            System.out.println("Data not found in cache. Loading from database...");
//            try {
//                // Nếu không tìm thấy trong cache, thực hiện load từ cơ sở dữ liệu
//                List<Product> data = myCache.get("products");
//                System.out.println("Data loaded from database: " + data);
//                return data;
//            } catch (Exception e) {
//                System.out.println("Error loading data from database: " + e.getMessage());
//                return Collections.emptyList();
//            }
//        }
//    }

    public static void main(String[] args) {
//        List<Product> list = getProducts();
//        for (Product product : list) {
//            System.out.println(product);
//        }
    }

}
