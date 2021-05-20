package com.example.ecommereceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommereceapp.R;
import com.example.ecommereceapp.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> list;
    int totalAmmount = 0;

    public MyCartAdapter(Context context, List<MyCartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.date.setText(list.get(position).getCurrentDate());
        holder.time.setText(list.get(position).getCurrentTime());
        holder.price.setText(list.get(position).getProductPrice()+"DKK");
        holder.name.setText(list.get(position).getProductName());
        holder.totalPrice.setText(String.valueOf(list.get(position).getProductPrice()));
        holder.totalQuantity.setText(list.get(position).getTotalQuantity());

        //Total amount
        totalAmmount = totalAmmount+ list.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmmount);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView date,price,name,time,totalQuantity, totalPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            totalQuantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);


        }
    }
}