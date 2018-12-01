package com.example.cm139.semester4_androidproject.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cm139.semester4_androidproject.Interface.ItemClickListener;
import com.example.cm139.semester4_androidproject.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtOrderId,txtOrderStatus,txtOrderPhone,txtOrderAddress;
    private ItemClickListener itemClickListener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        txtOrderId =(TextView)itemView.findViewById(R.id.order_id);
        txtOrderPhone =(TextView)itemView.findViewById(R.id.order_phone);
        txtOrderStatus =(TextView)itemView.findViewById(R.id.order_status);
        txtOrderAddress =(TextView)itemView.findViewById(R.id.order_address);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListner(ItemClickListener itemClickListner) {
        this.itemClickListener = itemClickListner;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view ,getAdapterPosition(),false);

    }
}


