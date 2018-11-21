package com.example.cm139.semester4_androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.cm139.semester4_androidproject.Interface.ItemClickListener;
import com.example.cm139.semester4_androidproject.Model.Book;
import com.example.cm139.semester4_androidproject.ViewHolder.BookViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BookList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference bookList;
    String categoryId="";

    FirebaseRecyclerAdapter<Book,BookViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        //Init Firebase
        database= FirebaseDatabase.getInstance();
        bookList= database.getReference("Books");

        recyclerView= (RecyclerView)findViewById(R.id.recycler_book);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent
        if (getIntent() != null){
            categoryId = getIntent().getStringExtra("CategoryId");
        }
        if (!categoryId.isEmpty() && categoryId != null){
            loadListBook(categoryId);
        }

    }

    private void loadListBook(String categoryId){
        adapter = new FirebaseRecyclerAdapter<Book, BookViewHolder>(Book.class,
                R.layout.book_item,
                BookViewHolder.class,
                bookList.orderByChild("CategoryId").equalTo(categoryId) //select * from Books where CategoryId = categoryId;
                ) {
            @Override
            protected void populateViewHolder(BookViewHolder viewHolder, Book model, int position) {
                viewHolder.book_Name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.book_image);

                final Book local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start New Activity
                        Intent bookDetails = new Intent(BookList.this, BookDetails.class);
                        bookDetails.putExtra("BookId",adapter.getRef(position).getKey());       // send BookId to new Activity
                        startActivity(bookDetails);


                    }
                });

            }
        };

        //Set adapter
        recyclerView.setAdapter(adapter);


    }


}
