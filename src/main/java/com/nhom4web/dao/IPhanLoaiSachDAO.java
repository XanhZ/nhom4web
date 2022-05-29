package com.nhom4web.dao;

import com.nhom4web.model.PhanLoaiSach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IPhanLoaiSachDAO {
    boolean them(int maSach, List<Integer> maDanhMucs, boolean luu);

    boolean capNhat(int maSach, List<Integer> maDanhMucs, boolean luu);

    List<PhanLoaiSach> timDanhMucSach(int maSach);

    default PhanLoaiSach rsSangThucThe(ResultSet rs) throws SQLException {
        PhanLoaiSach phanLoaiSach = new PhanLoaiSach();
        phanLoaiSach.setMa(rs.getInt("ma"));
        return phanLoaiSach;
    }
}
