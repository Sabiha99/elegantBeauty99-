package com.companyname.elegantbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Brand_6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView textView,textView2,textView3,textView4,textView5,textView7,textView8,textView9,textView10,textView6,textView12,textView11;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_1);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView7=findViewById(R.id.textView7);
        textView11=findViewById(R.id.textView11);
        textView12=findViewById(R.id.textView12);
        textView10=findViewById(R.id.textView10);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);
        textView6=findViewById(R.id.textView6);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this, Product_List.class);
                startActivity(intent);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this, Concealer_MAC.class);
                startActivity(intent);

            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this, Primer_MAC.class);
                startActivity(intent);

            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this, Highlighter_MAC.class);
                startActivity(intent);

            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this,Blush_MAC.class);
                startActivity(intent);

            }
        });


        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this,Lipstick_ING.class);
                startActivity(intent);

            }
        });

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this,Brow_MAC.class);
                startActivity(intent);

            }
        });
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this,Mascara_MAC.class);
                startActivity(intent);

            }
        });
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this,EyeLiner_MAC.class);
                startActivity(intent);

            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this,Bronzer_MAC.class);
                startActivity(intent);

            }
        });

        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this,Powdr_MAC.class);
                startActivity(intent);

            }
        });

        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_6.this,Setting_MAC.class);
                startActivity(intent);

            }
        });




    }
}