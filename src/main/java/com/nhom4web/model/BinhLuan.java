package com.nhom4web.model;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;

public class BinhLuan {
    @Expose private int ma;
            private int maSach;
            private int maNguoiDung;
    @Expose private String noiDung;
    @Expose private Timestamp thoiGianTao, thoiGianCapNhat;
    @Expose private NguoiDung nguoiDung;

    public BinhLuan() {
    }

    public BinhLuan(int maSach, String noiDung, Timestamp thoiGianTao, Timestamp thoiGianCapNhat, NguoiDung nguoiDung) {
        this.maSach = maSach;
        this.maNguoiDung = nguoiDung.getMa();
        this.noiDung = noiDung;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhat = thoiGianCapNhat;
        this.nguoiDung = nguoiDung;
    }

    public int getMa() {
        return ma;
    }

    public int getMaSach() {
        return maSach;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public Timestamp getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public void setMaSach(Integer maSach) {
        this.maSach = maSach;
    }

    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setThoiGianTao(Timestamp thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public void setThoiGianCapNhat(Timestamp thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
