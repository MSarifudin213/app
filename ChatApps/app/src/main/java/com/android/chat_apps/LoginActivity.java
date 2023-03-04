package com.android.chat_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText txtnama, txtemail;

    Intent a;
    private TextView register;

    private Button btnlogin;

    String url, success;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext());

        txtnama = this.findViewById(R.id.et_name);
        txtemail = this.findViewById(R.id.et_email);
        btnlogin = this.findViewById(R.id.button_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://192.168.43.28/api/login.php?" + "email=" + txtnama.getText().toString() + "&password=" + txtemail.getText().toString();
                if (txtnama.getText().toString().trim().length() > 0 && txtemail.getText().toString().trim().length() > 0) {
                    new Masuk(getApplicationContext()).execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Username or password is empty", Toast.LENGTH_LONG).show();
                }
            }
        });


        register = this.findViewById(R.id.register_text);
        register.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regis);
            }
        }));


    }

    private class Masuk extends AsyncTask<String, String, String> {

        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        ProgressDialog pDialog;
        android.content.Context context;

        public Masuk(android.content.Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                success = json.getString("success");
                Log.e("error", "nilai sukses=" + success);

                if (success.equals("1")) {
                    JSONArray hasil = json.getJSONArray("login");
                    for (int i = 0; i < hasil.length(); i++) {
                        JSONObject c = hasil.getJSONObject(i);
                        String email = c.getString("email").trim();
                        String password = c.getString("password").trim();
                        String name = c.getString("name").trim();
                        String id = c.getString("id").trim();

                        sessionManager.createLoginSession( email,password ,name, id);
                        Log.e("ok", " ambil = data");
                    }
                } else {
                    Log.e("error", "tidak bisa ambil data 0");
                }
            } catch (Exception e) {
                // TODO: handle exception

                e.printStackTrace();
                Log.e("erro", "tidak bisa ambil data 1");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            if (success!=null && success.equals("1")) {
                String nama = txtnama.getText().toString();
                String email = txtemail.getText().toString();
                a = new Intent(LoginActivity.this, MainActivity.class);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                a.putExtra("email", nama);
                a.putExtra("password", email);
                startActivity(a);
                finish();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Username or password is wrong, please try again!", Toast.LENGTH_LONG).show();
            }
        }


    }
}