package com.nhom4web.model;

import java.sql.Timestamp;

public class NguoiDung {
    private Integer ma;
    private String ten;
    private String sdt;
    private String email;
    private Integer loaiNguoiDung;
    private Integer gioiTinh;
    private String diaChi;

    private Timestamp thoiGianTao;
    private Timestamp thoiGianCapNhat;

    public NguoiDung(Integer ma, String ten, String sdt, String email, Integer loaiNguoiDung) {
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.loaiNguoiDung = loaiNguoiDung;
//        this.gioiTinh = gioiTinh;
//        this.diaChi = diaChi;
    }

    public Integer getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public Integer getLoaiNguoiDung() {
        return loaiNguoiDung;
    }

    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public Timestamp getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public NguoiDung() {

    }
}
