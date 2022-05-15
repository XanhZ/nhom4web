package com.nhom4web.dao.impl;

import com.nhom4web.dao.IThongTinDangNhapDAO;
import com.nhom4web.model.ThongTinDangNhap;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ThongTinDangNhapDAO extends AbstractDAO<ThongTinDangNhap> implements IThongTinDangNhapDAO {
    public ThongTinDangNhapDAO() {
        super("thongTinDangNhap");
    }

    @Override
    protected List<ThongTinDangNhap> sangThucThes(ResultSet rs) {
        return null;
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

    @Override
    protected LinkedHashMap<String, Object> sangMap(ThongTinDangNhap thongTinDangNhap) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (thongTinDangNhap.getMa() != -1) duLieu.put("ma", thongTinDangNhap.getMa());
        if (thongTinDangNhap.getMaNguoiDung() != null) duLieu.put("sdt", thongTinDangNhap.getMaNguoiDung());
        if (thongTinDangNhap.getTenDangNhap() != null) duLieu.put("email", thongTinDangNhap.getTenDangNhap());
        if (thongTinDangNhap.getMatKhau() != null) duLieu.put("ten", thongTinDangNhap.getMatKhau());
        if (thongTinDangNhap.getToken() != null) duLieu.put("loaiNguoiDung", thongTinDangNhap.getToken());
        if (thongTinDangNhap.getThoiGianTao() != null) duLieu.put("thoiGianTao", thongTinDangNhap.getThoiGianTao());
        if (thongTinDangNhap.getThoiGianCapNhat() != null) duLieu.put("thoiGianCapNhat", thongTinDangNhap.getThoiGianCapNhat());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(ThongTinDangNhap thongTinDangNhap, ResultSet rs) {
        try {
            if (rs.next()) thongTinDangNhap.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ThongTinDangNhap layThongTinDangNhap(int maNguoiDung) {
        return null;
    }

    @Override
    public ThongTinDangNhap layThongTinDangNhap(LinkedHashMap<String, Object> duLieu) {
        ThongTinDangNhap thongTinDangNhap = null;

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

                PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    thongTinDangNhap = sangThucThe(rs);
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return thongTinDangNhap;
    }

    @Override
    public ThongTinDangNhap timTheoTaiKhoanMatKhau(LinkedHashMap<String, Object> duLieu) {
        return layThongTinDangNhap(duLieu);
    }
}
