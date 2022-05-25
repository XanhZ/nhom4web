package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.util.List;

public class DonHang {
    @Expose
    private String diaChi;
    @Expose
    private List<DongDonHang> dongDonHangs;
    @Expose
    private int ma;
    private NguoiDung nguoiDung;
    @Expose
    private String trangThai;
    @Expose
    private int tongTien;
    @Expose
    private Timestamp thoiGianTao;
    @Expose
    private Timestamp thoiGianCapNhat;

    public DonHang() {
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Timestamp thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Timestamp getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(Timestamp thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public List<DongDonHang> getDongDonHangs() {
        return dongDonHangs;
    }

    public void setDongDonHangs(List<DongDonHang> dongDonHangs) {
        this.dongDonHangs = dongDonHangs;
    }
}
