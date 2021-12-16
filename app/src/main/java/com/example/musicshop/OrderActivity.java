package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String userName;
    String goodsName;
    int quantity;
    double price;
    double orderPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();
        userName = receivedOrderIntent.getStringExtra("userName");
        goodsName = receivedOrderIntent.getStringExtra("goodsName");
        quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        price = receivedOrderIntent.getDoubleExtra("price", 0.00);
        orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0.00);

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText("Customer Name: " + userName
                + "\n" + "Goods Name: " + goodsName
                + "\n" + "Quantity: " + quantity
                + "\n" + "Price: " + price + " €"
                + "\n" + "Order Price: " + orderPrice + " €");
    }
}