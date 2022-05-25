package com.nhom4web.dao;

import com.nhom4web.model.Sach;

import javax.servlet.http.Part;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ISachDAO {
    boolean them(Sach sach, List<Part> hinhAnhs, List<Integer> maDanhMucs);

    boolean capNhat(Sach sach, List<Part> hinhAnhs, List<Integer> maDanhMucs);

    default Sach rsSangThucThe(ResultSet rs) throws SQLException {
        Sach sach = new Sach();
        sach.setMa(rs.getInt("ma"));
        sach.setTen(rs.getString("tenSach"));
        sach.setGiaTien(rs.getInt("giaTien"));
        sach.setSoLuongTrongKho(rs.getInt("soLuongTrongKho"));
        sach.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
        sach.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
        return sach;
    }
}
