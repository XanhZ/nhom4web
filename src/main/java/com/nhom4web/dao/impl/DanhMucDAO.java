package com.nhom4web.dao.impl;

import com.nhom4web.dao.IDanhMucDAO;
import com.nhom4web.model.DanhMuc;
import com.nhom4web.model.Sach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class DanhMucDAO extends AbstractDAO<DanhMuc> implements IDanhMucDAO {
    public DanhMucDAO() {
        this.setTenBang("danhmuc");
    }

    @Override
    protected DanhMuc sangThucThe(ResultSet rs) {
        try {
            int ma = rs.getInt(1);
            String tenDanhMuc = rs.getString(2);
            Timestamp thoiGianTao = rs.getTimestamp(3);
            Timestamp thoiGianCapNhat = rs.getTimestamp(4);
            return new DanhMuc(ma, tenDanhMuc, thoiGianTao, thoiGianCapNhat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Sach> sachTrongDanhMuc(DanhMuc danhMuc) {
        return null;
    }
}
