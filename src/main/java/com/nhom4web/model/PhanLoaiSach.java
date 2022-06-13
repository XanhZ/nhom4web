package com.nhom4web.model;

import com.google.gson.annotations.Expose;

public class PhanLoaiSach {
    @Expose private int ma;
            private int maSach;
            private int maDanhMuc;
            private Sach sach;
    @Expose private DanhMuc danhMuc;

    public PhanLoaiSach() {}

    public PhanLoaiSach(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public PhanLoaiSach(int maSach, int maDanhMuc) {
        this.maSach = maSach;
        this.maDanhMuc = maDanhMuc;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }
}
