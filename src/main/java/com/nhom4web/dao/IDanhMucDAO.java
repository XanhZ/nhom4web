package com.nhom4web.dao;

import com.nhom4web.model.DanhMuc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public interface IDanhMucDAO {
    static DanhMuc rsSangThucThe(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Set<String> truongs = new HashSet<>();
        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
            truongs.add(rsmd.getColumnName(i));
        }
        DanhMuc danhMuc = new DanhMuc();
        if (truongs.contains("ma")) danhMuc.setMa(rs.getInt("ma"));
        if (truongs.contains("tenDanhMuc")) danhMuc.setTen(rs.getString("tenDanhMuc"));
        if (truongs.contains("thoiGianTao")) danhMuc.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
        if (truongs.contains("thoiGianCapNhat")) danhMuc.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
        return danhMuc;
    }
}
