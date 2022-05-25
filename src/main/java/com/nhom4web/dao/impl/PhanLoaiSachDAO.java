package com.nhom4web.dao.impl;

import com.nhom4web.dao.IPhanLoaiSach;
import com.nhom4web.model.DanhMuc;
import com.nhom4web.model.PhanLoaiSach;
import com.nhom4web.model.Sach;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class PhanLoaiSachDAO extends AbstractDAO<PhanLoaiSach> implements IPhanLoaiSach {
    public PhanLoaiSachDAO() {
        super("phanLoaiSach");
    }

    @Override
    protected List<PhanLoaiSach> sangThucThes(ResultSet rs) {
        List<PhanLoaiSach> phanLoaiSachs = new ArrayList<>();
        try {
            while (rs.next()) phanLoaiSachs.add(this.rsSangThucThe(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phanLoaiSachs;
    }

    @Override
    protected PhanLoaiSach sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return this.rsSangThucThe(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(PhanLoaiSach phanLoaiSach) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (phanLoaiSach.getMa() != -1) duLieu.put("ma", phanLoaiSach.getMa());
        if (phanLoaiSach.getSach().getMa() != -1) duLieu.put("duongDan", phanLoaiSach.getSach().getMa());
        if (phanLoaiSach.getDanhMuc().getMa() != -1) duLieu.put("publicId", phanLoaiSach.getDanhMuc().getMa());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(PhanLoaiSach phanLoaiSach, ResultSet rs) {
        try {
            if (rs.next()) phanLoaiSach.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean them(int maSach, List<Integer> maDanhMucs) {
        try {
            String[] temps = new String[maDanhMucs.size()];
            Arrays.fill(temps, "(?, ?)");
            String sql = String.format(
                    "INSERT INTO %s (maSach, maDanhMuc) VALUES %s",
                    this.tenBang,
                    String.join(", ", temps)
            );

            PreparedStatement ps = ketNoi.prepareStatement(sql);
            int i = 0;
            for (Integer maDanhMuc : maDanhMucs) {
                this.setThamSoTai(ps, ++i, maSach);
                this.setThamSoTai(ps, ++i, maDanhMuc);
            }

            ps.executeUpdate();
            ketNoi.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean capNhat(int maSach, List<Integer> maDanhMucs) {
        try {
            String sql = String.format("DELETE FROM %s WHERE maSach = ?", this.tenBang);
            PreparedStatement ps1 = ketNoi.prepareStatement(sql);
            this.setThamSoTruyVan(ps1, maSach);
            ps1.executeUpdate();
            ps1.close();

            String[] temps = new String[maDanhMucs.size()];
            Arrays.fill(temps, "(?, ?)");
            sql = String.format(
                    "INSERT INTO %s (maSach, maDanhMuc) VALUES %s",
                    this.tenBang,
                    String.join(", ", temps)
            );

            PreparedStatement ps2 = ketNoi.prepareStatement(sql);
            int i = 0;
            for (Integer maDanhMuc : maDanhMucs) {
                this.setThamSoTai(ps2, ++i, maSach);
                this.setThamSoTai(ps2, ++i, maDanhMuc);
            }

            ps2.executeUpdate();
            ketNoi.commit();
            ps2.close();
            return true;
        } catch (SQLException e1) {
            e1.printStackTrace();
            try {
                ketNoi.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean timDanhMucSach(Sach sach) {
        try {
            String sql = "SELECT C.ma, C.tenDanhMuc FROM sach as A, phanLoaiSach as B, danhMuc as C " +
                    "WHERE A.ma = ? AND A.ma = B.maSach AND B.maDanhMuc = C.ma";
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            this.setThamSoTruyVan(ps, sach.getMa());

            ResultSet rs = ps.executeQuery();
            List<DanhMuc> danhMucs = new ArrayList<>();
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setMa(rs.getInt(1));
                danhMuc.setTen(rs.getString(2));
                danhMucs.add(danhMuc);
            }

            sach.setDanhMucs(danhMucs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
