package com.companyname.elegantbeauty.ViewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.R;
import com.companyname.elegantbeauty.iInterface.OnItemClick;

import org.jetbrains.annotations.NotNull;

public class BrandsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtBrandsName;
    public ImageView imageView;
    private AdapterView.OnItemClickListener onItemClickListener;
    private OnItemClick onItemClick;
    public BrandsViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        txtBrandsName=itemView.findViewById(R.id.brands_Name);
        imageView=itemView.findViewById(R.id.brand_Image);



        itemView.setOnClickListener(this::onClick);

    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public void onClick(View v) {
        onItemClick.OnClick(v,getAdapterPosition(),false);

    }
}
