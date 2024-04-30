package com.companyname.elegantbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import android.view.View;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    TabLayout tablelayout;
    ViewPager2 viewpager;
    FloatingActionButton facebook,google,twitter;
    EditText email,phone,password;

     float v=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tablelayout=findViewById(R.id.tabId);
        viewpager=findViewById(R.id.viewId);
        facebook=findViewById(R.id.facebookId);
        google=findViewById(R.id.googleId);
        twitter=findViewById(R.id.twitterId);
        password=findViewById(R.id.password);
        //email = findViewById(R.id.email);
        phone = findViewById(R.id.phoneNo);
       // forget_pass = findViewById(R.id.forgetId);



        LoginAdapter adapter=new LoginAdapter(this);
        viewpager.setAdapter(adapter);
        //viewpager.addOnAttachStateChangeListener((View.OnAttachStateChangeListener) new TabLayout.TabLayoutOnPageChangeListener(tablelayout));

        new TabLayoutMediator(tablelayout, viewpager, (tab, position) -> {

           // tablelayout.addTab(tablelayout.newTab().setText("Login"));
           // tablelayout.addTab(tablelayout.newTab().setText("Signup"));
            tablelayout.setTabGravity(TabLayout.GRAVITY_FILL);
            if(position==1)
                    tab.setText("Signup");
            else
                    tab.setText("Login");

        }).attach();

        facebook.setTranslationY(200);
        google.setTranslationY(300);
        twitter.setTranslationY(300);
        tablelayout.setTranslationX(300);


        facebook.setAlpha(v);
        google.setAlpha(v);
        twitter.setAlpha(v);
        tablelayout.setAlpha(v);

        facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        tablelayout.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,GoogleSigninActivity.class);
               // intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });


    }

}