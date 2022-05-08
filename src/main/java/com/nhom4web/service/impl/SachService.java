package com.nhom4web.service.impl;

import com.nhom4web.dao.impl.HinhAnhSachDAO;
import com.nhom4web.dao.impl.PhanLoaiSachDAO;
import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.Sach;
import com.nhom4web.service.ISachService;

import java.util.LinkedHashMap;
import java.util.List;

public class SachService implements ISachService {
    private static final SachDAO SACH_DAO = new SachDAO();
    private static final PhanLoaiSachDAO PHAN_LOAI_SACH_DAO = new PhanLoaiSachDAO();
    private static final HinhAnhSachDAO HINH_ANH_SACH_DAO = new HinhAnhSachDAO();

    @Override
    public List<Sach> layTatCa() {
        return null;
    }

    @Override
    public boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma) {
        return false;
    }

    @Override
    public int them(LinkedHashMap<String, Object> duLieu) {
        return SACH_DAO.them(duLieu);
    }

    @Override
    public Sach timTheoMa(int ma) {
        return null;
    }

    @Override
    public boolean xoaTheoMa(int ma) {
        return false;
    }

    @Override
    public boolean phanLoaiSach(int maSach, List<Integer> maDanhMucs) {
        return PHAN_LOAI_SACH_DAO.them(maSach, maDanhMucs);
    }

    @Override
    public boolean themHinhAnhSach(int maSach, List<String> duongDans) {
        return HINH_ANH_SACH_DAO.them(maSach, duongDans);
    }
}
