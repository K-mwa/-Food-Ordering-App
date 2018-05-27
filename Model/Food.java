package com.younessharaki.apricot.Model;

/**
 * Created by Youness Haraki on 27.04.2018.
 */

public class Food {

    private String Description,Image,MenuId,Name,Discount,Price;

    public Food() {
    }

    public Food(String description, String image, String menuId, String name, String discount, String price) {
        Description = description;
        Image = image;
        MenuId = menuId;
        Name = name;
        Discount = discount;
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
