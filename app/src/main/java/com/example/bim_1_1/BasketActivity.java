package com.example.bim_1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bim_1_1.model.DBProduct;
import com.example.bim_1_1.model.ProdcutAdapter;
import com.example.bim_1_1.model.Product;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class BasketActivity extends AppCompatActivity implements Observer {

    ListView lvProduct;
    DBProduct dbProduct;
    Button btn;
    TextView tvTotalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        dbProduct = DBProduct.getInstance();
        lvProduct =  findViewById(R.id.lvProducts);
        btn = findViewById(R.id.btnComplete);
        tvTotalMoney = findViewById(R.id.tvTotalMoney);



        ProdcutAdapter adapter =  new ProdcutAdapter(this,R.layout.listview_product,dbProduct.getBasketList(), true);
        lvProduct.setAdapter(adapter);

        dbProduct.addObserver(this);
        tvTotalMoney.setText("Total:" + dbProduct.calculateBasketMoney() + "$");

    }

    @Override
    public void update(Observable observable, Object o) {
        dbProduct = (DBProduct) observable;
        tvTotalMoney.setText("Total:" + dbProduct.calculateBasketMoney() + "$");
    }
}