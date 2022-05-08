package com.nhom4web.dao.impl;

import com.nhom4web.dao.IHinhAnhSachDAO;
import com.nhom4web.model.HinhAnhSach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class HinhAnhSachDAO extends AbstractDAO<HinhAnhSach> implements IHinhAnhSachDAO {
    public HinhAnhSachDAO() {
        this.setTenBang("hinhAnhSach");
    }

    @Override
    protected HinhAnhSach sangThucThe(ResultSet rs) {
        try {
            int maSach = rs.getInt(1);
            String duongDan = rs.getString(2);
            return new HinhAnhSach(maSach, duongDan);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean them(int maSach, List<String> duongDans) {
        Connection ketNoi = this.getKetNoi();
        if (ketNoi != null) {
            try {
                String[] temps = new String[duongDans.size()];
                Arrays.fill(temps, "(?, ?)");
                String sql = String.format(
                        "INSERT INTO %s VALUES %s",
                        this.tenBang,
                        String.join(", ", temps)
                );
                ketNoi.setAutoCommit(false);
                PreparedStatement stmt = ketNoi.prepareStatement(sql);
                int i = 0;
                for (String duongDan : duongDans) {
                    this.setThamSoTai(stmt, ++i, maSach);
                    this.setThamSoTai(stmt, ++i, duongDan);
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
