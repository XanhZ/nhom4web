package com.nhom4web.dao;

import com.nhom4web.model.DonHang;
import com.nhom4web.model.DongDonHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.nhom4web.dao.impl.AbstractDAO.ketNoi;

public interface IDonHangDAO {
    List<DonHang> layTheoNguoiDung(int maNguoiDung, String trangThai);

    List<DonHang> layTatCa(String trangThai);

    static DonHang rsSangThucThe(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Set<String> truongs = new HashSet<>();
        for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
            truongs.add(rsmd.getColumnName(i));
        }
        DonHang donHang = new DonHang();
        if (truongs.contains("ma")) donHang.setMa(rs.getInt("ma"));
        if (truongs.contains("diaChi")) donHang.setDiaChi(rs.getString("diaChi"));
        if (truongs.contains("trangThai")) donHang.setTrangThai(rs.getString("trangThai"));
        if (truongs.contains("thoiGianTao")) donHang.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
        if (truongs.contains("thoiGianCapNhat")) donHang.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));
        return donHang;
    }

    default boolean dongDonHangs(DonHang donHang) {
        try {
            String sql = String.format("SELECT * FROM dongDonHang WHERE maDonHang = %d", donHang.getMa());
            ResultSet rs = ketNoi.prepareStatement(sql).executeQuery();
            List<DongDonHang> dongDonHangs = new ArrayList<>();
            while (rs.next()) dongDonHangs.add(IDongDonHangDAO.rsSangThucThe(rs));
            donHang.setDongDonHangs(dongDonHangs);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
