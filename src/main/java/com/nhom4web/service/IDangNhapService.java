package com.nhom4web.service;

import com.nhom4web.model.ThongTinDangNhap;

import java.util.LinkedHashMap;

public interface IDangNhapService {
    int them(LinkedHashMap<String, Object> duLieu);

    ThongTinDangNhap timTheoMa(int ma);
    ThongTinDangNhap timTheoTaiKhoanMatKhau(LinkedHashMap<String, Object> duLieu);
}
