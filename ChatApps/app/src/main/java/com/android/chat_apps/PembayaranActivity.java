package com.android.chat_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PembayaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        ImageView icon = findViewById(R.id.icon_pm);
        TextView creatat = findViewById(R.id.tgl_create);
        TextView namap = findViewById(R.id.nama_produk);
        TextView total = findViewById(R.id.harga);
        TextView totalm = findViewById(R.id.harga_total);

//        Intent intent = getIntent();
//
//        int iconpm = intent.getIntExtra("icon",0);
//        String tgl =intent.getStringExtra("tgl");
//        String namapr =intent.getStringExtra("namap");
//        String totalr =intent.getStringExtra("total");
//        String totalmr =intent.getStringExtra("totalm");
//
//        icon.setImageResource(iconpm);
//        creatat.setText(tgl);
//        namap.setText(namapr);
//        total.setText(totalr);
//        totalm.setText(totalmr);






    }
}