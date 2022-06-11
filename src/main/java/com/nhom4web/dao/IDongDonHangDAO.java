package com.nhom4web.dao;

import com.nhom4web.model.DongDonHang;
import com.nhom4web.model.Sach;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.nhom4web.dao.impl.AbstractDAO.ketNoi;

public interface IDongDonHangDAO {
    boolean them(int maDonHang, List<DongDonHang> dongDonHangs, boolean luu);

    boolean them(int maDonHang, DongDonHang dongDonHang, boolean luu);

    List<DongDonHang> timTatCa(int maDonHang);

    static DongDonHang rsSangThucThe(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Set<String> truongs = new HashSet<>();
        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
            truongs.add(rsmd.getColumnName(i));
        }
        DongDonHang dongDonHang = new DongDonHang();
        if (truongs.contains("ma")) dongDonHang.setMa(rs.getInt("ma"));
        if (truongs.contains("donGia")) dongDonHang.setDonGia(rs.getInt("donGia"));
        if (truongs.contains("soLuong")) dongDonHang.setSoLuong(rs.getInt("soLuong"));
        return dongDonHang;
    }

    default boolean sach(DongDonHang dongDonHang) {
        try {
            String sql = String.format(
                    "SELECT ma, tenSach, thoiGianTao, thoiGianCapNhat FROM sach, dongDonHang " +
                            "WHERE sach.ma = dongDonHang.maSach AND dongDonHang.ma = %d",
                    dongDonHang.getMa());
            ResultSet rs = ketNoi.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                Sach sach = new Sach();
                sach.setMa(rs.getInt("ma"));
                sach.setTen(rs.getString("tenSach"));
                sach.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
                sach.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
                dongDonHang.setSach(sach);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
