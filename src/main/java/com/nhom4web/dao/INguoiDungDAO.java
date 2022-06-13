package com.nhom4web.dao;

import com.nhom4web.model.NguoiDung;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public interface INguoiDungDAO {
    static NguoiDung rsSangThucThe(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Set<String> truongs = new HashSet<>();
        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
            truongs.add(rsmd.getColumnName(i));
        }
        NguoiDung nguoiDung = new NguoiDung();
        if (truongs.contains("ma")) nguoiDung.setMa(rs.getInt("ma"));
        if (truongs.contains("sdt")) nguoiDung.setSdt(rs.getString("sdt"));
        if (truongs.contains("email")) nguoiDung.setEmail(rs.getString("email"));
        if (truongs.contains("ten")) nguoiDung.setTen(rs.getString("ten"));
        if (truongs.contains("loaiNguoiDung")) nguoiDung.setLoaiNguoiDung(rs.getInt("loaiNguoiDung"));
        return nguoiDung;
    }
}
