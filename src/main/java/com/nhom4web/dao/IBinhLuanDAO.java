package com.nhom4web.dao;

import com.nhom4web.model.BinhLuan;
import com.nhom4web.model.NguoiDung;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static com.nhom4web.dao.impl.AbstractDAO.ketNoi;

public interface IBinhLuanDAO {
    static BinhLuan rsSangThucThe(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Set<String> truongs = new HashSet<>();
        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
            truongs.add(rsmd.getColumnName(i));
        }
        BinhLuan binhLuan = new BinhLuan();
        if (truongs.contains("ma")) binhLuan.setMa(rs.getInt("ma"));
        if (truongs.contains("maSach")) binhLuan.setMaSach(rs.getInt("maSach"));
        if (truongs.contains("maNguoiDung")) binhLuan.setMaNguoiDung(rs.getInt("maNguoiDung"));
        if (truongs.contains("noiDung")) binhLuan.setNoiDung(rs.getString("noiDung"));
        if (truongs.contains("thoiGianTao")) binhLuan.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
        if (truongs.contains("thoiGianCapNhat")) binhLuan.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
        return binhLuan;
    }

    default boolean nguoiDung(BinhLuan binhLuan) {
        try {
            String sql = String.format("SELECT nguoiDung.* FROM binhLuan, nguoiDung WHERE binhLuan.ma = %d", binhLuan.getMa());
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NguoiDung nguoiDung = INguoiDungDAO.rsSangThucThe(rs);
                binhLuan.setNguoiDung(nguoiDung);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
