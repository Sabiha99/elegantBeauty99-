package com.companyname.elegantbeauty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.companyname.elegantbeauty.Database.Databases;
import com.companyname.elegantbeauty.Model.Foundation;
import com.companyname.elegantbeauty.Model.Order;
import com.companyname.elegantbeauty.Model.Rating;
import com.companyname.elegantbeauty.common.Common;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class Product_detail extends AppCompatActivity  {

    TextView product_name,product_price,product_description,quantity,feedBack;
    ImageView product_image,add_item,sub_item;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart,btnRate;
    int total_quantity=1;
    String productId="";
    RatingBar ratingBar;
    FirebaseDatabase database;
    DatabaseReference products;
    DatabaseReference ratingTable;
    Foundation foundation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        database=FirebaseDatabase.getInstance();
        products=database.getReference("Foundation");
        ratingTable=database.getReference("Rating");

        product_name=findViewById(R.id.productName);
        product_price=findViewById(R.id.product_price);
        product_description=findViewById(R.id.product_description);
        btnCart=findViewById(R.id.btncart);
        btnRate=findViewById(R.id.btnrate);
        ratingBar=findViewById(R.id.ratingBar);
        feedBack=findViewById(R.id.txtfeedBack);
        collapsingToolbarLayout=findViewById(R.id.collapsing);
        product_image=findViewById(R.id.img_product);
        add_item=findViewById(R.id.add_item);
        sub_item=findViewById(R.id.sub_item);
        quantity=findViewById(R.id.quantity);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new Databases(getBaseContext()).addToCart(new Order(
                        productId,
                        foundation.getName(),
                        quantity.getText().toString(),
                        foundation.getPrice(),
                        foundation.getDiscount()

                ));
                Toast.makeText(Product_detail.this, "Added to Cart", Toast.LENGTH_LONG).show();
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
            }
        });

        /////////GETTING Product ID FROM TNTENT////////////
        if(getIntent()!=null)
        {
            productId=getIntent().getStringExtra("BrandNo");
        }
        if(!productId.isEmpty())
        {

            if(Common.isConnectedToInternet(this)) {

                getDetails(productId);
                getRatings(productId);

            }
            else {
                Toast.makeText(Product_detail.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                return;
            }

        }


        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total_quantity<10)
                {
                    total_quantity++;
                    quantity.setText(String.valueOf(total_quantity));
                }

            }
        });

        sub_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total_quantity>1)
                {
                    total_quantity--;
                    quantity.setText(String.valueOf(total_quantity));
                }


            }
        });
    }

    private void getRatings(String productId) {

        Query productRating=ratingTable.orderByChild("productId").equalTo(productId);
        productRating.addValueEventListener(new ValueEventListener() {

            int count=0;
            int sum=0;
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot:snapshot.getChildren())
                {
                    Rating item=postSnapshot.getValue(Rating.class);
                    sum+=item.getRateValue();
                    count++;
                }
                if(count!=0) {
                    float avg = sum / count;
                    ratingBar.setRating(avg);
                }
                if (ratingBar.getRating()==0 || ratingBar.getRating()==0.5){
                    feedBack.setText("Very Dissatisfied!");
                }
                else if (ratingBar.getRating()==1 || ratingBar.getRating()==1.5){
                    feedBack.setText("Dissatisfied!");

                }else if (ratingBar.getRating() ==2 ||ratingBar.getRating()==2.5){
                    feedBack.setText("OK!");

                }else if (ratingBar.getRating() ==3 || ratingBar.getRating()==3.5){
                    feedBack.setText("Good");

                }else if (ratingBar.getRating()==4 || ratingBar.getRating()==4.5){
                    feedBack.setText("Very Good");

                }else {
                    feedBack.setText("Excellent");

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void showRatingDialog() {
        ViewGroup viewGroup=findViewById(android.R.id.content);

        AlertDialog.Builder builder=new AlertDialog.Builder(Product_detail.this);
        builder.setTitle("Rate this Product");
        builder.setMessage("Please fill the information");

        View itemView= LayoutInflater.from(Product_detail.this).inflate(R.layout.layout_rating,viewGroup,false);
         RatingBar ratingBar2=itemView.findViewById(R.id.ratingBar2);
        EditText edtComment=itemView.findViewById(R.id.edtComment);
        TextView feedBack2=itemView.findViewById(R.id.feedback2);
        builder.setView(itemView);

        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar2, float rating, boolean fromUser) {
                if (rating==0 || rating==0.5){
                    feedBack2.setText("Very Dissatisfied!");
                }
                else if (rating ==1 || rating==1.5){
                    feedBack2.setText("Dissatisfied!");

                }else if (rating ==2 ||rating==2.5){
                    feedBack2.setText("OK!");

                }else if (rating ==3 || rating==3.5){
                    feedBack2.setText("Good");

                }else if (rating==4 || rating==4.5){
                    feedBack2.setText("Very Good");

                }else {
                    feedBack2.setText("Excellent");

                }

            }
        });

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
                    public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Product_detail.this, "Thanks for your feedback.", Toast.LENGTH_SHORT).show();
                Rating rating=new Rating(
                        Common.currentUser.getPhone(),
                        productId,
                        ratingBar2.getRating(),
                       edtComment.getText().toString(),
                        foundation.getBrandNo()

                );
               ratingTable.child(String.valueOf(System.currentTimeMillis())).setValue(rating);

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog=builder.create();
        builder.show();


    }

    private void getDetails(String productId) {
        products.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                foundation = snapshot.getValue(Foundation.class);
                Picasso.get().load(foundation.getImage()).into(product_image);
                collapsingToolbarLayout.setTitle(foundation.getName());
                product_price.setText(foundation.getPrice());
                product_name.setText(foundation.getName());
                product_description.setText(foundation.getDescription());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }


}