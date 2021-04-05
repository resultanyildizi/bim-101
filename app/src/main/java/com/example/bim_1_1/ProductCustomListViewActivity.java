package com.example.bim_1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.bim_1_1.model.DBProduct;
import com.example.bim_1_1.model.ProdcutAdapter;
import com.example.bim_1_1.model.Product;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProductCustomListViewActivity extends AppCompatActivity implements Observer {

    ListView lvProduct;
    Button btnCard;
    DBProduct dbProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_custom_list_view);

        dbProduct = DBProduct.getInstance();
        btnCard = findViewById(R.id.btnCard);
        lvProduct =  findViewById(R.id.lvProducts);

        btnCard.setOnClickListener(view -> {
            Intent intent = new Intent(this, BasketActivity.class);
            startActivity(intent);
        });

        dbProduct.addProduct(new Product("Laptop",3500,R.drawable.laptop_100px, 12, "Technology", "This is a good laptop"));
        dbProduct.addProduct(new Product("Apple",6500,R.drawable.apple_100px, 5, "Food", "Good apple"));
        dbProduct.addProduct(new Product("Mobile",5000,R.drawable.mobile_phone_100px, 0, "Technology", "Good mobile phone"));
        dbProduct.addProduct(new Product("Watch",4600,R.drawable.smart_watch_100px, 3, "Technology", "Good watch"));
        dbProduct.addProduct(new Product("Mobile",5000,R.drawable.mobile_phone_100px, 0, "Technology", "Good mobile phone"));

        ProdcutAdapter adapter =  new ProdcutAdapter(this,R.layout.listview_product,dbProduct.getProductList(), false);
        lvProduct.setAdapter(adapter);

        dbProduct.addObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ProdcutAdapter adapter =  new ProdcutAdapter(this,R.layout.listview_product,dbProduct.getProductList(), false);
        lvProduct.setAdapter(adapter);

    }

    @Override
    public void update(Observable observable, Object o) {
        dbProduct = (DBProduct) observable;
        btnCard.setText("CART (" + String.valueOf(dbProduct.getBasketSize()) + ")");
    }
}