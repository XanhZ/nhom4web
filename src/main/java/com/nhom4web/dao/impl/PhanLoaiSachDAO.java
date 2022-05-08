package com.nhom4web.dao.impl;

import com.nhom4web.dao.IPhanLoaiSach;
import com.nhom4web.model.PhanLoaiSach;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class PhanLoaiSachDAO extends AbstractDAO<PhanLoaiSach> implements IPhanLoaiSach {
    public PhanLoaiSachDAO() {
        this.setTenBang("phanLoaiSach");
    }

    @Override
    protected PhanLoaiSach sangThucThe(ResultSet rs) {
        try {
            int maSach = rs.getInt(1);
            int maDanhMuc = rs.getInt(2);
            return new PhanLoaiSach(maSach, maDanhMuc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean them(int maSach, List<Integer> maDanhMucs) {
        Connection ketNoi = this.getKetNoi();
        if (ketNoi != null) {
            try {
                String[] temps = new String[maDanhMucs.size()];
                Arrays.fill(temps, "(?, ?)");
                String sql = String.format(
                        "INSERT INTO %s VALUES %s",
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
                this.dongTruyVan(ketNoi, stmt, null);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
