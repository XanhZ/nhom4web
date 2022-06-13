package com.nhom4web.dao.impl;

import com.nhom4web.dao.ISachDAO;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.model.PhanLoaiSach;
import com.nhom4web.model.Sach;

import java.sql.*;
import java.util.*;

public class SachDAO extends AbstractDAO<Sach> implements ISachDAO {
    private static final PhanLoaiSachDAO PHAN_LOAI_SACH_DAO = new PhanLoaiSachDAO();
    private static final HinhAnhSachDAO HINH_ANH_SACH_DAO = new HinhAnhSachDAO();
    public SachDAO() {
        super("sach");
    }

    @Override
    protected Sach sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return ISachDAO.rsSangThucThe(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Sach> sangThucThes(ResultSet rs) {
        List<Sach> sachs = new ArrayList<>();
        try {
            while (rs.next()) sachs.add(ISachDAO.rsSangThucThe(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sachs;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(Sach sach) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (sach.getMa() > 0) duLieu.put("ma", sach.getMa());
        if (sach.getTen() != null) duLieu.put("tenSach", sach.getTen());
        if (sach.getGiaTien() != -1) duLieu.put("giaTien", sach.getGiaTien());
        if (sach.getSoLuongTrongKho() != -1) duLieu.put("soLuongTrongKho", sach.getSoLuongTrongKho());
        if (sach.getThoiGianTao() != null) duLieu.put("thoiGianTao", sach.getThoiGianTao());
        if (sach.getThoiGianCapNhat() != null) duLieu.put("thoiGianCapNhat", sach.getThoiGianCapNhat());
        return duLieu;
    }

    @Override
    public List<Sach> layTatCa() {
        List<Sach> sachs = super.layTatCa();
        for (Sach sach : sachs) {
            if (!this.hinhAnhSachs(sach)) return null;
        }
        return sachs;
    }

    @Override
    public Sach tim(int ma) {
        Sach sach = super.tim(ma);
        if (sach == null) return null;
        return this.hinhAnhSachs(sach) ? sach : null;
    }

    @Override
    public boolean them(Sach sach, boolean luu) {
        try {
            if (!super.them(sach, false)) throw new Exception();

            for (HinhAnhSach hinhAnhSach : sach.getHinhAnhSachs()) {
                hinhAnhSach.setMaSach(sach.getMa());
            }
            for (PhanLoaiSach phanLoaiSach : sach.getPhanLoaiSachs()) {
                phanLoaiSach.setMaSach(sach.getMa());
            }

            if (!PHAN_LOAI_SACH_DAO.them(sach.getPhanLoaiSachs(), false) ||
                    !HINH_ANH_SACH_DAO.them(sach.getHinhAnhSachs(), false)
            ) throw new Exception();

            if (luu) ketNoi.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }

    @Override
    public List<Sach> timSachLienQuan(int maSach) {
        return null;
    }

    @Override
    public List<Sach> timSachTheo(Map<String, Object> thuocTinhs) {
/*        try {
            String sql = "SELECT * FROM sach " +
                    "INNER JOIN phanLoaiSach ON sach.ma = phanLoaiSach.maSach " +
                    "WHERE phanLoaiSach.maSach IN ?";
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            this.setThamSoTai(ps, 1, maDanhMucs);
            List<Sach> sachs = this.sangThucThes(ps.executeQuery());
            for (Sach sach : sachs) {
                HINH_ANH_SACH_DAO.timTatCa(sach);
            }
            return sachs;
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public boolean capNhatToanBo(Sach sach, boolean luu) {
        try {
            if (
                    !super.capNhat(sach, false) ||
                            !PHAN_LOAI_SACH_DAO.xoaTheoMaSach(sach.getMa()) ||
                            !PHAN_LOAI_SACH_DAO.them(sach.getPhanLoaiSachs(), false) ||
                            !HINH_ANH_SACH_DAO.xoaTheoMaSach(sach.getMa()) ||
                            !HINH_ANH_SACH_DAO.them(sach.getHinhAnhSachs(), false)
            ) throw new Exception();
            if (luu) ketNoi.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }

    @Override
    public boolean xoa(int ma, boolean commit) {
        if (!new HinhAnhSachDAO().xoaTrenCloud(ma)) return false;
        return super.xoa(ma, commit);
    }
}
