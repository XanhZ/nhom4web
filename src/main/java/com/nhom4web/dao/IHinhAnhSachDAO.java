package com.nhom4web.dao;

import com.nhom4web.model.HinhAnhSach;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IHinhAnhSachDAO {
    static HinhAnhSach rsSangThucThe(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Set<String> truongs = new HashSet<>();
        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
            truongs.add(rsmd.getColumnName(i));
        }
        HinhAnhSach hinhAnhSach = new HinhAnhSach();
        if (truongs.contains("ma")) hinhAnhSach.setMa(rs.getInt("ma"));
        if (truongs.contains("duongDan")) hinhAnhSach.setDuongDan(rs.getString("duongDan"));
        if (truongs.contains("publicId")) hinhAnhSach.setPublicId(rs.getString("publicId"));
        return hinhAnhSach;
    }

    boolean them(int maSach, List<HinhAnhSach> hinhAnhSachs, boolean luu);

    boolean capNhat(int maSach, List<HinhAnhSach> hinhAnhSachs, boolean luu);

    List<HinhAnhSach> timTatCa(int maSach);
}
