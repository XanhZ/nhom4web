package com.nhom4web.service.impl;

import com.nhom4web.dao.impl.NguoiDungDAO;
import com.nhom4web.model.NguoiDung;
import com.nhom4web.service.INguoiDungService;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

public class NguoiDungService implements INguoiDungService {
    private static final NguoiDungDAO dao = new NguoiDungDAO();
    @Override
    public List<NguoiDung> layTatCa() {
        return null;
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
    public NguoiDung timTheoMa(int ma) {
        return dao.layTheoMa(ma);
    }

    @Override
    public boolean xoaTheoMa(int ma) {
        return dao.xoaTheoMa(ma);
    }
}
