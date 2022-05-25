package com.nhom4web.dao.impl;

import com.nhom4web.dao.IDonHangDAO;
import com.nhom4web.model.DonHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DonHangDAO extends AbstractDAO<DonHang> implements IDonHangDAO {
    public DonHangDAO() {
        super("donHang");
    }

    @Override
    protected List<DonHang> sangThucThes(ResultSet rs) {
        List<DonHang> donHangs = new ArrayList<>();
        try {
            while (rs.next()) donHangs.add(this.rsSangThucThe(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donHangs;
    }

    @Override
    protected DonHang sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return this.rsSangThucThe(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(DonHang donHang) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (donHang.getMa() > 0) duLieu.put("ma", donHang.getMa());
        if (donHang.getDiaChi() != null) duLieu.put("diaChi", donHang.getDiaChi());
        if (donHang.getTrangThai() != null) duLieu.put("trangThai", donHang.getTrangThai());
        if (donHang.getThoiGianTao() != null) duLieu.put("thoiGianTao", donHang.getThoiGianTao());
        if (donHang.getThoiGianCapNhat() != null) duLieu.put("thoiGianCapNhat", donHang.getThoiGianCapNhat());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(DonHang donHang, ResultSet rs) {
        try {
            if (rs.next()) donHang.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
