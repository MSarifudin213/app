package com.android.chat_apps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterRecyclerPesanan extends RecyclerView.Adapter<AdapterRecyclerPesanan.ViewHolder>{

    ArrayList<HashMap<String ,String >> listdata;
    private PesananFragment context;

//    private int lastSelectedPosition = -1;

    public AdapterRecyclerPesanan(PesananFragment context, ArrayList<HashMap<String , String >> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_pesanan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolder holder, final int position) {

        holder.tgl.setText(listdata.get(position).get("created_at"));
        holder.nama.setText(listdata.get(position).get("donor_name"));
        holder.harga.setText(listdata.get(position).get("amount"));
        holder.status.setText(listdata.get(position).get("status"));


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                Intent intent = new Intent(v.getContext(), PembayaranActivity.class);
//                intent.putExtra("created_at", listdata.get(position).get("created_at"));
//                intent.putExtra("donor_name", listdata.get(position).get("donor_name"));
//                intent.putExtra("amount", listdata.get(position).get("amount"));
//                intent.putExtra("status", listdata.get(position).get("status"));
//                v.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {

        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView  tgl, harga, nama, status;

        public ViewHolder(View v) {
            super(v);

            tgl = (TextView) v.findViewById(R.id.tgl_ps);
            nama = (TextView) v.findViewById(R.id.produk_ps);
            harga = (TextView) v.findViewById(R.id.harga_ps);
            status = (TextView) v.findViewById(R.id.status_pm);

        }
    }
}
