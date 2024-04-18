package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private ArrayList<Product> selectedProducts;
    private CustomAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("selectedProducts")) {
                extras.setClassLoader(Product.class.getClassLoader());
                ArrayList<Product> selectedProductsBundle = extras.getParcelableArrayList("selectedProducts", Product.class);
                if (selectedProductsBundle != null) {
                    selectedProducts = selectedProductsBundle;
                    if (selectedProducts != null && !selectedProducts.isEmpty()) {
                        cartAdapter = new CustomAdapter(this, selectedProducts, null);
                        cartListView.setAdapter(cartAdapter);
                        return;
                    }
                }
            }
            Toast.makeText(this, "Error: No selected products found.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

