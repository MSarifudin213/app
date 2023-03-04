package com.android.chat_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;
    private Button btproile, btnotif, btpesan ;

    private TextView txtnama, txtemail ,txtpass ;
    private Global globalVar = new Global();
    final int[] count_T = {0};

//    CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
//        public void onTick(long millisUntilFinished) {
//            Bundle extras = getIntent().getExtras();
//            String URL = globalVar.API_URL + "api/check/" + extras.getString("USER_ID") + "/" + String.valueOf(count_T[0]);
//            // For every second, do something.
//
//
//
//
//        }
//
//        public void onFinish() {
//            countDownTimer.start(); // restart again.
//        }
//    }.start();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnama = findViewById(R.id.text_nama);
        txtemail = findViewById(R.id.text_email);
        txtpass = findViewById(R.id.text_password);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.loginCheck();

        Intent b = getIntent();
        String nama = b.getStringExtra("email");


        txtnama.setText(nama);



        btproile = findViewById(R.id.button_profile);
        btproile.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profile);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        btnotif = findViewById(R.id.button_notif);
        btnotif.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent notif = new Intent(MainActivity.this, NotifikasiActivity.class);
                startActivity(notif);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        btpesan = findViewById(R.id.button_pesan);
        init();




        ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setAdapter(new MainPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Pesanan");
//                        BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
//                        badgeDrawable.setBackgroundColor(
//                                ContextCompat.getColor( getApplicationContext(), R.color.colorAccent)
//                        );
//                        badgeDrawable.setVisible(true);
                        break;
                    case 1:
                        tab.setText("Home");
                        break;
                    case 2:
                        tab.setText("Extra");
//                        BadgeDrawable badgeDrawable1 = tab.getOrCreateBadge();
//                        badgeDrawable1.setBackgroundColor(
//                                ContextCompat.getColor( getApplicationContext(), R.color.colorAccent)
//                        );
//                        badgeDrawable1.setVisible(true);
//                        badgeDrawable1.setNumber(100);
//                        badgeDrawable1.setMaxCharacterCount(3);
                        break;

                }

            }
        }
        );
        tabLayoutMediator.attach();



//        btpesan.setOnClickListener(new View.OnClickListener(){
//
//            public void onClick(View view){
//                Intent pesan = new Intent(MainActivity.this, Room.class);
//                startActivity(pesan);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });




    }

    private void init() {
        btpesan = this.findViewById(R.id.button_pesan);
        btpesan.setOnClickListener((view) -> {
            this.onButtonPressed();
        });

    }

    public void onButtonPressed() {
        System.out.println("Login Pressed" + globalVar.getAPI_URL() );
        this.postData();
    }

    public void postData() {
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("email", txtemail.getText());
            object.put("password",txtpass.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, globalVar.API_URL + "api/login", object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(!response.getBoolean("login")){
                                Toast.makeText(getApplicationContext(), response.getString("msg"),
                                        Toast.LENGTH_LONG).show();
                            }

                            Intent intent = new Intent(MainActivity.this, Room.class);
                            intent.putExtra("USER_ID", response.getString("user_id"));
                            System.out.println("user_id : " + response.getString("user_id"));
                            startActivity(intent);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error", "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}

