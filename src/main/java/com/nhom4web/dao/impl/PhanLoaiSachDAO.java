package com.nhom4web.dao.impl;

import com.nhom4web.dao.IPhanLoaiSachDAO;
import com.nhom4web.model.DanhMuc;
import com.nhom4web.model.PhanLoaiSach;

import java.sql.*;
import java.util.*;

public class PhanLoaiSachDAO extends AbstractDAO<PhanLoaiSach> implements IPhanLoaiSachDAO {
    public PhanLoaiSachDAO() {
        super("phanLoaiSach");
    }

    @Override
    protected List<PhanLoaiSach> sangThucThes(ResultSet rs) {
        List<PhanLoaiSach> phanLoaiSachs = new ArrayList<>();
        try {
            while (rs.next()) phanLoaiSachs.add(IPhanLoaiSachDAO.rsSangThucThe(rs));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return phanLoaiSachs;
    }

    @Override
    protected PhanLoaiSach sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return IPhanLoaiSachDAO.rsSangThucThe(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(PhanLoaiSach phanLoaiSach) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (phanLoaiSach.getMa() > 0) duLieu.put("ma", phanLoaiSach.getMa());
        if (phanLoaiSach.getMaDanhMuc() > 0) duLieu.put("maDanhMuc", phanLoaiSach.getMaDanhMuc());
        if (phanLoaiSach.getMaSach() > 0) duLieu.put("maSach", phanLoaiSach.getMaSach());
        return duLieu;
    }

    @Override
    public PhanLoaiSach tim(int ma) {
        PhanLoaiSach phanLoaiSach = super.tim(ma);
        if (!this.danhMuc(phanLoaiSach)) return null;
        return phanLoaiSach;
    }

    @Override
    public boolean capNhat(PhanLoaiSach phanLoaiSach, boolean luu) {
        return super.capNhat(phanLoaiSach, luu) && this.danhMuc(phanLoaiSach);
    }

    @Override
    public boolean them(PhanLoaiSach phanLoaiSach, boolean luu) {
        return super.them(phanLoaiSach, luu) && this.danhMuc(phanLoaiSach);
    }

    @Override
    public List<PhanLoaiSach> timDanhMucSach(int maSach) {
        try {
            List<PhanLoaiSach> phanLoaiSachs = new LinkedList<>();
            String sql = "SELECT B.ma, B.maDanhMuc, A.tenDanhMuc, A.thoiGianTao, A.thoiGianCapNhat " +
                    "FROM danhMuc AS A, phanLoaiSach AS B " +
                    "WHERE B.maSach = ? AND B.maDanhMuc = A.ma";
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            PhanLoaiSachDAO.setThamSoTai(ps, 1, maSach);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhanLoaiSach phanLoaiSach = new PhanLoaiSach();
                phanLoaiSach.setMa(rs.getInt("ma"));

                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setMa(rs.getInt("maDanhMuc"));
                danhMuc.setTen(rs.getString("tenDanhMuc"));
                danhMuc.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
                danhMuc.setThoiGianCapNhat(rs.getTimestamp("thoiGianCapNhat"));

                phanLoaiSach.setDanhMuc(danhMuc);

                phanLoaiSachs.add(phanLoaiSach);
            }
            ps.close();
            return phanLoaiSachs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean xoaTheoMaSach(int maSach) {
        try {
            String sql = String.format("DELETE FROM %s WHERE maSach = %d", this.tenBang, maSach);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
