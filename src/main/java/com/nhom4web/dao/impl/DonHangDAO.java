package com.nhom4web.dao.impl;

import com.nhom4web.dao.IDonHangDAO;
import com.nhom4web.model.DonHang;
import com.nhom4web.model.DongDonHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DonHangDAO extends AbstractDAO<DonHang> implements IDonHangDAO {
    private static final DongDonHangDAO DONG_DON_HANG_DAO = new DongDonHangDAO();

    public DonHangDAO() {
        super("donHang");
    }

    @Override
    protected List<DonHang> sangThucThes(ResultSet rs) {
        List<DonHang> donHangs = new ArrayList<>();
        try {
            while (rs.next()) donHangs.add(IDonHangDAO.rsSangThucThe(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donHangs;
    }

    @Override
    protected DonHang sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return IDonHangDAO.rsSangThucThe(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(DonHang donHang) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (donHang.getMa() > 0) duLieu.put("ma", donHang.getMa());
        if (donHang.getMaNguoiDung() > 0) duLieu.put("maNguoiDung", donHang.getMaNguoiDung());
        if (donHang.getDiaChi() != null) duLieu.put("diaChi", donHang.getDiaChi());
        if (donHang.getTrangThai() != null) duLieu.put("trangThai", donHang.getTrangThai());
        if (donHang.getThoiGianTao() != null) duLieu.put("thoiGianTao", donHang.getThoiGianTao());
        if (donHang.getThoiGianCapNhat() != null) duLieu.put("thoiGianCapNhat", donHang.getThoiGianCapNhat());
        return duLieu;
    }

    @Override
    public DonHang tim(int ma) {
        DonHang donHang = super.tim(ma);
        if (!this.dongDonHangs(donHang)) return null;
        for (DongDonHang dongDonHang : donHang.getDongDonHangs()) {
            if (!DONG_DON_HANG_DAO.sach(dongDonHang)) return null;
        }
        return donHang;
    }

    @Override
    public boolean them(DonHang donHang, boolean luu) {
        try {
            if (!super.them(donHang, false)) throw new Exception();
            for (DongDonHang dongDonHang : donHang.getDongDonHangs()) {
                dongDonHang.setMaDonHang(donHang.getMa());
            }
            if (!DONG_DON_HANG_DAO.them(donHang.getDongDonHangs(), false)) throw new Exception();
            if (luu) ketNoi.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }

    @Override
    public List<DonHang> layTheoNguoiDung(int maNguoiDung, String trangThai) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE maNguoiDung = ? AND trangThai = ?", this.tenBang);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            DonHangDAO.setThamSoTruyVan(ps, maNguoiDung, trangThai);
            List<DonHang> donHangs = this.sangThucThes(ps.executeQuery());
            for (DonHang donHang : donHangs) {
                if (!this.dongDonHangs(donHang)) throw new Exception();
                for (DongDonHang dongDonHang : donHang.getDongDonHangs()) {
                    if (!DONG_DON_HANG_DAO.sach(dongDonHang)) throw new Exception();
                }
            }
            return donHangs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DonHang> layTatCa(String trangThai) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE trangThai = ? ORDER BY thoiGianTao DESC", this.tenBang);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            DonHangDAO.setThamSoTruyVan(ps, trangThai);
            List<DonHang> donHangs = this.sangThucThes(ps.executeQuery());
            for (DonHang donHang : donHangs) {
                if (!this.dongDonHangs(donHang) || !this.nguoiDung(donHang)) throw new Exception();
                for (DongDonHang dongDonHang : donHang.getDongDonHangs()) {
                    if (!DONG_DON_HANG_DAO.sach(dongDonHang)) throw new Exception();
                }
            }
            return donHangs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
