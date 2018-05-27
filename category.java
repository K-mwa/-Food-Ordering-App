package com.younessharaki.apricot;

import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.younessharaki.apricot.Interface.ItemClickUI;
import com.younessharaki.apricot.Model.Type;
import com.younessharaki.apricot.Viewholder.MenuViewholder;
import com.younessharaki.apricot.common.common;

import java.util.Locale;

public class category extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
FirebaseDatabase database;
DatabaseReference category;
TextView txtFullName;

RecyclerView recycler_menu;
RecyclerView.LayoutManager layoutManager;
FirebaseRecyclerAdapter<Type,MenuViewholder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);


        //init Firebase
   database=FirebaseDatabase.getInstance();
   category=database.getReference("Category");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//cart button
             Intent cartIntent =new Intent(category.this,Cart.class);
             startActivity(cartIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set Name for User
        View headerView =navigationView.getHeaderView(0);
        txtFullName=(TextView) headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(common.currentUser.getName());
        //load menu
recycler_menu=(RecyclerView)findViewById(R.id.recycler_menu);
recycler_menu.setHasFixedSize(true);
layoutManager =new LinearLayoutManager(this);//add scroling
recycler_menu.setLayoutManager(layoutManager);
loadMenu();
    }

    private void loadMenu() {
        adapter=new FirebaseRecyclerAdapter<Type, MenuViewholder>(Type.class,R.layout.menu_item,MenuViewholder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewholder viewholder, Type model, int position) {
                viewholder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewholder.imageView);
                final Type clickItem = model;
                viewholder.setItemClickListener(new ItemClickUI() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       //GetCategory Id and send to new activity
                        Intent FoodList =new Intent(category.this,foodlist.class);
                        FoodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(FoodList);
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {

        } else if (id == R.id.nav_Cart) {
            Intent cartIntent =new Intent(category.this,Cart.class);
            startActivity(cartIntent);

        } else if (id == R.id.nav_orders){
            Intent orderIntent =new Intent(category.this,OderStatus.class);
            startActivity(orderIntent);
        } else if (id == R.id.nav_exit) {

            Intent signIn =new Intent(category.this,SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signIn);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
