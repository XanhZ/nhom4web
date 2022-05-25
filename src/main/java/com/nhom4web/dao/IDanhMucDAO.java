package com.nhom4web.dao;

import com.nhom4web.model.DanhMuc;
import com.nhom4web.model.Sach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDanhMucDAO {
    List<Sach> sachTrongDanhMuc(DanhMuc danhMuc);

    default DanhMuc rsSangThucThe(ResultSet rs) throws SQLException {
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setMa(rs.getInt("ma"));
        danhMuc.setTen(rs.getString("tenDanhMuc"));
        danhMuc.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
        danhMuc.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
        return danhMuc;
    }
}
