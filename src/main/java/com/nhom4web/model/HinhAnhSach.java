package com.nhom4web.model;

public class HinhAnhSach extends AbstractModel {
    private int maSach;
    private String duongDan;

    public HinhAnhSach(int maSach, String duongDan) {
        this.maSach = maSach;
        this.duongDan = duongDan;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }
}
