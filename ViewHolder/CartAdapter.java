package com.companyname.elegantbeauty.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.companyname.elegantbeauty.Model.Order;
import com.companyname.elegantbeauty.R;
import com.companyname.elegantbeauty.common.Common;
import com.companyname.elegantbeauty.iInterface.OnItemClick;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {
    TextView cartName,cartPrice,cartQuantity;
    ImageView cartCount;
    private OnItemClick onItemClick;

    public CardViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        cartName=itemView.findViewById(R.id.cartItemName);
        cartPrice=itemView.findViewById(R.id.cartItemPrice);
        cartCount=itemView.findViewById(R.id.cartItemCount);
        cartQuantity=itemView.findViewById(R.id.cartItemQuantity);
        itemView.setOnCreateContextMenuListener(this::onCreateContextMenu);
    }

    public void setCartName(TextView cartName) {

        this.cartName = cartName;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Option:");
        menu.add(0,0,getAdapterPosition(), Common.Delete);

    }
}

public class CartAdapter extends RecyclerView.Adapter<CardViewHolder>{

    private List<Order> listData =new ArrayList<>();

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    private Context context;
    @NonNull
    @NotNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.cart_layout,parent,false);
        return  new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CardViewHolder holder, int position) {
        TextDrawable drawable=TextDrawable.builder().buildRound(""+listData.get(position).getQuantity(), Color.BLACK);
        holder.cartCount.setImageDrawable(drawable);

        holder.cartPrice.setText(listData.get(position).getPrice());
        holder.cartName.setText(listData.get(position).getProductName());

       // String price =listData.get(position).getPrice();
        String s=listData.get(position).getQuantity();
        int price2=Integer.parseInt(s);
        int price = (Integer.parseInt(listData.get(position).getPrice()))*price2;

               // *(TextUtils.isEmpty(listData.get(position).getQuantity()) ? "0" : listData.get(position).getQuantity());
        //double price = (Double.parseDouble(listData.get(position).getPrice()))*(Double.parseDouble(listData.get(position).getQuantity()));
       // holder.cartPrice.setText(listData.get(position).getPrice());
        //holder.cartQuantity.setText(String.valueOf(price2));
        holder.cartPrice.setText(String.valueOf(price));

        //*(Integer.parseInt(listData.get(position).getQuantity()));
       // holder.cartPrice.setText(fmt.format(price));
          //? "0" : listData.get(position).getPrice()))


    }


    @Override
    public int getItemCount() {
        return listData.size();
    }


}
