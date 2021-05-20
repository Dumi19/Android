package com.example.ecommereceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecommereceapp.R;
import com.example.ecommereceapp.adapter.AddressAdapter;
import com.example.ecommereceapp.models.AddressModel;
import com.example.ecommereceapp.models.MyCartModel;
import com.example.ecommereceapp.models.NewProductsModel;
import com.example.ecommereceapp.models.PopularProductsModel;
import com.example.ecommereceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress {

    Button addAdress;
    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Button addAdressButton, paymentButton;
    Toolbar toolbar;
    String mAdress = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        toolbar = findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Button to go back repetead
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Get Datafrom detailedActivity
        Object object = getIntent().getSerializableExtra("item");


        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.address_recycler);
        paymentButton = findViewById(R.id.payment_btn);
        addAdressButton = findViewById(R.id.add_address_btn);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList = new ArrayList<>();
        addressAdapter = new AddressAdapter(addressModelList, getApplicationContext(), this);

        recyclerView.setAdapter(addressAdapter);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        AddressModel addressModel = doc.toObject(AddressModel.class);
                        addressModelList.add(addressModel);
                        addressAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = 0.0;
            if(object instanceof NewProductsModel)
            {
                NewProductsModel newProductsModel = (NewProductsModel)object;
                amount = newProductsModel.getPrice();
            }
                if(object instanceof PopularProductsModel)
                {
                    PopularProductsModel popularProductsModel = (PopularProductsModel) object;
                    amount = popularProductsModel.getPrice();
                }
                if(object instanceof ShowAllModel)
                {
                    ShowAllModel showAllModel = (ShowAllModel) object;
                    amount = showAllModel.getPrice();
                }
                Intent intent=new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });

        addAdress = findViewById(R.id.add_address_btn);

        addAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, AddAdressActivity.class));
            }
        });
    }

    @Override
    public void setAddress(String address) {
        mAdress = address;
    }
}