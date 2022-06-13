package com.nhom4web.dao.impl;

import com.nhom4web.dao.INguoiDungDAO;
import com.nhom4web.model.NguoiDung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class NguoiDungDAO extends AbstractDAO<NguoiDung> implements INguoiDungDAO {
    public NguoiDungDAO() {
        super("nguoiDung");
    }

    @Override
    protected List<NguoiDung> sangThucThes(ResultSet rs) {
        List<NguoiDung> nguoiDungs = new ArrayList<>();
        try {
            while (rs.next()) nguoiDungs.add(INguoiDungDAO.rsSangThucThe(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return nguoiDungs;
    }

    @Override
    protected NguoiDung sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return INguoiDungDAO.rsSangThucThe(rs);
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
}
