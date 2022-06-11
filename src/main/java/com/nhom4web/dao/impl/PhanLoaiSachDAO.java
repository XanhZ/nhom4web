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
    public boolean them(int maSach, List<Integer> maDanhMucs, boolean luu) {
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
                PhanLoaiSachDAO.setThamSoTai(ps, ++i, maSach);
                PhanLoaiSachDAO.setThamSoTai(ps, ++i, maDanhMuc);
            }

            ps.executeUpdate();
            if (luu) ketNoi.commit();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.rollback();
        }
        return false;
    }

    @Override
    public boolean capNhat(int maSach, List<Integer> maDanhMucs, boolean luu) {
        try {
            String sql = String.format("DELETE FROM %s WHERE maSach = %d", this.tenBang, maSach);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            if (this.them(maSach, maDanhMucs, false)) {
                if (luu) ketNoi.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
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
}
