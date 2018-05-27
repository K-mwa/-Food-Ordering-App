package com.younessharaki.apricot.Viewholder;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.younessharaki.apricot.Interface.ItemClickUI;
import com.younessharaki.apricot.R;

/**
 * Created by Youness Haraki on 25.04.2018.
 */

public class MenuViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickUI itemClickListener;

    public MenuViewholder(View itemView) {
        super(itemView);
        txtMenuName = (TextView) itemView.findViewById(R.id.menu_name);
        imageView =(ImageView)itemView.findViewById(R.id.menu_image);
        itemView.setOnClickListener(this);

     }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickUI itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
