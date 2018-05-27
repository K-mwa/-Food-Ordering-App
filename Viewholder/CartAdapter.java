package com.younessharaki.apricot.Viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.younessharaki.apricot.Interface.ItemClickUI;
import com.younessharaki.apricot.Model.Order;
import com.younessharaki.apricot.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Youness Haraki on 06.05.2018.
 */

class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_cart_name,txt_price;
    public ImageView img_cart_count;
    private ItemClickUI itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public CardViewHolder(View itemView) {
        super(itemView);
        txt_cart_name =(TextView)itemView.findViewById(R.id.cart_item_name);
        img_cart_count =(ImageView) itemView.findViewById(R.id.cart_item_count);
        txt_price =(TextView) itemView.findViewById(R.id.cart_item_price);


    }

    @Override
    public void onClick(View view) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CardViewHolder>{


    private List<Order> ListData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.ListData = listData;
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View itemView =inflater.inflate(R.layout.cart_layout,parent,false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        TextDrawable drawable=TextDrawable.builder()
                .buildRound(""+ListData.get(position).getQuantity(), Color.RED);

        cardViewHolder.img_cart_count.setImageDrawable(drawable);

        Locale locale=new Locale("en","US");
        NumberFormat fmt =NumberFormat.getCurrencyInstance(locale);

        int price =(Integer.parseInt(ListData.get(position).getPrice()))*(Integer.parseInt(ListData.get(position).getQuantity()));
        cardViewHolder.txt_price.setText(fmt.format(price));
        cardViewHolder.txt_cart_name.setText(ListData.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }
}
