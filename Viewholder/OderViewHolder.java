package com.younessharaki.apricot.Viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.younessharaki.apricot.Interface.ItemClickUI;
import com.younessharaki.apricot.R;

/**
 * Created by Youness Haraki on 09.05.2018.
 */

public class OderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtOrderId,txtOrderStatus,txtOrderPhone,txtOderAdress;

    private ItemClickUI itemClickListener;
    public OderViewHolder(View itemView) {
        super(itemView);

        txtOderAdress = (TextView) itemView.findViewById(R.id.order_Adress);
        txtOrderId = (TextView) itemView.findViewById(R.id.order_id);
        txtOrderStatus = (TextView) itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView) itemView.findViewById(R.id.order_Phone);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickUI itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);



    }
}
