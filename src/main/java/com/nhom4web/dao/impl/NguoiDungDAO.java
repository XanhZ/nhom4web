package com.nhom4web.dao.impl;

import com.nhom4web.model.NguoiDung;
import com.nhom4web.service.INguoiDungService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NguoiDungDAO extends AbstractDAO<NguoiDung> implements INguoiDungService {
    public NguoiDungDAO() {
        this.setTenBang("nguoiDung");
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
    public NguoiDung timTheoMa(int ma) {
        return null;
    }
}
