package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.util.List;

public class Sach {
    @Expose private int ma;
    @Expose private String ten;
    @Expose private int giaTien;
    @Expose private int soLuongTrongKho;
    @Expose private Timestamp thoiGianTao, thoiGianCapNhat;
    @Expose private List<HinhAnhSach> hinhAnhSachs;
            private List<PhanLoaiSach> phanLoaiSachs;

    public Sach() {
    }

    public Sach(String ten, int giaTien, int soLuongTrongKho, Timestamp thoiGianTao, Timestamp thoiGianCapNhat, List<HinhAnhSach> hinhAnhSachs, List<PhanLoaiSach> phanLoaiSachs) {
        this.ten = ten;
        this.giaTien = giaTien;
        this.soLuongTrongKho = soLuongTrongKho;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhat = thoiGianCapNhat;
        this.hinhAnhSachs = hinhAnhSachs;
        this.phanLoaiSachs = phanLoaiSachs;
    }

    public int getMa() {
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

    public List<HinhAnhSach> getHinhAnhSachs() {
        return hinhAnhSachs;
    }

    public void setHinhAnhSachs(List<HinhAnhSach> hinhAnhSachs) {
        this.hinhAnhSachs = hinhAnhSachs;
    }

    public List<PhanLoaiSach> getPhanLoaiSachs() {
        return phanLoaiSachs;
    }

    public void setPhanLoaiSachs(List<PhanLoaiSach> phanLoaiSachs) {
        this.phanLoaiSachs = phanLoaiSachs;
    }
}
