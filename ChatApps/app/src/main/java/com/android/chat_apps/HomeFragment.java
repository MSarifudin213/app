package com.android.chat_apps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {
    View v;
    ArrayList<HomeModel> data;
    AdapterRecylerHome adapterRecylerHome;
    RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView =(RecyclerView) v.findViewById(R.id.recyler_home);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<HomeModel>();
        for (int i = 0; i < HomeItem.namap.length ; i++){
            data.add(new HomeModel(
                    HomeItem.namap[i],
                    HomeItem.keteranganp[i],
                    HomeItem.gambarp[i]
            ));
        }
        adapterRecylerHome = new AdapterRecylerHome(data);
        recyclerView.setAdapter(adapterRecylerHome);


        return v;
    }

}