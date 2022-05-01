package com.nhom4web.model;

public class PhanLoaiSach extends AbstractModel {
    private int maSach;
    private int maDanhMuc;

    public PhanLoaiSach(int maSach, int maDanhMuc) {
        this.maSach = maSach;
        this.maDanhMuc = maDanhMuc;
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
}
