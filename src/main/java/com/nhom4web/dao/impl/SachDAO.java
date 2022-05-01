package com.nhom4web.dao.impl;

import com.nhom4web.dao.ISachDAO;
import com.nhom4web.model.DanhMuc;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.model.Sach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class SachDAO extends AbstractDAO<Sach> implements ISachDAO {
    public SachDAO() {
        this.setTenBang("sach");
    }

    @Override
    protected Sach sangThucThe(ResultSet rs) {
        try {
            int ma = rs.getInt(1);
            int maDanhMuc = rs.getInt(2);
            String tenSach = rs.getString(3);
            int giaTien = rs.getInt(4);
            int soLuongTrongKho = rs.getInt(5);
            Timestamp thoiGianTao = rs.getTimestamp(6);
            Timestamp thoiGianCapNhat = rs.getTimestamp(7);
            return new Sach(ma, maDanhMuc, tenSach, giaTien, soLuongTrongKho, thoiGianTao, thoiGianCapNhat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HinhAnhSach> layHinhAnh(Sach sach) {
        return null;
    }

    @Override
    public List<DanhMuc> layDanhMucSach(Sach sach) {
        return null;
    }
}
