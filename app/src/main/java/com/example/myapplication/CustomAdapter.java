package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> productList;
    private TextView totalItemsTextView;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Product> productList, TextView totalItemsTextView) {
        this.context = context;
        this.productList = productList;
        this.totalItemsTextView = totalItemsTextView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.idTextView = convertView.findViewById(R.id.idTextView);
            holder.nameTextView = convertView.findViewById(R.id.nameTextView);
            holder.priceTextView = convertView.findViewById(R.id.priceTextView);
            holder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = productList.get(position);
        holder.idTextView.setText(String.valueOf(product.getId()));
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.valueOf(product.getPrice()));

        holder.checkBox.setChecked(product.isChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                productList.get(position).setChecked(checkBox.isChecked());
                updateTotalItems();
            }
        });
        if(holder.idTextView != null){
            updateTotalItems();
        }


        return convertView;
    }

    private void updateTotalItems() {
        int totalCheckedItems = 0;
        for (Product product : productList) {
            if (product.isChecked()) {
                totalCheckedItems++;
            }
        }
        if(totalItemsTextView != null){
            totalItemsTextView.setText("Total Items: " + totalCheckedItems);
        }
    }

    public ArrayList<Product> getSelectedProducts() {
        ArrayList<Product> selectedProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.isChecked()) {
                selectedProducts.add(product);
            }
        }
        return selectedProducts;
    }

    static class ViewHolder {
        TextView idTextView;
        TextView nameTextView;
        TextView priceTextView;
        CheckBox checkBox;
    }
}

