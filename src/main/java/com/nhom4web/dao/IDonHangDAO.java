package com.nhom4web.dao;

import com.nhom4web.model.DonHang;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDonHangDAO {
    default DonHang rsSangThucThe(ResultSet rs) throws SQLException {
        DonHang donHang = new DonHang();
        donHang.setMa(rs.getInt("ma"));
        donHang.setDiaChi(rs.getString("diaChi"));
        donHang.setTrangThai(rs.getString("trangThai"));
        donHang.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
        donHang.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
        return donHang;
    }
}
