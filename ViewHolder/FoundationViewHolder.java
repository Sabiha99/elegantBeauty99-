package com.companyname.elegantbeauty.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.R;
import com.companyname.elegantbeauty.iInterface.OnItemClick;

import org.jetbrains.annotations.NotNull;

public class FoundationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView product_Name;
    public ImageView product_image,img_fav;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    private OnItemClick onItemClick;
    public FoundationViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        product_Name=itemView.findViewById(R.id.product_Name);
        product_image=itemView.findViewById(R.id.product_Image);
        img_fav=itemView.findViewById(R.id.fav);
        itemView.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        onItemClick.OnClick(v,getAdapterPosition(),false);

    }
}
