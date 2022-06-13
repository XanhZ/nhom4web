package com.nhom4web.dao;

import com.nhom4web.dao.impl.PhanLoaiSachDAO;
import com.nhom4web.model.PhanLoaiSach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.nhom4web.dao.impl.AbstractDAO.ketNoi;

public interface IPhanLoaiSachDAO {
    static PhanLoaiSach rsSangThucThe(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Set<String> truongs = new HashSet<>();
        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
            truongs.add(rsmd.getColumnName(i));
        }
        PhanLoaiSach phanLoaiSach = new PhanLoaiSach();
        if (truongs.contains("ma")) phanLoaiSach.setMa(rs.getInt("ma"));
        if (truongs.contains("maSach")) phanLoaiSach.setMaSach(rs.getInt("maSach"));
        if (truongs.contains("maDanhMuc")) phanLoaiSach.setMaDanhMuc(rs.getInt("maDanhMuc"));
        return phanLoaiSach;
    }

    List<PhanLoaiSach> timDanhMucSach(int maSach);

    boolean xoaTheoMaSach(int maSach);

    default boolean danhMuc(PhanLoaiSach phanLoaiSach) {
        try {
            String sql = "SELECT danhMuc.* FROM danhMuc, phanLoaiSach " +
                    "WHERE phanLoaiSach.ma = ? AND phanLoaiSach.maDanhMuc = danhMuc.ma";
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            PhanLoaiSachDAO.setThamSoTai(ps, 1, phanLoaiSach.getMa());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                phanLoaiSach.setDanhMuc(IDanhMucDAO.rsSangThucThe(rs));
            }
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
