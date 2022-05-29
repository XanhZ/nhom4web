package com.nhom4web.model;

import com.google.gson.annotations.Expose;

public class PhanLoaiSach {
    @Expose
    private int ma;
    @Expose
    private Sach sach;
    @Expose
    private DanhMuc danhMuc;

    public PhanLoaiSach() {}

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
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
