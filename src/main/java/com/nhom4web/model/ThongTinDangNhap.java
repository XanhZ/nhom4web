package com.nhom4web.model;

import java.sql.Timestamp;

public class ThongTinDangNhap {
    private Integer ma;
    private Integer maNguoiDung;
    private String tenDangNhap;
    private String matKhau;
    private String token;
    private Timestamp thoiGianTao;

    public void setThoiGianTao(Timestamp thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public void setThoiGianCapNhat(Timestamp thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public Timestamp getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    private Timestamp thoiGianCapNhat;

    public ThongTinDangNhap(Integer ma, Integer maNguoiDung, String tenDangNhap, String matKhau, String token) {
        this.ma = ma;
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.token = token;
    }

    public Integer getMa() {
        return ma;
    }

    public Integer getMaNguoiDung() {
        return maNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getToken() {
        return token;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
