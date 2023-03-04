package com.android.chat_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRecyclerView adapterRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ShareModel> data;

    Button btnback;

    TextView btnfake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        recyclerView = findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);

        layoutManager =new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<ShareModel>();
        for (int i = 0; i < ShareItem.share.length ; i++){
            data.add(new ShareModel(
                    ShareItem.share[i]
            ));
        }
        adapterRecyclerView = new AdapterRecyclerView(data);
        recyclerView.setAdapter(adapterRecyclerView);

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent home = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(home);

            }
        });

        btnfake = this.findViewById(R.id.txtlogout);
        btnfake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(ProfileActivity.this, PembayaranActivity.class);
                startActivity(logout);
            }
        });
    }

}