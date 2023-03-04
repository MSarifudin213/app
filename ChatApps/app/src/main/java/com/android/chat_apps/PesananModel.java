package com.android.chat_apps;

public class PesananModel {
    String nama, order, tanggal , harga, status;
    int icon;

    public PesananModel(String nama, String order, String tanggal, String harga, String status, int icon) {
        this.nama = nama;
        this.order = order;
        this.tanggal = tanggal;
        this.harga = harga;
        this.status = status;
        this.icon = icon;
    }

    public String getNama() {
        return nama;
    }

    public String getOrder() {
        return order;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getHarga() {
        return harga;
    }

    public String getStatus() {
        return status;
    }

    public int getIcon() {
        return icon;
    }


}
