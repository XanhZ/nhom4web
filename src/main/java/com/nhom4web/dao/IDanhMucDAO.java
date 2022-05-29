package com.nhom4web.dao;

import com.nhom4web.model.DanhMuc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDanhMucDAO {
    default DanhMuc rsSangThucThe(ResultSet rs) throws SQLException {
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setMa(rs.getInt("ma"));
        danhMuc.setTen(rs.getString("tenDanhMuc"));
        danhMuc.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
        danhMuc.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
        return danhMuc;
    }
}
