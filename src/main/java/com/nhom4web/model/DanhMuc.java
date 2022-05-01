package com.nhom4web.model;

import java.sql.Timestamp;

public class DanhMuc extends AbstractModel {
    private final int ma;
    private String tenDanhMuc;
    private Timestamp thoiGianTao, thoiGianCapNhat;

    public DanhMuc(int ma, String tenDanhMuc, Timestamp thoiGianTao, Timestamp thoiGianCapNhat) {
        this.ma = ma;
        this.tenDanhMuc = tenDanhMuc;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public int getMa() {
        return ma;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
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
