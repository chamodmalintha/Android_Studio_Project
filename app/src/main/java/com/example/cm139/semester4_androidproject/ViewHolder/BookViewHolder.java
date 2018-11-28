package com.example.cm139.semester4_androidproject.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cm139.semester4_androidproject.Interface.ItemClickListener;
import com.example.cm139.semester4_androidproject.R;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView book_Name;
    public ImageView book_Image;

    private ItemClickListener itemClickListener;



    public BookViewHolder(@NonNull View itemView) {
        super(itemView);

        book_Name = (TextView)itemView.findViewById(R.id.book_name);
        book_Image = (ImageView)itemView.findViewById(R.id.book_image);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
    }
}
