package com.nhom4web.dao.impl;

import com.nhom4web.dao.INguoiDungDAO;
import com.nhom4web.model.NguoiDung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

public class NguoiDungDAO extends AbstractDAO<NguoiDung> implements INguoiDungDAO {
    public NguoiDungDAO() {
        super("nguoiDung");
    }

    @Override
    protected List<NguoiDung> sangThucThes(ResultSet rs) {
        return null;
    }

    @Override
    protected NguoiDung sangThucThe(ResultSet rs) {
        try {
            int ma = rs.getInt(1);
            String sdt = rs.getString(2);
            String email = rs.getString(3);
            String ten = rs.getString(4);
            int loaiNguoiDung = rs.getInt(5);
            Timestamp thoiGianTao = rs.getTimestamp(6);
            Timestamp thoiGianCapNhat = rs.getTimestamp(7);
//            int gioiTinh = rs.getInt(8);
//            String diaChi = rs.getString(9);

            return new NguoiDung(ma, sdt, email, ten, loaiNguoiDung);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(NguoiDung nguoiDung) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (nguoiDung.getMa() != -1) duLieu.put("ma", nguoiDung.getMa());
        if (nguoiDung.getSdt() != null) duLieu.put("sdt", nguoiDung.getSdt());
        if (nguoiDung.getEmail() != null) duLieu.put("email", nguoiDung.getEmail());
        if (nguoiDung.getTen() != null) duLieu.put("ten", nguoiDung.getTen());
        if (nguoiDung.getLoaiNguoiDung() != null) duLieu.put("loaiNguoiDung", nguoiDung.getLoaiNguoiDung());
        if (nguoiDung.getThoiGianTao() != null) duLieu.put("thoiGianTao", nguoiDung.getThoiGianTao());
        if (nguoiDung.getThoiGianCapNhat() != null) duLieu.put("thoiGianCapNhat", nguoiDung.getThoiGianCapNhat());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(NguoiDung nguoiDung, ResultSet rs) {
        try {
            if (rs.next()) nguoiDung.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
