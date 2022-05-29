package com.nhom4web.model;

import com.google.gson.annotations.Expose;

public class DongDonHang {
    @Expose
    private int ma;
    @Expose
    private Sach sach;
    @Expose
    private int soLuong;
    @Expose
    private int donGia;

    public DongDonHang() {}

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
