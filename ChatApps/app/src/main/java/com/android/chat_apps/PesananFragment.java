package com.android.chat_apps;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PesananFragment extends Fragment {
    View v;

    HttpResponse httpResponse;

    JSONObject jsonObject = null;

    String StringHolder = "";

    private static final String TAG = PesananFragment.class.getSimpleName(); //getting the info
    Context context;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RecyclerView recyclerView;

    String getId;


    ArrayList<HashMap<String , String>> listdata;

    SessionManager sessionManager;
    String HttpURL = "http://192.168.43.28/api/discovery-read.php";


    public PesananFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pesanan, container, false);

        sessionManager = new SessionManager(getActivity().getApplicationContext());
        sessionManager.loginCheck();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.KEY_ID);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyler_pesanan);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        listdata = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.POST, HttpURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("kursus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        HashMap<String, String> itemt = new HashMap<String, String>();
                        itemt.put("donor_name", json.getString("donor_name"));
                        itemt.put("donor_email", json.getString("donor_email"));
                        itemt.put("donation_type", json.getString("donation_type"));
                        itemt.put("note", json.getString("note"));
                        itemt.put("status", json.getString("status"));
                        itemt.put("created_at", json.getString("created_at"));
                        itemt.put("amount", json.getString("amount"));
                        listdata.add(itemt);
                        AdapterRecyclerPesanan adapter = new AdapterRecyclerPesanan(PesananFragment.this, listdata);
                        recyclerView.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }})
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        return v;
    }
}