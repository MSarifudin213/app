package com.android.chat_apps;

public class HomeModel {

    String namap, keteranganp;
    int gambrp;

    public HomeModel(String namap, String keteranganp, int gambrp) {
        this.namap = namap;
        this.keteranganp = keteranganp;
        this.gambrp = gambrp;
    }

    public String getNamap() {
        return namap;
    }

    public String getKeteranganp() {
        return keteranganp;
    }

    public int getGambrp() {
        return gambrp;
    }
}
