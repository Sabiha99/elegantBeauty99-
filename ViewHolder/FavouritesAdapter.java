package com.companyname.elegantbeauty.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.Model.Favourites;
import com.companyname.elegantbeauty.Product_detail;
import com.companyname.elegantbeauty.R;
import com.companyname.elegantbeauty.iInterface.OnItemClick;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouriteViewHolder> {
    private Context context;
    private List<Favourites> favouritesList;


    public FavouritesAdapter(Context context, List<Favourites> favouritesList) {
        this.context = context;
        this.favouritesList = favouritesList;
    }

    @NonNull
    @NotNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.favourite_item,parent,false);
        return new FavouriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavouriteViewHolder holder, int position) {


        holder.fav_Name.setText(favouritesList.get(position).getProductName());
        holder.fav_price.setText(favouritesList.get(position).getProductPrice());
        Picasso.get().load(favouritesList.get(position).getProductImage()).into(holder.fav_image);



        final Favourites clickItem = favouritesList.get(position);


        holder.setOnItemClick(new OnItemClick() {
            @Override
            public void OnClick(View view, int pos, boolean isLongClick) {
                //Toast.makeText(Product_List.this, "" + clickItem.getName(), Toast.LENGTH_SHORT).show();
                Intent productDetail= new Intent(context , Product_detail.class);
                productDetail.putExtra("BrandNo", favouritesList.get(pos).getBrandId());
                context.startActivity(productDetail);


            }
        });

    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }
 /*   public void removeItem(int position)
    {
        favouritesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Favourites item,int position)
    {
        favouritesList.add(position,item);
        notifyItemInserted(position);

    }*/
}
