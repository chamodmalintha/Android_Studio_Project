 package com.example.cm139.semester4_androidproject;

 import android.support.v7.app.AlertDialog;
 import android.content.DialogInterface;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.widget.RecyclerView;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.LinearLayout;
 import android.widget.TextView;
 import android.widget.Toast;


 import com.example.cm139.semester4_androidproject.Common.Common;
 import com.example.cm139.semester4_androidproject.Database.Database;
 import com.example.cm139.semester4_androidproject.Model.Order;
 import com.example.cm139.semester4_androidproject.Model.Request;
 import com.example.cm139.semester4_androidproject.ViewHolder.CartAdapter;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;

 import java.text.NumberFormat;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Locale;






 public class Cart extends AppCompatActivity {

     RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutManager;

     FirebaseDatabase database;
     DatabaseReference requests;

     TextView txtTotalPrice;
     Button btnPlace;

     List<Order> cart=new ArrayList<>();
     CartAdapter adapter;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_cart);

         database=FirebaseDatabase.getInstance();
         requests =database.getReference("Requets");

         recyclerView=(RecyclerView)findViewById(R.id.listCart);
         recyclerView.setHasFixedSize(true);
         layoutManager=new LinearLayoutManager(this);
         recyclerView.setLayoutManager(layoutManager);

         txtTotalPrice=(TextView)findViewById(R.id.total);
         btnPlace=(Button)findViewById(R.id.btnPlaceOrder);
         btnPlace.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 showAleartDailog();
             }
         });

         LoadListFood();
     }

     private void showAleartDailog() {
         //Toast.makeText(getApplicationContext(),"fdgdg",Toast.LENGTH_SHORT).show();
         final AlertDialog.Builder alertDialog =new AlertDialog.Builder(Cart.this);
         alertDialog.setTitle("Client Details");
         alertDialog.setMessage("Enter your Address ..");

         //final String edtAddress="kjdhgdjkkldjhd";
         final EditText edtAddress=new EditText(Cart.this);
         LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(
                 LinearLayout.LayoutParams.MATCH_PARENT,
                 LinearLayout.LayoutParams.MATCH_PARENT
         );
         edtAddress.setLayoutParams(lp);
         alertDialog.setView(edtAddress);
         alertDialog.setIcon(R.drawable.ic_add_box_black_24dp);


         alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 Request request =new Request(
                         Common.currentUser.getPhone(),
                         Common.currentUser.getName(),
                         edtAddress.getText().toString(),
                         txtTotalPrice.getText().toString(),
                         cart);
                 requests.child(String.valueOf(System.currentTimeMillis()))
                         .setValue(request);
                 new Database(getBaseContext()).cleanCart();
                 Toast.makeText(Cart.this, "Thank you,Order Placed..", Toast.LENGTH_SHORT).show();

             }
         });
         alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 dialogInterface.dismiss();
             }
         });
         alertDialog.show();

     }

     private void LoadListFood() {
         cart =new Database(this).getCarts();
         adapter=new CartAdapter(cart,this);
         recyclerView.setAdapter(adapter);

       int total =0;
       for (Order order:cart){
//         total +=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
//       Locale locale =new Locale("en","US");
 //      NumberFormat fmt= NumberFormat.getCurrencyInstance(locale);
   //    txtTotalPrice.setText(fmt.format(total));

     }
     }
 }


