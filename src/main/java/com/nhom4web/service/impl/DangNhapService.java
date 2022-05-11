package com.nhom4web.service.impl;

import com.nhom4web.dao.impl.ThongTinDangNhapDAO;
import com.nhom4web.model.ThongTinDangNhap;
import com.nhom4web.service.IDangNhapService;

import java.util.LinkedHashMap;

public class DangNhapService implements IDangNhapService {
    private static final ThongTinDangNhapDAO dao = new ThongTinDangNhapDAO();

    @Override
    public int them(LinkedHashMap<String, Object> duLieu) {
        return dao.them(duLieu);
    }

    @Override
    public ThongTinDangNhap timTheoMa(int maNguoiDung) {
        return dao.layThongTinDangNhap(maNguoiDung);
    }

    @Override
    public ThongTinDangNhap timTheoTaiKhoanMatKhau(LinkedHashMap<String, Object> duLieu) {
        return dao.layThongTinDangNhap(duLieu);
    }
}
