package com.companyname.elegantbeauty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.companyname.elegantbeauty.Model.User;
import com.companyname.elegantbeauty.common.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

//import android.widget.CompoundButton;

//import com.google.android.material.textfield.TextInputLayout;

public class LoginClass extends Fragment {
    EditText  phone, password;
    CheckBox showPassword;
    //TextInputLayout textInputLogin;
    Button btnlogin;
    float v = 0;
    TextView forget_pass, createAccount;
    //String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    FirebaseAuth fAuth;
    FirebaseUser fUser;
    ProgressDialog progressDialog;
     EditText resetMail;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_fragment, container, false);
        {

            //email = root.findViewById(R.id.email);
            phone = root.findViewById(R.id.phoneNo);
            password = root.findViewById(R.id.password);
            btnlogin = root.findViewById(R.id.login);
            forget_pass = root.findViewById(R.id.forgetId);
            // createAccount=root.findViewById(R.id.createAccount);
            showPassword = root.findViewById(R.id.showPass);
            progressDialog = new ProgressDialog(requireActivity());

            fAuth = FirebaseAuth.getInstance();
            fUser = fAuth.getCurrentUser();

            FirebaseDatabase database= FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=database.getReference("User");


           /* createAccount.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                   // startActivity(new Intent(getActivity(),SignupClass.class));
                   Intent intent=new Intent(getActivity(),SignupClass.class);
                   startActivity(intent);
                }
            });*/

            showPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            });

             forget_pass.setOnClickListener(new View.OnClickListener() {
              @Override
             public void onClick(View v) {
                  Intent intent = new Intent(requireActivity(), forgetphonePassword2.class);
                  startActivity(intent);
            // EditText resetMail= new EditText(v.getContext());
             // Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
              validateData();
           // AlertDialog.Builder passwordRestDialogue=new AlertDialog.Builder(v.getContext());
           // passwordRestDialogue.setTitle("Reset Password");
                   /* passwordRestDialogue.setMessage("Enter your email to reset the password");
                    passwordRestDialogue.setView(resetMail);
                    passwordRestDialogue.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String mail;
                            mail = resetMail.getText().toString();
                            fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(requireActivity(), "A link has been sent to your email to reset password", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Toast.makeText(requireActivity(), "Error!Link is not sent"+e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    });passwordRestDialogue.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            passwordRestDialogue.create().show();

                        }
                    });*/

             }
              });

            ////////***Authentication***/////
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Common.isConnectedToInternet(getContext())) {
                        performAuth();

                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                if (snapshot.child(phone.getText().toString()).exists()) {

                                    User user = snapshot.child(phone.getText().toString()).getValue(User.class);
                                    user.setPhone(phone.getText().toString());

                                    if (user.getPassword().equals(password.getText().toString())) {

                                        Toast.makeText(requireActivity(), "Login Completed", Toast.LENGTH_SHORT).show();
                                        sendUsertoNextActivity();
                                        Common.currentUser = user;

                                    } else {

                                        password.setError("Enter correct password");

                                        Toast.makeText(requireActivity(), "Login failed", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                    }
                                } else {
                                    Toast.makeText(requireActivity(), "User does not exist", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });
                        // performAuth();
                    }
                    else{
                        Toast.makeText(requireActivity(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


            });
           // email.setTranslationX(800);
            phone.setTranslationX(800);
            password.setTranslationX(800);
            btnlogin.setTranslationX(800);
            forget_pass.setTranslationX(800);
            showPassword.setTranslationX(800);
            // createAccount.setTranslationX(800);
            //  textInputLogin.setTranslationX(800);

           // email.setAlpha(v);
            phone.setAlpha(v);
            password.setAlpha(v);
            btnlogin.setAlpha(v);
            forget_pass.setAlpha(v);
            showPassword.setAlpha(v);
            // createAccount.setAlpha(v);

          //  email.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
            phone.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();
            password.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
            btnlogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(700).start();
            //createAccount.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(700).start();
            forget_pass.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
            showPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
            //textInputLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();

            return root;

        }
    }

    private void validateData() {

        String mail=resetMail.getText().toString();
        fAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Check your email.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Error!Link is not sent."+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /////////**********Authentication***********//////
       private void performAuth () {
          //  String getEmail = email.getText().toString();
            String getPass = password.getText().toString();
            String getPhone = phone.getText().toString();

            if (getPass.isEmpty()) {
                password.setError("Enter password");
            }
                else if(getPhone.isEmpty())
                {
                   phone.setError("Enter your phone number");
                }
             else {
                progressDialog.setMessage("Please wait");
                progressDialog.setTitle("Login");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                //progressDialog.dismiss();



            }
        }

    private void sendUsertoNextActivity() {
        Intent intent = new Intent(requireActivity(), Hello.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);// user succesfully registered,this will stop and go to new activity
        startActivity(intent);
    }
}

