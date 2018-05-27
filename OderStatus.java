package com.younessharaki.apricot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.younessharaki.apricot.Model.Request;
import com.younessharaki.apricot.Viewholder.OderViewHolder;
import com.younessharaki.apricot.common.common;

public class OderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OderViewHolder> adapter;


    FirebaseDatabase database;
    DatabaseReference requests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_status);

        //Firebase
        database =FirebaseDatabase.getInstance();
        requests =database.getReference("Requests");

        recyclerView =(RecyclerView)findViewById(R.id.ListOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders(common.currentUser.getPhone());

    }

    private void loadOrders(String phone) {

        adapter = new FirebaseRecyclerAdapter<Request, OderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OderViewHolder.class,
                requests.orderByChild("phone")
                             .equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OderViewHolder viewHolder, Request model, int position) {
                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(convertCodeToStatus(model.getStatus()));
                viewHolder.txtOderAdress.setText(model.getAdress());
                viewHolder.txtOrderPhone.setText(model.getPhone());


            }
        };
        recyclerView.setAdapter(adapter);
    }

    private String convertCodeToStatus(String status) {
      if(status.equals("0"))
          return "Placed";
      else if(status.equals("1"))
            return "Processing";
      else
          return "Shipped";
    }
}
