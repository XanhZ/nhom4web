package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class SachTrongGioHang {
    @Expose private int soLuong;
    @Expose private final Sach sach;

    public SachTrongGioHang(Sach sach, int soLuong) {
        this.sach = sach;
        this.soLuong = soLuong;
    }

    public Sach getSach() {
        return sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SachTrongGioHang)) return false;
        return this.getSach().getMa() == ((SachTrongGioHang) obj).getSach().getMa();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSach().getMa());
    }
}