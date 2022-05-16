package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.util.List;

public class Sach {
    @Expose
    List<HinhAnhSach> hinhAnhSachs;
    @Expose
    List<DanhMuc> danhMucs;
    private int ma;
    @Expose
    private String ten;
    @Expose
    private int giaTien;
    @Expose
    private int soLuongTrongKho;
    @Expose
    private Timestamp thoiGianTao, thoiGianCapNhat;

    public Sach() {
    }

    public Sach(int ma, List<HinhAnhSach> hinhAnhSachs, String ten, int giaTien, int soLuongTrongKho, Timestamp thoiGianTao, Timestamp thoiGianCapNhat) {
        this.ma = ma;
        this.hinhAnhSachs = hinhAnhSachs;
        this.ten = ten;
        this.giaTien = giaTien;
        this.soLuongTrongKho = soLuongTrongKho;
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

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public List<DanhMuc> getDanhMucs() {
        return danhMucs;
    }

    public void setDanhMucs(List<DanhMuc> danhMucs) {
        this.danhMucs = danhMucs;
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
}
