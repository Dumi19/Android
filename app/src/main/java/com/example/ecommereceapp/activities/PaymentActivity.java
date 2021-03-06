package com.example.ecommereceapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ecommereceapp.R;

public class PaymentActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView subTotal, discount, shipping,total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //ToolBar


        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        double amount= 0.0;
        amount= getIntent().getDoubleExtra("amount",0);

        subTotal= findViewById(R.id.sub_total);
        discount= findViewById(R.id.textView17);
        shipping= findViewById(R.id.textView18);
        total= findViewById(R.id.total_amt);

        subTotal.setText(amount+"DKK");





    }
}