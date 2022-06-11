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
        if (dongDonHang.getDonGia() > 0) duLieu.put("donGia", dongDonHang.getDonGia());
        if (dongDonHang.getSoLuong() > 0) duLieu.put("soLuong", dongDonHang.getSoLuong());
        if (dongDonHang.getSach() != null) duLieu.put("maSach", dongDonHang.getSach().getMa());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(DongDonHang dongDonHang, ResultSet rs) {
        try {
            if (rs.next()) dongDonHang.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DongDonHang> timTatCa(int maDonHang) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE maDonHang = %d", this.tenBang, maDonHang);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            List<DongDonHang> dongDonHangs =  this.sangThucThes(ps.executeQuery());
            if (dongDonHangs != null) {
                for (DongDonHang dongDonHang : dongDonHangs) {
                    if (!this.sach(dongDonHang)) throw new Exception();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean them(int maDonHang, List<DongDonHang> dongDonHangs, boolean luu) {
        String sql = "INSERT INTO dongDonHang (maSach, maDonHang, soLuong, donGia) VALUES (?, ?, ?, ?)";
        try {
            for (DongDonHang dongDonHang : dongDonHangs) {
                Sach sach = SACH_DAO.tim(dongDonHang.getSach().getMa());
                if (sach == null) throw new Exception("Khong ton tai sach co ma la " + dongDonHang.getSach().getMa());
                sach.setSoLuongTrongKho(sach.getSoLuongTrongKho() - dongDonHang.getSoLuong());
                sach.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
                if (!SACH_DAO.capNhat(sach, false)) throw new Exception("Khong the cap nhat so luong trong kho");

                dongDonHang.setSach(sach);
                dongDonHang.setDonGia(sach.getGiaTien());
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

    @Override
    public boolean them(int maDonHang, DongDonHang dongDonHang, boolean luu) {
        String sql = String.format(
                "INSERT INTO %s (maSach, maDonHang, soLuong, donGia) VALUES (?, ?, ?, ?)",
                this.tenBang
        );
        try {
            Sach sach = SACH_DAO.tim(dongDonHang.getSach().getMa());
            if (sach == null) throw new Exception("Khong ton tai sach co ma la " + dongDonHang.getSach().getMa());
            sach.setSoLuongTrongKho(sach.getSoLuongTrongKho() - dongDonHang.getSoLuong());
            sach.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
            if (!SACH_DAO.capNhat(sach, false)) throw new Exception("Khong the cap nhat so luong trong kho");

            dongDonHang.setSach(sach);
            dongDonHang.setDonGia(sach.getGiaTien());
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
