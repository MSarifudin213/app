package com.android.chat_apps;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    ArrayList<ShareModel> shareItem;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //inisialisasi
        ImageView share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            share = itemView.findViewById(R.id.share_share);
        }
    }

    AdapterRecyclerView(ArrayList<ShareModel> data){
        this.shareItem = data ;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gird_share , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.ViewHolder holder, int position) {

        ImageView image_share = holder.share;

        image_share.setImageResource(shareItem.get(position).getShare());
    }

    @Override
    public int getItemCount() {
        return shareItem.size();
    }


}
