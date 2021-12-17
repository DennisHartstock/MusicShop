package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String userName;
    String goodsName;
    int quantity;
    double price;
    double orderPrice;
    String orderContent;
    String[] addresses = {"barbarden@web.de"};
    String subject = "Order from Music Shop App";


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

        orderContent = "Customer Name: " + userName + "\n" +
                "Goods Name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + price + " €" + "\n" +
                "Order Price: " + orderPrice + " €";
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(orderContent);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, orderContent);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}