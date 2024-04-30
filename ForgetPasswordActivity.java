package com.companyname.elegantbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextView back;
    Button forgetBtn;
    EditText forgetEmail;
   // String mail;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgett_password);

       back =findViewById(R.id.backToLogin);
        forgetBtn =findViewById(R.id.forgetBtn);
        forgetEmail =findViewById(R.id.forgetemail);
        fAuth = FirebaseAuth.getInstance();


       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
               startActivity(intent);
           }
       });

       forgetBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               validateData();
           }
       });
       }

    private void validateData() {
        String mail=forgetEmail.getText().toString();
        if(mail.isEmpty()) {
            forgetEmail.setError("Required");
        }
        else {

            fAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgetPasswordActivity.this, "Check your email.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);// user succesfully registered,this will stop and go to new activity
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ForgetPasswordActivity.this, "Error!Link is not sent." + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}