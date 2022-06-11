package com.nhom4web.dao.impl;

import com.nhom4web.dao.IDonHangDAO;
import com.nhom4web.model.DonHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DonHangDAO extends AbstractDAO<DonHang> implements IDonHangDAO {
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
        if (donHang.getNguoiDung().getMa() > 0) duLieu.put("maNguoiDung", donHang.getNguoiDung().getMa());
        if (donHang.getDiaChi() != null) duLieu.put("diaChi", donHang.getDiaChi());
        if (donHang.getTrangThai() != null) duLieu.put("trangThai", donHang.getTrangThai());
        if (donHang.getThoiGianTao() != null) duLieu.put("thoiGianTao", donHang.getThoiGianTao());
        if (donHang.getThoiGianCapNhat() != null) duLieu.put("thoiGianCapNhat", donHang.getThoiGianCapNhat());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(DonHang donHang, ResultSet rs) {
        try {
            if (rs.next()) donHang.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DonHang tim(int ma) {
        DonHang donHang = super.tim(ma);
        return this.dongDonHangs(donHang) ? donHang : null;
    }

    @Override
    public boolean them(DonHang donHang, boolean luu) {
        try {
            if (
                    !super.them(donHang, false) ||
                    !new DongDonHangDAO().them(donHang.getMa(), donHang.getDongDonHangs(), false)
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
    public List<DonHang> layTheoNguoiDung(int maNguoiDung, String trangThai) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE maNguoiDung = ? AND trangThai = ?", this.tenBang);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            DonHangDAO.setThamSoTruyVan(ps, maNguoiDung, trangThai);
            List<DonHang> donHangs = this.sangThucThes(ps.executeQuery());
            for (DonHang donHang : donHangs) {
                if (!this.dongDonHangs(donHang)) {
                    throw new Exception();
                }
            }
        }
        catch (Exception e) {
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
                if (!this.dongDonHangs(donHang)) {
                    throw new Exception();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
