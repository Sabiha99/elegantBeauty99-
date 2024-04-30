package com.companyname.elegantbeauty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.Database.Databases;
import com.companyname.elegantbeauty.Model.Confirm;
import com.companyname.elegantbeauty.Model.Order;
import com.companyname.elegantbeauty.ViewHolder.CartAdapter;
import com.companyname.elegantbeauty.common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cart_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference request;
    TextView totalPrice;
    Button btnPlaceOrder;
    CartAdapter adapter;

    List<Order> cart =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = FirebaseDatabase.getInstance();
        request = database.getReference("A Confirmation");

        recyclerView=findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        totalPrice=findViewById(R.id.total);
        btnPlaceOrder=findViewById(R.id.btnPlaceOrder);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(cart.size()>0) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(Cart_Activity.this);
                    alert.setTitle("Enter your Address");
                    //alert.setMessage("Enter your Address");

                    final EditText editAddress = new EditText(Cart_Activity.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    editAddress.setLayoutParams(lp);
                    alert.setView(editAddress);//adding the edit text to the alertDialogue

                    alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Confirm confirm = new Confirm(
                                    Common.currentUser.getPhone(),
                                    Common.currentUser.getName(),
                                    editAddress.getText().toString(),
                                    totalPrice.getText().toString(),

                                    cart
                                    );
                            //Sending values to firebase
                            request.child(String.valueOf(System.currentTimeMillis()))
                                    .setValue(confirm);
                            //Deleting from the cart
                            new Databases(getBaseContext()).removeFromCart();
                            Toast.makeText(Cart_Activity.this, "Order have been placed", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    alert.show();
                }
                else {
                    Toast.makeText(Cart_Activity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
            }




        });
        
        loadList();
    }

    private void loadList() {
       // Query getOrderByUser=request.orderByChild("phone").equalTo(phone);

        cart=new Databases(this).getCarts();
        adapter=new CartAdapter(cart,this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


        int total=0;
        for (Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        totalPrice.setText(String.valueOf(total));


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals(Common.Delete))
        {
            deleteCart(item.getOrder());
        }
        return true;
    }

    private void deleteCart(int position) {
        cart.remove(position);  //delete from cart
        new Databases(this).removeFromCart(); //delete data from SQ DB
        for(Order item:cart)
        {
            new Databases(this).addToCart(item); //updating new data from the List to SQ DB
        }
        loadList();
    }
}