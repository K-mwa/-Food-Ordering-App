package com.younessharaki.apricot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class Apricot extends AppCompatActivity {
    Button btnSignup,btnSignin;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apricot);

        btnSignin=(Button)findViewById(R.id.BtnSignin);
        btnSignup=(Button)findViewById(R.id.BtnSignup);

        txtSlogan=(TextView)findViewById(R.id.txtSlogan);
        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        //specifies the typeface and intrinsic style of a font.
        txtSlogan.setTypeface(face);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup =new Intent(Apricot.this,SignUp.class);
                startActivity(signup);



            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin =new Intent(Apricot.this,SignIn.class);
                startActivity(signin);
            }
        });

    }
}
