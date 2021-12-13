package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increaseQuantity(View view) {
        quantity++;
        TextView quantityValue = findViewById(R.id.quantityValue);
        quantityValue.setText("" + quantity);
    }

    public void degreaseQuantity(View view) {
        quantity--;
        if (quantity < 0) quantity = 0;
        TextView quantityValue = findViewById(R.id.quantityValue);
        quantityValue.setText("" + quantity);
    }
}