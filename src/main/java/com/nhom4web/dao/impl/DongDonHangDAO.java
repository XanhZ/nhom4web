package com.nhom4web.dao.impl;

import com.nhom4web.dao.IDongDonHangDAO;
import com.nhom4web.model.DongDonHang;
import com.nhom4web.model.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DongDonHangDAO extends AbstractDAO<DongDonHang> implements IDongDonHangDAO {
    private static final SachDAO SACH_DAO = new SachDAO();

    public DongDonHangDAO() {
        super("dongDonHang");
    }

    @Override
    protected List<DongDonHang> sangThucThes(ResultSet rs) {
        List<DongDonHang> dongDonHangs = new ArrayList<>();
        try {
            while (rs.next()) dongDonHangs.add(IDongDonHangDAO.rsSangThucThe(rs));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dongDonHangs;
    }

    @Override
    protected DongDonHang sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return IDongDonHangDAO.rsSangThucThe(rs);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(DongDonHang dongDonHang) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (dongDonHang.getMa() > 0) duLieu.put("ma", dongDonHang.getMa());
        if (dongDonHang.getMaDonHang() > 0) duLieu.put("maDonHang", dongDonHang.getMaDonHang());
        if (dongDonHang.getDonGia() > 0) duLieu.put("donGia", dongDonHang.getDonGia());
        if (dongDonHang.getSoLuong() > 0) duLieu.put("soLuong", dongDonHang.getSoLuong());
        if (dongDonHang.getMaSach() > 0) duLieu.put("maSach", dongDonHang.getMaSach());
        return duLieu;
    }

    @Override
    public DongDonHang tim(int ma) {
        DongDonHang dongDonHang = super.tim(ma);
        if (dongDonHang == null || !this.sach(dongDonHang)) return null;
        return dongDonHang;
    }

    @Override
    public List<DongDonHang> timTatCa(int maDonHang) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE maDonHang = %d", this.tenBang, maDonHang);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            List<DongDonHang> dongDonHangs =  this.sangThucThes(ps.executeQuery());
            if (dongDonHangs != null) {
                for (DongDonHang dongDonHang : dongDonHangs) {
                    if (!this.sach(dongDonHang)) return null;
                }
            }
            return dongDonHangs;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean them(DongDonHang dongDonHang, boolean luu) {
        Sach sach = dongDonHang.getSach();
        sach.setSoLuongTrongKho(sach.getSoLuongTrongKho() - dongDonHang.getSoLuong());
        sach.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
        return SACH_DAO.capNhat(sach, luu) && super.them(dongDonHang, luu);
    }

    @Override
    public boolean them(List<DongDonHang> list, boolean luu) {
        for (DongDonHang dongDonHang : list) {
            Sach sach = dongDonHang.getSach();
            sach.setSoLuongTrongKho(sach.getSoLuongTrongKho() - dongDonHang.getSoLuong());
            sach.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
            if (!SACH_DAO.capNhat(sach, luu)) return false;
        }
        return super.them(list, luu);
    }

    @Override
    public boolean them(int maDonHang, DongDonHang dongDonHang, boolean luu) {
        String sql = String.format(
                "INSERT INTO %s (maSach, maDonHang, soLuong, donGia) VALUES (?, ?, ?, ?)",
                this.tenBang
        );
        try {
            Sach sach = dongDonHang.getSach();
            sach.setSoLuongTrongKho(sach.getSoLuongTrongKho() - dongDonHang.getSoLuong());
            sach.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
            if (!SACH_DAO.capNhat(sach, false)) throw new Exception();
            PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            DongDonHangDAO.setThamSoTruyVan(
                    ps,
                    sach.getMa(),
                    maDonHang,
                    dongDonHang.getSoLuong(),
                    dongDonHang.getDonGia()
            );
            ps.executeUpdate();
            this.setKhoaChinh(dongDonHang, ps.getGeneratedKeys());
            if (luu) ketNoi.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }

    @Override
    public boolean capNhat(DongDonHang dongDonHang, boolean luu) {
        try {
            String sql = String.format(
                    "SELECT maSach, soLuong FROM %s WHERE ma = %d",
                    this.tenBang,
                    dongDonHang.getMa()
            );
            ResultSet rs = ketNoi.prepareStatement(sql).executeQuery();
            Sach sach;
            if (rs.next()) {
                sach = SACH_DAO.tim(rs.getInt("maSach"));
                if (sach == null) return false;
                sach.setSoLuongTrongKho(
                        sach.getSoLuongTrongKho() + rs.getInt("soLuong") - dongDonHang.getSoLuong()
                );
                if (!super.capNhat(dongDonHang, false) || !SACH_DAO.capNhat(sach, false))
                    throw new Exception();
            }
            if (luu) ketNoi.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }
}
