package com.example.bim_1_1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class DBProduct extends Observable {
    // Singleton
    private static DBProduct _instance;


    public static DBProduct getInstance() {
        if (_instance == null) {
            _instance = new DBProduct();
        }

        return _instance;
    }


    List<Product> productList;
    List<Product> basketList;

    private DBProduct() {
        productList = new ArrayList<>();
        basketList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Product> getBasketList() {
        return basketList;
    }

    public void addProduct(Product p) {
        productList.add(p);
    }

    public void increaseBasketQuantity(Product p) {
        p.setBasketQuantity(p.getBasketQuantity() + 1);
    }

    public void decreaseBasketQuantity(Product p) {
        p.setBasketQuantity(p.getBasketQuantity() - 1);
    }

    public void addProductToBasket(Product p) {
        if (!basketList.contains(p)) {
            basketList.add(p);
        }
        increaseBasketQuantity(p);
        setChanged();
        notifyObservers();

    }

    public void removeProductFromBasket(Product p) {
        if (basketList.contains(p)) {
            if (p.getBasketQuantity() > 1) {
                decreaseBasketQuantity(p);
            } else {
                basketList.remove(p);
            }
        }
        setChanged();
        notifyObservers();
    }

    public int calculateBasketMoney() {
        int total = 0;
        for (Product p :
                basketList) {
            total += p.getPrice() * p.getBasketQuantity();
        }
        return total;
    }

    public int getBasketSize() {
        return basketList.size();
    }
}
