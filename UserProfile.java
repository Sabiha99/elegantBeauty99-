package com.companyname.elegantbeauty;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.companyname.elegantbeauty.Model.User;
import com.companyname.elegantbeauty.common.Common;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextInputLayout name,email,phoneNo,password,address;
    TextView fullNameLabel,userNameLabel;
    Button btnUpdate;

    //Global variables to hold user data inside this activity
    String _USERNAME,_NAME,_EMAIL,_PHONENO,_PASSWORD;
    User user;
    FirebaseDatabase database= FirebaseDatabase.getInstance();

    DatabaseReference reference=database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hooks
         name = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.profile_name);
        address=findViewById(R.id.address);
        btnUpdate=findViewById(R.id.btnUpdate);

        showalldata();



        //  showAllUserData();
        btnUpdate.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                update(v);
            }
        });

    }

    private void showalldata() {

        name.getEditText().setText(Common.currentUser.getName());
        phoneNo.getEditText().setText(Common.currentUser.getPhone());
        password.getEditText().setText(Common.currentUser.getPassword());
        email.getEditText().setText(Common.currentUser.getEmail());
        address.getEditText().setText(Common.currentUser.getAddress());
        fullNameLabel.setText(Common.currentUser.getName());
    }

    private void update(android.view.View v) {

        if( isPasswordChanged() && isNameChanged()){

            Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
        }

        else if( isPasswordChanged()||isNameChanged()){

            Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
           // showalldata();
            //Intent profileIntent=new Intent(UserProfile.this,Hello.class);
          //  startActivity(profileIntent);

        }
       else{
            Toast.makeText(this, "Data  updated", Toast.LENGTH_SHORT).show();

        }
       // recreate();
    }

    private boolean isPasswordChanged() {

        if(!Common.currentUser.getPassword().equals(password.getEditText().getText().toString())){
            //reference.child(phoneNo.getEditText().getText().toString()).child("password").setValue(password.getEditText().getText().toString());
            //password.getEditText().setText(Common.currentUser.getPassword());
            //user = new User(password.getEditText().getText().toString(); //password.getText().toString(),phone.getText().toString());
            user = new User(name.getEditText().getText().toString(), password.getEditText().getText().toString(),phoneNo.getEditText().getText().toString());
            reference.child(phoneNo.getEditText().getText().toString()).setValue(user);
            // name.getEditText().setText(Common.currentUser.getPassword());
            //reference.child("password").setValue(password.getEditText().getText().toString());
          //  Common.currentUser.setPassword()= password.getEditText().getText().toString();
           // password.getEditText().setText(Common.currentUser.getPassword());//=password.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }
        private boolean isNameChanged() {

     if(!Common.currentUser.getName().equals(name.getEditText().getText().toString())){
       // reference.child(phoneNo.getEditText().getText().toString()).child("name").setValue(name.getEditText().getText().toString());
        // name.getEditText().setText(Common.currentUser.getPassword());
        //reference.child("password").setValue(password.getEditText().getText().toString());
        //  Common.currentUser.setPassword()= password.getEditText().getText().toString();
        // password.getEditText().setText(Common.currentUser.getPassword());//=password.getEditText().getText().toString();
        return true;
    }
        else {

        return false;
    }
}
   /* private boolean isNameChanged() {

        if(!_NAME.equals(name.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(name.getEditText().getText().toString());
            _NAME = name.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }*/

    }
