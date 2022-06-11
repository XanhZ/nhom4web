package com.nhom4web.dao;

import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.model.Sach;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import static com.nhom4web.dao.impl.AbstractDAO.ketNoi;

public interface ISachDAO {
    boolean them(Sach sach, List<Integer> maDanhMucs);

    boolean capNhat(Sach sach, List<Integer> maDanhMucs);

    List<Sach> timSachLienQuan(int maSach);

    List<Sach> timSachTheo(Map<String, Object> thuocTinhs);

    static Sach rsSangThucThe(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Set<String> truongs = new HashSet<>();
        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
            truongs.add(rsmd.getColumnName(i));
        }
        Sach sach = new Sach();
        if (truongs.contains("ma")) sach.setMa(rs.getInt("ma"));
        if (truongs.contains("tenSach")) sach.setTen(rs.getString("tenSach"));
        if (truongs.contains("giaTien")) sach.setGiaTien(rs.getInt("giaTien"));
        if (truongs.contains("soLuongTrongKho")) sach.setSoLuongTrongKho(rs.getInt("soLuongTrongKho"));
        if (truongs.contains("thoiGianTao")) sach.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
        if (truongs.contains("thoiGianCapNhat")) sach.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
        return sach;
    }

    default boolean hinhAnhSachs(Sach sach) {
        try {
            String sql = String.format("SELECT * FROM hinhAnhSach WHERE maSach = %d", sach.getMa());
            ResultSet rs = ketNoi.prepareStatement(sql).executeQuery();
            List<HinhAnhSach> hinhAnhSachs = new ArrayList<>();
            while (rs.next()) hinhAnhSachs.add(IHinhAnhSachDAO.rsSangThucThe(rs));
            sach.setHinhAnhSachs(hinhAnhSachs);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
