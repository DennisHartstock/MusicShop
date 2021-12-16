package com.example.musicshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int quantity = 0;
    Spinner spinner;
    ArrayList<String> spinnerArrayList;
    ArrayAdapter<String> spinnerAdapter;
    HashMap<String, Double> goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();

        createGoodsMap();
    }

    private void createGoodsMap() {
        goodsMap = new HashMap<>();
        goodsMap.put("Balalaika", 10.00);
        goodsMap.put("Drum", 20.00);
        goodsMap.put("Piano", 30.00);
    }

    private void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("Balalaika");
        spinnerArrayList.add("Drum");
        spinnerArrayList.add("Piano");

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void increaseQuantity(View view) {
        quantity++;
        TextView quantityValue = findViewById(R.id.quantityValue);
        quantityValue.setText(String.valueOf(quantity));
        TextView priceValueTextView = findViewById(R.id.priceValueTextView);
        priceValueTextView.setText(String.format("%s €", price * quantity));
    }

    public void degreaseQuantity(View view) {
        quantity--;
        if (quantity < 0) quantity = 0;
        TextView quantityValue = findViewById(R.id.quantityValue);
        quantityValue.setText(String.valueOf(quantity));
        TextView priceValueTextView = findViewById(R.id.priceValueTextView);
        priceValueTextView.setText(String.format("%s €", price * quantity));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = goodsMap.get(goodsName);
        TextView priceValueTextView = findViewById(R.id.priceValueTextView);
        priceValueTextView.setText(String.format("%s €", price * quantity));

        ImageView spinnerImageView = findViewById(R.id.spinnerImageView);

        switch (goodsName) {
            case "Balalaika":
                spinnerImageView.setImageResource(R.drawable.balalaika);
                break;
            case "Drum":
                spinnerImageView.setImageResource(R.drawable.drum);
                break;
            case "Piano":
                spinnerImageView.setImageResource(R.drawable.piano);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addToCart(View view) {
        Order order = new Order();

        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.price = price;
        order.orderPrice = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);

        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);

    }
}