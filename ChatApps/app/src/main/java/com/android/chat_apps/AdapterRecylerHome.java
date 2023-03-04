package com.android.chat_apps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecylerHome extends RecyclerView.Adapter<AdapterRecylerHome.ViewHolder>{

    ArrayList<HomeModel> homeItem;

    public class ViewHolder extends RecyclerView.ViewHolder {
        //inisialisasi
        ImageView imhome;
        TextView txtnama, txtketerangan ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imhome = itemView.findViewById(R.id.ic_home);
            txtnama = itemView.findViewById(R.id.produk_home);
            txtketerangan = itemView.findViewById(R.id.keterangan_hm);

        }
    }
    AdapterRecylerHome(ArrayList<HomeModel> data){
        this.homeItem = data ;
    }

    @NonNull
    @Override
    public AdapterRecylerHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_home, parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView txt_nama = holder.txtnama;
        TextView txt_keterangan = holder.txtketerangan;
        ImageView im_home = holder.imhome;

        txt_nama.setText(homeItem.get(position).getNamap());
        txt_keterangan.setText(homeItem.get(position).getKeteranganp());
        im_home.setImageResource(homeItem.get(position).getGambrp());
    }


    @Override
    public int getItemCount() {
        return homeItem.size();
    }

}
