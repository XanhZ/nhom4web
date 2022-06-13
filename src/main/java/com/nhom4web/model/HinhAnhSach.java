package com.nhom4web.model;

import com.google.gson.annotations.Expose;

public class HinhAnhSach {
    @Expose private int ma;
            private int maSach;
    @Expose private String duongDan;
    @Expose private String publicId;

    public HinhAnhSach() {}

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

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }
}
