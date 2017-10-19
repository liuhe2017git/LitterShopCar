package com.bwie.myshopcar;

/**
 * 作者：刘贺
 * 时间: 2017-10-19.
 * 功能:
 */

public class ShopBean {
    public String shopName;
    public int price;
    public boolean isSelected;

    public ShopBean(String shopName, int price, boolean isSelected) {
        this.shopName = shopName;
        this.price = price;
        this.isSelected = isSelected;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
