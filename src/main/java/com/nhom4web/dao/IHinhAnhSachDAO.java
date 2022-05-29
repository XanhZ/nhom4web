package com.nhom4web.dao;

import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.model.Sach;

import javax.servlet.http.Part;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IHinhAnhSachDAO {
    List<HinhAnhSach> them(int maSach, List<Part> hinhAnhs, boolean luu);

    List<HinhAnhSach> capNhat(int maSach, List<Part> hinhAnhs);

    boolean timTatCa(Sach sach);

    List<HinhAnhSach> timTatCa(int maSach);

    default HinhAnhSach rsSangThucThe(ResultSet rs) throws SQLException {
        HinhAnhSach hinhAnhSach = new HinhAnhSach();
        hinhAnhSach.setMa(rs.getInt("ma"));
        hinhAnhSach.setDuongDan(rs.getString("duongDan"));
        hinhAnhSach.setPublicId(rs.getString("publicId"));
        return hinhAnhSach;
    }
}
