package com.nhom4web.dao;

import com.nhom4web.model.DongDonHang;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDongDonHangDAO {
    default DongDonHang rsSangThucThe(ResultSet rs) throws SQLException {
        DongDonHang dongDonHang = new DongDonHang();
        dongDonHang.setMa(rs.getInt("ma"));
        dongDonHang.setDonGia(rs.getInt("donGia"));
        dongDonHang.setSoLuong(rs.getInt("soLuong"));
        return dongDonHang;
    }
}
