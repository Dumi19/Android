package com.example.ecommereceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommereceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAdressActivity extends AppCompatActivity {

    EditText name, address, city, postNr, phoneNumber;
    Toolbar toolbar;
    Button addAddreessBar;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        toolbar = findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_address);
        city = findViewById(R.id.ad_city);
        phoneNumber = findViewById(R.id.ad_phone);
        postNr = findViewById(R.id.ad_code);
        addAddreessBar = findViewById(R.id.ad_add_address);


        addAddreessBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userCity = city.getText().toString();
                String userAddress = address.getText().toString();
                String userPostNr = postNr.getText().toString();
                String userPhone = phoneNumber.getText().toString();

                String final_adress = "";
                if (!userName.isEmpty()) {
                    final_adress += userName;
                }
                if (!userCity.isEmpty()) {
                    final_adress += userCity;
                }
                if (!userAddress.isEmpty()) {
                    final_adress += userAddress;
                }
                if (!userPhone.isEmpty()) {
                    final_adress += userPhone;
                }
                if (!userPostNr.isEmpty()) {
                    final_adress += userPostNr;
                }
                if (!userName.isEmpty() && !userPostNr.isEmpty() && !userPhone.isEmpty() && !userAddress.isEmpty()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("userAddress", final_adress);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddAdressActivity.this, "Address added", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddAdressActivity.this,DetailedActivity.class));
                                finish();
                            }
                        }
                    });
                } else {
                    Toast.makeText(AddAdressActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}