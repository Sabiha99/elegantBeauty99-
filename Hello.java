package com.companyname.elegantbeauty;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.Model.Brand;
import com.companyname.elegantbeauty.ViewHolder.BrandsViewHolder;
import com.companyname.elegantbeauty.common.Common;
import com.companyname.elegantbeauty.iInterface.OnItemClick;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class Hello extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Filterable {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    TextView FullName;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activy_hello);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Brands Name");
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Brand");

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
                Intent cartintent=new Intent(Hello.this,Cart_Activity.class);
                startActivity(cartintent);

            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //////////////SET USERS NAME//////////////////
        View headerView = navigationView.getHeaderView(0);
        FullName = headerView.findViewById(R.id.fullName);
       // FullName.setText(Common.currentUser.getName());
        recyclerView = findViewById(R.id.nav_host_fragment_content_hello2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(Common.isConnectedToInternet(this)) {

            loadBrands();
        }
        else{
            Toast.makeText(Hello.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    private void loadBrands() {
        FirebaseRecyclerOptions<Brand> options =
                new FirebaseRecyclerOptions.Builder<Brand>().setQuery(databaseReference, Brand.class).build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Brand, BrandsViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull BrandsViewHolder holder, int pos, @NonNull Brand model) {
                holder.txtBrandsName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.imageView);
                final Brand clickItem = model;


                holder.setOnItemClick(new OnItemClick() {
                    @Override
                    public void OnClick(View view, int pos, boolean isLongClick) {
                        Toast.makeText(Hello.this, "" + clickItem.getName(), Toast.LENGTH_SHORT).show();
                        if(pos==0)
                        {
                            Intent intent = new Intent(Hello.this, Brand1Activity.class);
                            startActivity(intent);
                        }
                        else if(pos==1)
                        {
                            Intent intent = new Intent(Hello.this, Brand_2.class);
                            startActivity(intent);
                        }
                        else if(pos==2)
                        {
                            Intent intent = new Intent(Hello.this, Brand_3.class);
                            startActivity(intent);
                        }
                        else if(pos==3)
                        {
                            Intent intent = new Intent(Hello.this,Brand_4.class);
                            startActivity(intent);
                        }
                        else if(pos==4)
                        {
                            Intent intent = new Intent(Hello.this, Brand_5.class);
                            startActivity(intent);
                        }
                        else if(pos==5)
                        {
                            Intent intent = new Intent(Hello.this, Brand_6.class);
                            startActivity(intent);
                        }

                         else if(pos==6)
                        {
                            Intent intent = new Intent(Hello.this, Brand_7.class);
                            startActivity(intent);
                        }
                        else if(pos==7)
                        {
                            Intent intent = new Intent(Hello.this, Brand_8.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(Hello.this, brand_09.class);
                            startActivity(intent);
                        }
                    }
                });
            }

            @NonNull
            @Override
            public BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.brands_name, parent, false);
                return new BrandsViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

        @Override
        public void onPointerCaptureChanged ( boolean hasCapture){

        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.hello, menu);
            return true;
        }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.nav_brand){
           recreate();
        }
        else if(id==R.id.nav_cart){
            Intent cartIntent=new Intent(Hello.this,Cart_Activity.class);
            startActivity(cartIntent);
        }
        else if(id==R.id.nav_order){
            Intent orderIntent=new Intent(Hello.this,Order.class);
            startActivity(orderIntent);
        }
        else if(id==R.id.nav_profile){
            Intent profileIntent=new Intent(Hello.this,UserProfile.class);
            startActivity(profileIntent);
        }
        else if(id==R.id.nav_favourites){
            Intent favIntent=new Intent(Hello.this,Favourites_Activity.class);
            startActivity(favIntent);

        }

        else if(id==R.id.nav_logout){
            AlertDialog.Builder builder=new AlertDialog.Builder(Hello.this);
            builder.setTitle("Logout");
            builder.setMessage("Are you sure you want to logout?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Hello.this.finishAffinity();
                   // System.exit(0);
                    Intent intent = new Intent(Hello.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);// this will stop and go to new activity
                    startActivity(intent);

                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        /*if(id==R.id.menu_search)
        {
            startActivity(new Intent(Hello.this,Search.class));
        }*/
        return super.onOptionsItemSelected(item);

    }

    @Override
    public Filter getFilter() {
        return null;
    }
}





