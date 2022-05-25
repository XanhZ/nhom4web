package com.nhom4web.dao.impl;

import com.nhom4web.dao.IDonHangDAO;
import com.nhom4web.dao.IDongDonHangDAO;
import com.nhom4web.model.DongDonHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DongDonHangDAO extends AbstractDAO<DongDonHang> implements IDongDonHangDAO {
    public DongDonHangDAO() {
        super("dongDonHang");
    }

    @Override
    protected List<DongDonHang> sangThucThes(ResultSet rs) {
        List<DongDonHang> dongDonHangs = new ArrayList<>();
        try {
            while (rs.next()) dongDonHangs.add(this.rsSangThucThe(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dongDonHangs;
    }

    @Override
    protected DongDonHang sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return this.rsSangThucThe(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(DongDonHang dongDonHang) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (dongDonHang.getMa() > 0) duLieu.put("ma", dongDonHang.getMa());
        if (dongDonHang.getDonGia() > 0) duLieu.put("donGia", dongDonHang.getDonGia());
        if (dongDonHang.getSoLuong() > 0) duLieu.put("soLuong", dongDonHang.getSoLuong());
        if (dongDonHang.getSach() != null) duLieu.put("maSach", dongDonHang.getSach().getMa());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(DongDonHang dongDonHang, ResultSet rs) {
        try {
            if (rs.next()) dongDonHang.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
