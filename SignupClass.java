package com.companyname.elegantbeauty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.companyname.elegantbeauty.Model.User;
import com.companyname.elegantbeauty.common.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class SignupClass extends Fragment {

   // TextView haveAccount;
    EditText  phone, password,userName;
    Button btnSignupn;

    FirebaseAuth fAuth;
    FirebaseUser fUser;
    ProgressDialog progressDialog;
    //User user;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=database.getReference("User");

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_fragment, container,false);
        {
            password=root.findViewById(R.id.password);
            phone = root.findViewById(R.id.phoneNo);
            btnSignupn = root.findViewById(R.id.btnSignup);
            userName=root.findViewById(R.id.userNameId);
            progressDialog = new ProgressDialog(requireActivity());
            fAuth = FirebaseAuth.getInstance();
            fUser = fAuth.getCurrentUser();
          //  FirebaseDatabase database= FirebaseDatabase.getInstance();
           // DatabaseReference databaseReference=database.getReference("User");

            btnSignupn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Common.isConnectedToInternet(getContext())) {

                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                                //////check if allready exist////////
                                if (snapshot.child(phone.getText().toString()).exists()) {

                                    Toast.makeText(requireActivity(), " Already have an account", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }

                                else {
                                    performAuth();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });

                    }
                    else {
                        Toast.makeText(requireActivity(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }

            return root;
        }

    private void performAuth() {

        String getPass = password.getText().toString();
        String getPhone = phone.getText().toString();
        String getName = userName.getText().toString();


             if(getPhone.isEmpty())
            {
                phone.setError("Enter your phone number");
            }
             else if(getName.isEmpty())
             {
                 userName.setError("Enter your name");
             }
         else if (getPass.isEmpty() ) {
            password.setError("Enter a password");
        }else if(getPass.length() < 6){
            password.setError("Enter at least 6 characters");
        }
        else {
                User user = new User(userName.getText().toString(), password.getText().toString(),phone.getText().toString());

                 databaseReference.child(phone.getText().toString()).setValue(user);
                 Toast.makeText(requireActivity(), "Signup Completed", Toast.LENGTH_SHORT).show();
                // progressDialog.dismiss();
            progressDialog.setMessage("Please wait");
            progressDialog.setTitle("Registration");

            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
                 sendUsertoNextActivity();


             }
}

    private void sendUsertoNextActivity() {
        Intent intent=new Intent(requireActivity(),LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);// user succesfully registered,this will stop and go to new activity
        startActivity(intent);
    }
}


