package com.example.musicshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("Balalaika");
        spinnerArrayList.add("Drum");
        spinnerArrayList.add("Piano");

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        goodsMap = new HashMap<>();
        goodsMap.put("Balalaika", 10.00);
        goodsMap.put("Drum", 20.00);
        goodsMap.put("Piano", 30.00);
    }

    public void increaseQuantity(View view) {
        quantity++;
        TextView quantityValue = findViewById(R.id.quantityValue);
        quantityValue.setText(String.valueOf(quantity));
        TextView priceValueTextView = findViewById(R.id.priceValueTextView);
        priceValueTextView.setText(String.format("%s€", price * quantity));
    }

    public void degreaseQuantity(View view) {
        quantity--;
        if (quantity < 0) quantity = 0;
        TextView quantityValue = findViewById(R.id.quantityValue);
        quantityValue.setText(String.valueOf(quantity));
        TextView priceValueTextView = findViewById(R.id.priceValueTextView);
        priceValueTextView.setText(String.format("%s€", price * quantity));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = goodsMap.get(goodsName);
        TextView priceValueTextView = findViewById(R.id.priceValueTextView);
        priceValueTextView.setText(String.format("%s€", price * quantity));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}