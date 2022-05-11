package com.nhom4web.dao.impl;

import com.nhom4web.dao.IThongTinDangNhapDAO;
import com.nhom4web.model.ThongTinDangNhap;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ThongTinDangNhapDAO extends AbstractDAO<ThongTinDangNhap> implements IThongTinDangNhapDAO {
    public ThongTinDangNhapDAO() {
        this.setTenBang("thongTinDangNhap");
    }

    @Override
    protected ThongTinDangNhap sangThucThe(ResultSet rs) {
        try {
            int ma = rs.getInt(1);
            int maNguoiDung = rs.getInt(2);
            String tenDangNhap = rs.getString(3);
            String matKhau = rs.getString(4);
            String token = rs.getString(5);
            Timestamp thoiGianTao = rs.getTimestamp(6);
            Timestamp thoiGianCapNhat = rs.getTimestamp(7);

            return new ThongTinDangNhap(ma, maNguoiDung, tenDangNhap, matKhau, token);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public ThongTinDangNhap layThongTinDangNhap(int maNguoiDung) {
        return null;
    }

    public ThongTinDangNhap layThongTinDangNhap (LinkedHashMap<String, Object> duLieu) {
        ThongTinDangNhap thongTinDangNhap = null;

        Connection ketNoi = this.getKetNoi();
        if (ketNoi != null) {
            try {
                ArrayList<String> filters = new ArrayList<>();
                for (Map.Entry<String, Object> capDuLieu : duLieu.entrySet()) {
                    filters.add(capDuLieu.getKey() + " = " + "'" + capDuLieu.getValue() + "'");
                }

                String sql = String.format(
                        "SELECT * FROM %s WHERE %s",
                        this.tenBang,
                        String.join(" AND ", filters)
                );

                PreparedStatement stmt = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    thongTinDangNhap = sangThucThe(rs);
                }
                this.dongTruyVan(ketNoi, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return thongTinDangNhap;
    }
}
