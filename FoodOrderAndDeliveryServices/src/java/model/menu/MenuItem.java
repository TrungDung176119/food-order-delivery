/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.menu;

/**
 *
 * @author anhdu
 */
public class MenuItem {
    private int menuItemId,menuId,productId;

    public MenuItem() {
    }

    public MenuItem(int menuItemId, int menuId, int productId) {
        this.menuItemId = menuItemId;
        this.menuId = menuId;
        this.productId = productId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    
}
