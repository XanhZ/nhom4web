package com.nhom4web.dao.impl;

import com.nhom4web.dao.ISachDAO;
import com.nhom4web.model.Sach;

import javax.servlet.http.Part;
import java.sql.*;
import java.util.*;

public class SachDAO extends AbstractDAO<Sach> implements ISachDAO {
    private static final PhanLoaiSachDAO PHAN_LOAI_SACH_DAO = new PhanLoaiSachDAO();
    private static final HinhAnhSachDAO HINH_ANH_SACH_DAO = new HinhAnhSachDAO();

    public SachDAO() {
        super("sach");
    }

    @Override
    protected Sach sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) {
                Sach sach = new Sach();
                sach.setMa(rs.getInt(1));
                sach.setTen(rs.getString(2));
                sach.setGiaTien(rs.getInt(3));
                sach.setSoLuongTrongKho(rs.getInt(4));
                sach.setThoiGianTao(rs.getTimestamp(5));
                sach.setThoiGianCapNhat(rs.getTimestamp(6));
                return sach;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(Sach sach) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (sach.getMa() != -1) duLieu.put("ma", sach.getMa());
        if (sach.getTen() != null) duLieu.put("tenSach", sach.getTen());
        if (sach.getGiaTien() != -1) duLieu.put("giaTien", sach.getGiaTien());
        if (sach.getSoLuongTrongKho() != -1) duLieu.put("soLuongTrongKho", sach.getSoLuongTrongKho());
        if (sach.getThoiGianTao() != null) duLieu.put("thoiGianTao", sach.getThoiGianTao());
        if (sach.getThoiGianCapNhat() != null) duLieu.put("thoiGianCapNhat", sach.getThoiGianCapNhat());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(Sach sach, ResultSet rs) {
        try {
            if (rs.next()) sach.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sach> layTatCa() {
        List<Sach> sachs = super.layTatCa();
        for (Sach sach : sachs) {
            if (!HINH_ANH_SACH_DAO.timTatCa(sach)) return null;
        }
        return sachs;
    }

    @Override
    public Sach tim(int ma) {
        Sach sach = super.tim(ma);
        if (sach == null) return null;
        if (!HINH_ANH_SACH_DAO.timTatCa(sach)) return null;
        if (!PHAN_LOAI_SACH_DAO.timDanhMucSach(sach)) return null;
        return sach;
    }

    @Override
    public boolean them(Sach sach, List<Part> hinhAnhs, List<Integer> maDanhMucs) {
        return super.them(sach) &&
                PHAN_LOAI_SACH_DAO.them(sach.getMa(), maDanhMucs) &&
                HINH_ANH_SACH_DAO.them(sach.getMa(), hinhAnhs);
    }

    @Override
    public boolean xoa(int ma) {
        if (!HINH_ANH_SACH_DAO.xoaTrenCloud(ma)) return false;
        return super.xoa(ma);
    }

    @Override
    protected List<Sach> sangThucThes(ResultSet rs) {
        List<Sach> sachs = new ArrayList<>();
        try {
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setMa(rs.getInt(1));
                sach.setTen(rs.getString(2));
                sach.setGiaTien(rs.getInt(3));
                sach.setSoLuongTrongKho(rs.getInt(4));
                sach.setThoiGianTao(rs.getTimestamp(5));
                sach.setThoiGianCapNhat(rs.getTimestamp(6));
                sachs.add(sach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sachs;
    }
}
