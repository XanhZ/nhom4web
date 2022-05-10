package com.nhom4web.dao.impl;

import com.nhom4web.dao.IPhanLoaiSach;
import com.nhom4web.model.PhanLoaiSach;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class PhanLoaiSachDAO extends AbstractDAO<PhanLoaiSach> implements IPhanLoaiSach {
    public PhanLoaiSachDAO() {
        super("phanLoaiSach");
    }

    @Override
    protected PhanLoaiSach sangThucThe(ResultSet rs) {
        try {
            PhanLoaiSach phanLoaiSach = new PhanLoaiSach();
            phanLoaiSach.setMa(rs.getInt(1));
            return phanLoaiSach;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(PhanLoaiSach phanLoaiSach) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (phanLoaiSach.getMa() != -1) duLieu.put("ma", phanLoaiSach.getMa());
        if (phanLoaiSach.getSach().getMa() != -1) duLieu.put("duongDan", phanLoaiSach.getSach().getMa());
        if (phanLoaiSach.getDanhMuc().getMa() != -1) duLieu.put("publicId", phanLoaiSach.getDanhMuc().getMa());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(PhanLoaiSach phanLoaiSach, ResultSet rs) {
        try {
            if (rs.next()) phanLoaiSach.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean them(int maSach, List<Integer> maDanhMucs) {
        try {
            String[] temps = new String[maDanhMucs.size()];
            Arrays.fill(temps, "(?, ?)");
            String sql = String.format(
                    "INSERT INTO %s (maSach, maDanhMuc) VALUES %s",
                    this.tenBang,
                    String.join(", ", temps)
            );
            ketNoi.setAutoCommit(false);
            PreparedStatement stmt = ketNoi.prepareStatement(sql);
            int i = 0;
            for (Integer maDanhMuc : maDanhMucs) {
                this.setThamSoTai(stmt, ++i, maSach);
                this.setThamSoTai(stmt, ++i, maDanhMuc);
            }
            stmt.executeUpdate();
            ketNoi.commit();
            this.dongTruyVan(stmt, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
