package com.nhom4web.dao;

import com.nhom4web.model.Sach;

import javax.servlet.http.Part;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ISachDAO {
    boolean them(Sach sach, List<Part> hinhAnhs, List<Integer> maDanhMucs);

    boolean capNhat(Sach sach, List<Part> hinhAnhs, List<Integer> maDanhMucs);

    List<Sach> timSachLienQuan(int maSach);

    List<Sach> timSachTheo(Map<String, Object> thuocTinhs);

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
