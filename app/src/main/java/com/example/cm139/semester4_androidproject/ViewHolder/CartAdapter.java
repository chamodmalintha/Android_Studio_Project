package com.example.cm139.semester4_androidproject.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;



import com.amulyakhare.textdrawable.TextDrawable;
import com.example.cm139.semester4_androidproject.Interface.ItemClickListener;
import com.example.cm139.semester4_androidproject.Model.Order;
import com.example.cm139.semester4_androidproject.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;










class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_cart_name, txt_price;

    private ItemClickListener itemClickListener;

    public void setTxtCartName(TextView txt_cart_name){
        this.txt_cart_name =txt_cart_name;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_cart_name =(TextView)itemView.findViewById(R.id.cart_item_name);
        txt_price=(TextView)itemView.findViewById(R.id.cart_item_Price);

    }

    @Override
    public void onClick(View view) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Order> listData;
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View itemView =inflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        TextDrawable drawable =TextDrawable.builder()
                .buildRound(""+ listData.get(position).getQuantity(), Color.RED);
        Locale locale =new Locale("","SL");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        int price =(Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_price.setText(fmt.format(price));
        holder.txt_cart_name.setText(listData.get(position).getProductName());

    }





    @Override
    public int getItemCount() {

        return listData.size();
    }
}
