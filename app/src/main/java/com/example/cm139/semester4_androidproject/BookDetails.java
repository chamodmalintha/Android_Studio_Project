package com.example.cm139.semester4_androidproject;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.cm139.semester4_androidproject.Model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BookDetails extends AppCompatActivity {

    TextView book_name,book_price,book_description;
    ImageView book_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String bookId="";

    FirebaseDatabase database;
    DatabaseReference books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //init Firebase
        database = FirebaseDatabase.getInstance();
        books = database.getReference("Books");

        //init View
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btncart);

        book_description = (TextView)findViewById(R.id.book_description);
        book_name = (TextView)findViewById(R.id.details_book_name);
        book_price = (TextView)findViewById(R.id.book_price);
        book_image = (ImageView)findViewById(R.id.details_img_book);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        //get BookId from Intent
        if (getIntent() !=null)
            bookId = getIntent().getStringExtra("BookId");
        if (!bookId.isEmpty()){
            getDetailBook(bookId);
        }


    }

    private void getDetailBook(String bookId){
        books.child(bookId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);

                //Set Image
                Picasso.with(getBaseContext()).load(book.getImage()).into(book_image);

                collapsingToolbarLayout.setTitle(book.getName());

                book_price.setText(book.getPrice());
                book_name.setText(book.getName());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
