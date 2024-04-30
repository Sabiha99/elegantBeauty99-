package com.companyname.elegantbeauty.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.companyname.elegantbeauty.R;
import com.companyname.elegantbeauty.iInterface.OnItemClick;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrderAddress,txtOrderName,txtOrderDate;
     public  ImageView btnDelete;

    private OnItemClick itemClickListener;

    public OrderViewHolder (View itemView){
        super (itemView);

        txtOrderAddress=itemView.findViewById(R.id.order_address);
        txtOrderId=itemView.findViewById(R.id.order_id);
        txtOrderPhone=itemView.findViewById(R.id.order_phone);
        txtOrderStatus=itemView.findViewById(R.id.order_status);
        txtOrderName=itemView.findViewById(R.id.order_name);
        btnDelete=itemView.findViewById(R.id.btnDelete);
        txtOrderDate=itemView.findViewById(R.id.order_date);

        itemView.setOnClickListener(this::onClick);

    }
    public void setItemClickListener(OnItemClick itemClickListener){
       // this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View v) {
        //itemClickListener.OnClick(v,getAdapterPosition(),false);
    }
}
