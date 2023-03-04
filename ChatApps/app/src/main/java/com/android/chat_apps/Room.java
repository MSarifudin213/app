package com.android.chat_apps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Base64;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Room extends AppCompatActivity {

    private Global globalVar = new Global();
    public String IMAGES_BASE64 = "false";
    final int[] count_T = {0};

        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
        public void onTick(long millisUntilFinished) {
            Bundle extras = getIntent().getExtras();
            String URL = globalVar.API_URL + "api/check/" + extras.getString("USER_ID") + "/" + String.valueOf(count_T[0]);
            // For every second, do something.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if(response.getBoolean("new_message")){
                                    System.out.println(response.getInt("count"));
                                    count_T[0] = response.getInt("count");
                                    loadChat();
                                }else{
                                    System.out.println("no New");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(jsonObjectRequest);
            //
        }

        public void onFinish() {
            countDownTimer.start(); // restart again.
        }
    }.start();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Button kirim = (Button) this.findViewById(R.id.kirim_btn);
        kirim.setOnClickListener((view) -> {
            this.sendMessage();
        });

        ImageButton search = (ImageButton)findViewById(R.id.searchImageButton);
        search.setOnClickListener((view) -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);

            // pass the constant to compare it
            // with the returned requestCode
            startActivityForResult(Intent.createChooser(i, "Select Picture"), 200);
        });
    }

    public void loadChat(){
        Bundle extras = getIntent().getExtras();
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                view.scrollTo(0, view.getContentHeight());
            }
        });
        myWebView.loadUrl(globalVar.getAPI_URL() + "api/room/" + extras.getString("USER_ID"));
    }

    public void sendMessage() {
        Bundle extras = getIntent().getExtras();
        JSONObject object = new JSONObject();
        EditText etMessage = (EditText) this.findViewById(R.id.message_et);
        ImageButton search = (ImageButton)findViewById(R.id.searchImageButton);
        try {
            //input your API parameters
            object.put("sender_id", extras.getString("USER_ID"));
            object.put("message",etMessage.getText());
            object.put("valid",true);
            object.put("image_base64",IMAGES_BASE64);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, globalVar.API_URL + "api/send_message", object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        etMessage.setText("");
                        IMAGES_BASE64 = "false";
                        ((ImageButton) search).setImageResource(R.drawable.ic_baseline_cloud_upload_24);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error", "Error: " + error.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageButton search = (ImageButton)findViewById(R.id.searchImageButton);
        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == 200) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    IMAGES_BASE64 = getEncoded64ImageStringFromBitmap(bitmap);
                    System.out.println(IMAGES_BASE64);
                    Picasso.with(Room.this).load(selectedImageUri).fit()
                            .centerInside().into(search);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}