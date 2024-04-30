package com.companyname.elegantbeauty.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.R;
import com.companyname.elegantbeauty.common.Common;
import com.companyname.elegantbeauty.iInterface.OnItemClick;

import org.jetbrains.annotations.NotNull;

public class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnCreateContextMenuListener{

    public TextView fav_Name,fav_price;
    public ImageView fav_image,fav_cart;


    //public LinearLayout view_foreground;



    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
   }

    private OnItemClick onItemClick;
    public FavouriteViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        fav_Name=itemView.findViewById(R.id.fav_Name);
        fav_image=itemView.findViewById(R.id.fav_Image);
        fav_cart=itemView.findViewById(R.id.fav_cart);
        fav_price=itemView.findViewById(R.id.fav_price);

      //  view_foreground=itemView.findViewById(R.id.view_foreground);
        itemView.setOnClickListener(this::onClick);
        itemView.setOnCreateContextMenuListener(this::onCreateContextMenu);
    }

    @Override
    public void onClick(View v) {
        onItemClick.OnClick(v,getAdapterPosition(),true);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Option:");
        menu.add(0,0,getAdapterPosition(), Common.Delete);

    }
}
