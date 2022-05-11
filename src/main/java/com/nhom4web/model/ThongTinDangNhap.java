package com.nhom4web.model;

public class ThongTinDangNhap {
    private Integer ma;
    private Integer maNguoiDung;
    private String tenDangNhap;
    private String matKhau;
    private String token;

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
}
