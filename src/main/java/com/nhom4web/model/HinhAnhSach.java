package com.nhom4web.model;

import com.google.gson.annotations.Expose;

public class HinhAnhSach {
    private int ma;
    @Expose
    private String duongDan;
    @Expose
    private String publicId;

    public HinhAnhSach() {

    }

    public HinhAnhSach(int ma, String duongDan, String publicId) {
        this.ma = ma;
        this.duongDan = duongDan;
        this.publicId = publicId;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
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
