package com.younessharaki.apricot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.ULocale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.younessharaki.apricot.Model.User;
import com.younessharaki.apricot.common.common;

public class SignIn extends AppCompatActivity {

    EditText edtPhone , edtPasssword;
    Button BtnSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtPasssword= (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone= (MaterialEditText)findViewById(R.id.edtPhone);
        BtnSignin= (Button)findViewById(R.id.BtnSignin);
        //Init of Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user =database.getReference("User");

        BtnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog =new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check if user doesn't exist
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()){
                        //Get User info
                        mDialog.dismiss();
                        User user =dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                        //receive from input and send to User class value
                            user.setPassword(edtPasssword.getText().toString());

                        user.setPhone(edtPhone.getText().toString());//set phone

                        if(user.getPassword().equals(edtPasssword.getText().toString()))
                        {

                                 Intent homeIntent = new Intent(SignIn.this, category.class);
                                 common.currentUser=user;
                                 startActivity(homeIntent);
                                 finish();

                        } else{
                            Toast.makeText(SignIn.this,"Sign in Failed (Wrong Password)! ",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,"Wrong Username! ",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
