package com.nhom4web.dao;

import com.nhom4web.model.ThongTinDangNhap;

import java.util.LinkedHashMap;

public interface IThongTinDangNhapDAO {
    ThongTinDangNhap layThongTinDangNhap(int maNguoiDung);
    ThongTinDangNhap layThongTinDangNhap(LinkedHashMap<String, Object> duLieu);
    ThongTinDangNhap timTheoTaiKhoanMatKhau(LinkedHashMap<String, Object> duLieu);
}
