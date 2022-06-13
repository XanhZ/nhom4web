package com.nhom4web.model;

import com.google.gson.annotations.Expose;

public class DongDonHang {
    @Expose private int ma;
            private int maDonHang;
            private int maSach;
    @Expose private int soLuong;
    @Expose private int donGia;
    @Expose private Sach sach;

    public DongDonHang() {}

    public DongDonHang(int soLuong, Sach sach) {
        this.maSach = sach.getMa();
        this.soLuong = soLuong;
        this.donGia = sach.getGiaTien();
        this.sach = sach;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
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

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DongDonHang)) return false;
        return this.getMaSach() == ((DongDonHang) o).getMaSach();
    }
}
