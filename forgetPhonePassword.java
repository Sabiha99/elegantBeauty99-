package com.companyname.elegantbeauty;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class forgetPhonePassword extends AppCompatActivity {
    Button forget_btn;
    CountryCodePicker countryCodePicker;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_phone_password);
        forget_btn=findViewById(R.id.forgetBtn);
        countryCodePicker=findViewById(R.id.countryCodePicker);
        editText=findViewById(R.id.forgetphone);

    }
}