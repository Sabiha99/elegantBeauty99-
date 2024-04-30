package com.companyname.elegantbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.companyname.elegantbeauty.Model.PowderLA;
import com.companyname.elegantbeauty.ViewHolder.FoundationViewHolder;
import com.companyname.elegantbeauty.iInterface.OnItemClick;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EyeBrow_ING extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference inglot;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        database = FirebaseDatabase.getInstance();
        inglot = database.getReference("EyeBrowING");

        recyclerView=findViewById(R.id.rv_product);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        load();
    }


    private void load() {

        FirebaseRecyclerOptions<PowderLA> options =
                new FirebaseRecyclerOptions.Builder<com.companyname.elegantbeauty.Model.PowderLA>().setQuery(inglot, com.companyname.elegantbeauty.Model.PowderLA.class).build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<com.companyname.elegantbeauty.Model.PowderLA, FoundationViewHolder>(options) {
            @Override
            public void onBindViewHolder(@androidx.annotation.NonNull FoundationViewHolder holder, int pos, @androidx.annotation.NonNull com.companyname.elegantbeauty.Model.PowderLA model) {
                holder.product_Name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.product_image);
                final com.companyname.elegantbeauty.Model.PowderLA clickItem = model;


                holder.setOnItemClick(new OnItemClick() {
                    @Override
                    public void OnClick(View view, int pos, boolean isLongClick) {
                        Toast.makeText(EyeBrow_ING.this, "" + clickItem.getName(), Toast.LENGTH_SHORT).show();


                    }
                });
            }

            @androidx.annotation.NonNull
            @Override
            public FoundationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.product_items, parent, false);
                return new FoundationViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}