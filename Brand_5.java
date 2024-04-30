package com.companyname.elegantbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Brand_5 extends AppCompatActivity {


    TextView textView,textView2,textView3,textView4,textView5,textView11,textView7,textView1,textView8,textView9,textView10,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_5);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView7=findViewById(R.id.textView7);
        textView11=findViewById(R.id.textView11);
        textView10=findViewById(R.id.textView10);
        textView1=findViewById(R.id.textView1);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);
        textView6=findViewById(R.id.textView6);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this, Foundation_LA.class);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this, Concealer_LA.class);
                startActivity(intent);

            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this, Primer_LA.class);
                startActivity(intent);

            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this, Highlighter_LA.class);
                startActivity(intent);

            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this,Blush_LA.class);
                startActivity(intent);

            }
        });

        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this,SettingSpray_LA.class);
                startActivity(intent);

            }
        });


        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this,Contour_LA.class);
                startActivity(intent);

            }
        });

        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this,Powder_LA.class);
                startActivity(intent);

            }
        });

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this,EyeBrow_LA.class);
                startActivity(intent);

            }
        });
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this,MascaraLA.class);
                startActivity(intent);

            }
        });
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this,EyeLiner_LA.class);
                startActivity(intent);

            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand_5.this,LipstickLA.class);
                startActivity(intent);

            }
        });
    }
}
