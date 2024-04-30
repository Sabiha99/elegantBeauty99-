package com.companyname.elegantbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.Database.Databases;
import com.companyname.elegantbeauty.Model.Favourites;
import com.companyname.elegantbeauty.Model.Foundation;
import com.companyname.elegantbeauty.ViewHolder.FoundationViewHolder;
import com.companyname.elegantbeauty.common.Common;
import com.companyname.elegantbeauty.iInterface.OnItemClick;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Foundation_ING extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MaterialSearchBar materialSearchBar;
    FirebaseRecyclerAdapter adapter;
    FirebaseRecyclerAdapter searchAdapter;
    List<String> suggestionList=new ArrayList<>();

    String productId="";

    Databases localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("FoundationING");

        localDB=new Databases(this);

        recyclerView=findViewById(R.id.rv_product);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        materialSearchBar=findViewById(R.id.searchBar);
        materialSearchBar.setHint("Search");


        if(Common.isConnectedToInternet(this)) {

            loadFoundation();
        }
        else {
            Toast.makeText(Foundation_ING.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
            return;
        }
        loadSuggest();
        materialSearchBar.setLastSuggestions(suggestionList);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest= new ArrayList<String>();
                for(String search:suggestionList)
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                    {
                        suggest.add(search);
                    }
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                //WHEN SUGGEST BAR CLOSED RESTORE ORIGINAL SUGGEST ADAPTER
                if(!enabled)
                {
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                //SHOW RESULT ON SEARCH ADAPTER
                startSearch(text);
            }
            @Override
            public void onButtonClicked(int buttonCode) {
            }
        });
    }


    private void startSearch(CharSequence text) {
        FirebaseRecyclerOptions<Foundation> options =
                new FirebaseRecyclerOptions.Builder<Foundation>().setQuery(databaseReference, Foundation.class).build();

        searchAdapter = new FirebaseRecyclerAdapter<Foundation, FoundationViewHolder>(options) {

            @NonNull
            @NotNull
            @Override
            public FoundationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.product_items, parent, false);
                return new FoundationViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull FoundationViewHolder holder, int position, @NonNull @NotNull Foundation model){
                holder.product_Name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.product_image);
                final Foundation clickItem = model;

                holder.setOnItemClick(new OnItemClick() {
                    @Override
                    public void OnClick(View view, int pos, boolean isLongClick) {
                        Intent productDetail= new Intent(Foundation_ING.this,FoundationING_detail.class);
                        productDetail.putExtra("BrandNo", searchAdapter.getRef(pos).getKey());
                        startActivity(productDetail);
                    }
                });
            }
        };
        searchAdapter.startListening();
        recyclerView.setAdapter(searchAdapter);
    }



    private void loadSuggest() {
        databaseReference.orderByChild("BrandNo").equalTo(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot:snapshot.getChildren())
                {
                    Foundation item=postSnapshot.getValue(Foundation.class);
                    suggestionList.add(item.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    };



private void loadFoundation() {

        FirebaseRecyclerOptions<Foundation> options =
        new FirebaseRecyclerOptions.Builder<Foundation>().setQuery(databaseReference, Foundation.class).build();

        adapter = new FirebaseRecyclerAdapter<Foundation, FoundationViewHolder>(options) {
@Override
public void onBindViewHolder(@androidx.annotation.NonNull FoundationViewHolder holder, int pos, @androidx.annotation.NonNull Foundation model) {
        holder.product_Name.setText(model.getName());
        Picasso.get().load(model.getImage()).into(holder.product_image);

        ////Changing favourite icon
        if(localDB.ifFavourite(adapter.getRef(pos).getKey(),Common.currentUser.getPhone()))
        {
        holder.img_fav.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        holder.img_fav.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

        Favourites favourites=new Favourites();
        favourites.setBrandId(adapter.getRef(pos).getKey());
        favourites.setUserPhone(Common.currentUser.getPhone());
        favourites.setProductName(model.getName());
        favourites.setProductImage(model.getImage());
        favourites.setProductDescription(model.getDescription());
        favourites.setProductPrice(model.getPrice());
        favourites.setProductDiscount(model.getDiscount());
        favourites.setProductBrandNo(model.getBrandNo());


        if(!localDB.ifFavourite(adapter.getRef(pos).getKey(),Common.currentUser.getPhone()))
        {
        localDB.addToFavourite(favourites);
        holder.img_fav.setImageResource(R.drawable.ic_baseline_favorite_24);
        Toast.makeText(Foundation_ING.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
        }
        else
        {
        localDB.removeFromFavourite(adapter.getRef(pos).getKey(),Common.currentUser.getPhone());
        holder.img_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        Toast.makeText(Foundation_ING.this, "Removed from Favourites", Toast.LENGTH_SHORT).show();
        }
        }
        });
final Foundation clickItem = model;


        holder.setOnItemClick(new OnItemClick() {
@Override
public void OnClick(View view, int pos, boolean isLongClick) {
        Intent productDetail= new Intent(Foundation_ING.this,FoundationING_detail.class);
        productDetail.putExtra("BrandNo", adapter.getRef(pos).getKey());
        startActivity(productDetail);
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
