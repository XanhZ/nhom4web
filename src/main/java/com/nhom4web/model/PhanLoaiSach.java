package com.nhom4web.model;

public class PhanLoaiSach extends AbstractModel {
    private Sach sach;
    private DanhMuc danhMuc;

    public PhanLoaiSach() {

    }

    public PhanLoaiSach(int ma, Sach sach, DanhMuc danhMuc) {
        super(ma);
        this.sach = sach;
        this.danhMuc = danhMuc;
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
