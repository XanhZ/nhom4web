package com.nhom4web.model;

import java.sql.Timestamp;

public class Sach extends AbstractModel {
    private final int ma;
    private int maDanhMuc;
    private String tenSach;
    private int giaTien;
    private int soLuongTrongKho;
    private Timestamp thoiGianTao;
    private Timestamp thoiGianCapNhat;

    public Sach(int ma, int maDanhMuc, String tenSach, int giaTien, int soLuongTrongKho, Timestamp thoiGianTao, Timestamp thoiGianCapNhat) {
        this.ma = ma;
        this.maDanhMuc = maDanhMuc;
        this.tenSach = tenSach;
        this.giaTien = giaTien;
        this.soLuongTrongKho = soLuongTrongKho;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public int getMa() {
        return ma;
    }

    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuongTrongKho() {
        return soLuongTrongKho;
    }

    public void setSoLuongTrongKho(int soLuongTrongKho) {
        this.soLuongTrongKho = soLuongTrongKho;
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
