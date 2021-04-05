package com.example.bim_1_1.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ButtonBarLayout;

import com.example.bim_1_1.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProdcutAdapter extends ArrayAdapter<Product> {


    List<Product> productList;
    Context context;
    int resourceID;
    boolean isBasket;
    public ProdcutAdapter(@NonNull Context context, int resource, @NonNull List<Product> list, boolean isBasket) {
        super(context, resource, list);
        this.productList = list;
        this.context = context;
        this.resourceID = resource;
        this.isBasket = isBasket;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view =  inflater.inflate(resourceID,null,false);

        ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);
        TextView tvProductName = (TextView) view.findViewById(R.id.tvProductName);
        TextView tvProductPrice = (TextView) view.findViewById(R.id.tvProductPrice);
        TextView tvQuantity = (TextView) view.findViewById(R.id.tvQuantity);
        TextView tvBasketQuant = (TextView) view.findViewById(R.id.tvBasketQuantity);
        Button btnAdd = (Button) view.findViewById(R.id.btnAdd);
        Button btnDetails = (Button) view.findViewById(R.id.btnDetails);
        Button btnRemove = (Button) view.findViewById(R.id.btnRemove);

        Product product = productList.get(position);
        ivImage.setImageDrawable(context.getResources().getDrawable(product.getImage(),null));
        tvProductName.setText(product.getName());
        tvProductPrice.setText("Price: " + String.valueOf(product.getPrice()));
        tvQuantity.setText("Left: " +String.valueOf(product.getQuantity()));
        tvBasketQuant.setText(("Added: x" + String.valueOf(product.getBasketQuantity())));


        if(product.getQuantity() < 1 && !isBasket) {
            view.setBackgroundColor(Color.rgb(255, 170,170));
        }

        if(this.isBasket) {
            btnAdd.setVisibility(View.GONE);
            btnRemove.setVisibility(View.VISIBLE);
            tvBasketQuant.setVisibility(View.VISIBLE);
        }

        btnAdd.setOnClickListener(_view -> {
            if(product.getQuantity() > 0) {
                DBProduct.getInstance().addProductToBasket(product);
                product.setQuantity(product.getQuantity() - 1);
                notifyDataSetChanged();
                Toast.makeText(context, "You added one product", Toast.LENGTH_SHORT).show();
            }  else {
                Toast.makeText(context, "Not available", Toast.LENGTH_SHORT).show();
            }
        });

        btnDetails.setOnClickListener(_view-> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Details");

            builder.setMessage("Details: " + product.Description.toString() + "\n" + "Category: " + product.Category.toString());

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        btnRemove.setOnClickListener(_view -> {
            DBProduct.getInstance().removeProductFromBasket(product);
            product.setQuantity(product.getQuantity() + 1);
            notifyDataSetChanged();
        });

        return view;
    }
}
