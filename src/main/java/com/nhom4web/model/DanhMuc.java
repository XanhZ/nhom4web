package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.util.List;

public class DanhMuc extends AbstractModel {
    @Expose private String ten;
    @Expose private Timestamp thoiGianTao, thoiGianCapNhat;
    private List<PhanLoaiSach> phanLoaiSachs;

    public DanhMuc() {
    }

    public DanhMuc(int ma, String ten, Timestamp thoiGianTao, Timestamp thoiGianCapNhat) {
        super(ma);
        this.ten = ten;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<PhanLoaiSach> getPhanLoaiSachs() {
        return phanLoaiSachs;
    }

    public void setPhanLoaiSachs(List<PhanLoaiSach> phanLoaiSachs) {
        this.phanLoaiSachs = phanLoaiSachs;
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
