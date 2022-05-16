package com.nhom4web.model;

public class PhanLoaiSach {
    private int ma;
    private Sach sach;
    private DanhMuc danhMuc;

    public PhanLoaiSach() {

    }

    public PhanLoaiSach(int ma, Sach sach, DanhMuc danhMuc) {
        this.ma = ma;
        this.sach = sach;
        this.danhMuc = danhMuc;
    }

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
