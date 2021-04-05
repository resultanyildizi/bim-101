package com.example.bim_1_1.model;

public class Product {
    String Name;
    float Price;
    int Image;
    int Quantity;
    String Category;
    String Description;
    boolean inBasket;
    int BasketQuantity;

    public Product(String name, float price, int image, int quantity, String category, String description) {
        Name = name;
        Price = price;
        Image = image;
        Quantity = quantity;
        Category = category;
        Description = description;
        inBasket = false;
        BasketQuantity = 0;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getPrice() {
        return Price;
    }

    public void setBasketQuantity(int basketQuantity) {
        BasketQuantity = basketQuantity;
    }

    public int getBasketQuantity() {
        return BasketQuantity;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

}
