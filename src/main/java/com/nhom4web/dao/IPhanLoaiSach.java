package com.nhom4web.dao;

import com.nhom4web.model.PhanLoaiSach;
import com.nhom4web.model.Sach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IPhanLoaiSach {
    boolean them(int maSach, List<Integer> maDanhMucs);

    boolean capNhat(int maSach, List<Integer> maDanhMucs);

    boolean timDanhMucSach(Sach sach);

    default PhanLoaiSach rsSangThucThe(ResultSet rs) throws SQLException {
        PhanLoaiSach phanLoaiSach = new PhanLoaiSach();
        phanLoaiSach.setMa(rs.getInt("ma"));
        return phanLoaiSach;
    }
}
