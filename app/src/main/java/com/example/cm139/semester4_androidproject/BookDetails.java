package com.example.cm139.semester4_androidproject;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.cm139.semester4_androidproject.Database.Database;
import com.example.cm139.semester4_androidproject.Model.Book;
import com.example.cm139.semester4_androidproject.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BookDetails extends AppCompatActivity {

    TextView book_name,book_price;
    ImageView book_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String bookId="";

    FirebaseDatabase database;
    DatabaseReference books;

    Book currentBook;

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

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        bookId,
                        currentBook.getName(),
                        numberButton.getNumber(),
                        currentBook.getPrice()
                        //I havent mentioned discount before. in this line i should add discount

                ));

                Toast.makeText(BookDetails.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });


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
                currentBook = dataSnapshot.getValue(Book.class);

                //Set Image
                Picasso.with(getBaseContext()).load(currentBook.getImage()).into(book_image);

                collapsingToolbarLayout.setTitle(currentBook.getName());

                book_price.setText(currentBook.getPrice());
                book_name.setText(currentBook.getName());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
