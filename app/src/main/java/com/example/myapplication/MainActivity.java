package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView totalItemsTextView;
    private Button showCheckedItemsButton;
    private ArrayList<Product> productList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        totalItemsTextView = findViewById(R.id.totalItemsTextView);
        showCheckedItemsButton = findViewById(R.id.showCheckedItemsButton);

        productList = new ArrayList<>();
        productList.add(new Product(1, "Товар 1", 100));
        productList.add(new Product(2, "Товар 2", 200));

        customAdapter = new CustomAdapter(this, productList, totalItemsTextView);

        listView.setAdapter(customAdapter);

        showCheckedItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Product> selectedProducts = customAdapter.getSelectedProducts();
                if (selectedProducts != null && !selectedProducts.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, CartActivity.class);
                    intent.putParcelableArrayListExtra("selectedProducts", selectedProducts);
                    startActivity(intent, new Bundle());
                } else {
                    Toast.makeText(MainActivity.this, "Please select at least one item.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
