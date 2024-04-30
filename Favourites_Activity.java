package com.companyname.elegantbeauty;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.Database.Databases;
import com.companyname.elegantbeauty.Model.Favourites;
import com.companyname.elegantbeauty.ViewHolder.FavouritesAdapter;
import com.companyname.elegantbeauty.common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Favourites_Activity extends AppCompatActivity  {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FavouritesAdapter adapter;

    //RelativeLayout rootLayout;
    List<Favourites> fav =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        recyclerView=findViewById(R.id.recycler_fav);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        loadFavourites();

    }

    private void loadFavourites() {
       // fav=new Databases(this).getFavorites();
       //// adapter=new FavouritesAdapter(fav,this);
        //adapter.notifyDataSetChanged();
       // recyclerView.setAdapter(adapter);
        adapter=new FavouritesAdapter(this,new Databases(this).getFavorites(Common.currentUser.getPhone()));
       adapter.notifyDataSetChanged();//after deleting it has automatically gone
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals(Common.Delete))
        {
            deleteFavourite(item.getOrder());
        }
        return true;
    }

    private void deleteFavourite(int pos) {
        //fav.remove(pos);  //delete from cart
        //fav.remove(pos);

            new Databases(this).cleanFromFavourite();//delete data from SQ DB
            for (Favourites item : fav) {
                new Databases(this).addToFavourite(item); //updating new data from the List to SQ DB
            }
            loadFavourites();
        }
    }


