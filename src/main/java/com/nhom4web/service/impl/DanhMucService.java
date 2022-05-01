package com.nhom4web.service.impl;

import com.nhom4web.dao.impl.DanhMucDAO;
import com.nhom4web.model.DanhMuc;
import com.nhom4web.service.IDanhMucService;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

public class DanhMucService implements IDanhMucService {
    private static final DanhMucDAO dao = new DanhMucDAO();

    @Override
    public List<DanhMuc> layTatCa() {
        return dao.layTatCa();
    }

    @Override
    public boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma) {
        duLieu.put("thoiGianCapNhat", new Timestamp(System.currentTimeMillis()));
        return dao.capNhat(duLieu, ma);
    }

    @Override
    public int them(LinkedHashMap<String, Object> duLieu) {
        return dao.them(duLieu);
    }

    @Override
    public DanhMuc timTheoMa(int ma) {
        return dao.layTheoMa(ma);
    }

    @Override
    public boolean xoaTheoMa(int ma) {
        return dao.xoaTheoMa(ma);
    }
}
