package com.android.chat_apps;

public class Global {
    String API_URL = "http://192.168.43.28/";
    String USER_ID = "";

    public String getAPI_URL(){
        return this.API_URL;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
