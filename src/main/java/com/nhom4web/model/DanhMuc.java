package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.util.List;

public class DanhMuc {
    @Expose
    private int ma;
    @Expose
    private String ten;
    @Expose
    private Timestamp thoiGianTao, thoiGianCapNhat;

    public DanhMuc() {}

    public DanhMuc(String ten, Timestamp thoiGianTao, Timestamp thoiGianCapNhat) {
        this.ten = ten;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Timestamp thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public Timestamp getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(Timestamp thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }
}
