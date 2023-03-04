package com.android.chat_apps;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    String HttpURL = "http://192.168.43.28/api/createaccount.php";



    Intent a;

    private Button buttonTwoButton;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etName;
    private TextView tvHaveAccount;

    String TempFullname, TempPassword, TempEmail;

//    private Global globalVar = new Global();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonTwoButton = this.findViewById(R.id.button_daftar);
        etEmail = this.findViewById(R.id.et_email);
        etPassword = this.findViewById(R.id.et_password);
        etName = this.findViewById(R.id.et_name);
        tvHaveAccount = this.findViewById(R.id.login_text);

        buttonTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetData();
                InsertData(TempFullname, TempPassword, TempEmail);
                a = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(a);
                finish();
            }

        });

    }



    private void GetData() {

        TempFullname = etEmail.getText().toString();
        TempEmail = etName.getText().toString();
        TempPassword = etPassword.getText().toString();
    }

    private void InsertData(final String name,final String password,final String email) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>{


            @Override
            protected String doInBackground(String... strings) {

            String FullnameHolder = name ;
            String EmailHolder = email ;
            String PasswordHolder = password ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", FullnameHolder));
                nameValuePairs.add(new BasicNameValuePair("password", PasswordHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(HttpURL);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                } catch (UnsupportedEncodingException e) {

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Registered successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name, password, email);


    }
}

//
