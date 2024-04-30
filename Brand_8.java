package com.companyname.elegantbeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Brand_8 extends AppCompatActivity {
    private TextView textView,textView2,textView3,textView4,textView5,textView7,textView8,textView9,textView10,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_8);

        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView7=findViewById(R.id.textView7);
       // textView11=findViewById(R.id.textView11);
        textView10=findViewById(R.id.textView10);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);
        textView6=findViewById(R.id.textView6);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this, Foundation_ING.class);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this, Conceal_ING.class);
                startActivity(intent);

            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this, Primer_ING.class);
                startActivity(intent);

            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this, Highlighter_ING.class);
                startActivity(intent);

            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this,Blush_ING.class);
                startActivity(intent);

            }
        });


        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this,Lipstick_ING.class);
                startActivity(intent);

            }
        });

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this,EyeBrow_ING.class);
                startActivity(intent);

            }
        });
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this,Mascara_ING.class);
                startActivity(intent);

            }
        });
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this,Powder_ING.class);
                startActivity(intent);

            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_8.this,EyeLiner_ING.class);
                startActivity(intent);

            }
        });
    }
    }
