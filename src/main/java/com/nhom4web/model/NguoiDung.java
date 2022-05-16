package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;

public class NguoiDung {
    private Integer ma;
    @Expose
    private String ten;
    @Expose
    private String sdt;
    @Expose
    private String email;
    private Integer loaiNguoiDung;
    private Timestamp thoiGianTao;
    private Timestamp thoiGianCapNhat;

    public NguoiDung(Integer ma, String ten, String sdt, String email, Integer loaiNguoiDung) {
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public NguoiDung() {

    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public void setLoaiNguoiDung(Integer loaiNguoiDung) {
        this.loaiNguoiDung = loaiNguoiDung;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public Timestamp getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }
}
