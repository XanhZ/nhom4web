package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.util.List;

public class DonHang {
    @Expose private int ma;
    @Expose private String diaChi;
    @Expose private String trangThai;
            private int tongTien;
            private int maNguoiDung;
    @Expose private Timestamp thoiGianTao;
    @Expose private Timestamp thoiGianCapNhat;
    @Expose private NguoiDung nguoiDung;
    @Expose private List<DongDonHang> dongDonHangs;

    public DonHang() {
    }

    public DonHang(String diaChi, List<DongDonHang> dongDonHangs, NguoiDung nguoiDung, Timestamp thoiGianTao, Timestamp thoiGianCapNhat) {
        this.maNguoiDung = nguoiDung.getMa();
        this.diaChi = diaChi;
        this.dongDonHangs = dongDonHangs;
        this.nguoiDung = nguoiDung;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
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

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
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
