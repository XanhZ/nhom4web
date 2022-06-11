package com.nhom4web.dao.impl;

import com.nhom4web.dao.IHinhAnhSachDAO;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.model.Sach;
import com.nhom4web.utils.HinhAnh;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class HinhAnhSachDAO extends AbstractDAO<HinhAnhSach> implements IHinhAnhSachDAO {
    public HinhAnhSachDAO() {
        super("hinhAnhSach");
    }

    @Override
    protected List<HinhAnhSach> sangThucThes(ResultSet rs) {
        List<HinhAnhSach> hinhAnhSachs = new ArrayList<>();
        try {
            while (rs.next()) hinhAnhSachs.add(IHinhAnhSachDAO.rsSangThucThe(rs));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hinhAnhSachs;
    }

    @Override
    protected HinhAnhSach sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return IHinhAnhSachDAO.rsSangThucThe(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(HinhAnhSach hinhAnhSach) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (hinhAnhSach.getMa() != -1) duLieu.put("ma", hinhAnhSach.getMa());
        if (hinhAnhSach.getDuongDan() != null) duLieu.put("duongDan", hinhAnhSach.getDuongDan());
        if (hinhAnhSach.getPublicId() != null) duLieu.put("publicId", hinhAnhSach.getPublicId());
        return duLieu;
    }

    @Override
    protected void setKhoaChinh(HinhAnhSach hinhAnhSach, ResultSet rs) {
        try {
            if (rs.next()) hinhAnhSach.setMa(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean them(int maSach, List<HinhAnhSach> hinhAnhSachs, boolean luu) {
        try {
            String[] temp = new String[hinhAnhSachs.size()];
            Arrays.fill(temp, "(?, ?, ?)");
            String sql = String.format(
                    "INSERT INTO %s (maSach, duongDan, publicId) VALUES %s;",
                    this.tenBang,
                    String.join(", ", temp)
            );
            PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            for (HinhAnhSach hinhAnhSach : hinhAnhSachs) {
                HinhAnhSachDAO.setThamSoTai(ps, ++i, maSach);
                HinhAnhSachDAO.setThamSoTai(ps, ++i, hinhAnhSach.getDuongDan());
                HinhAnhSachDAO.setThamSoTai(ps, ++i, hinhAnhSach.getPublicId());
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            for (HinhAnhSach hinhAnhSach : hinhAnhSachs) {
                this.setKhoaChinh(hinhAnhSach, rs);
            }

            if (luu) ketNoi.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }

    @Override
    public boolean capNhat(int maSach, List<HinhAnhSach> hinhAnhSachs, boolean luu) {
        try {
            String sql = String.format("SELECT publicId FROM %s WHERE maSach = %d", this.tenBang, maSach);
            PreparedStatement ps1 = ketNoi.prepareStatement(sql);
            ResultSet rs = ps1.executeQuery();
            List<String> publicIds = new ArrayList<>();
            while (rs.next()) {
                publicIds.add(rs.getString("publicId"));
            }
            ps1.close();

            sql = String.format("DELETE FROM %s WHERE maSach = %d", this.tenBang, maSach);
            PreparedStatement ps2 = ketNoi.prepareStatement(sql);
            ps2.executeUpdate();
            ps2.close();

            if (!HinhAnh.xoaTrenCloud(publicIds)) throw new Exception("Khong the xoa tren cloud");

            return this.them(maSach, hinhAnhSachs, luu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<HinhAnhSach> timTatCa(int maSach) {
        try {
            String sql = String.format("SELECT * FROM hinhAnhSach WHERE maSach = %d", maSach);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<HinhAnhSach> hinhAnhSachs = this.sangThucThes(rs);
            if (hinhAnhSachs != null) {
                return hinhAnhSachs;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean xoa(int ma, boolean luu) {
        try {
            String sql = String.format(
                    "SELECT publicId FROM %s WHERE ma = %d",
                    this.tenBang,
                    ma
            );
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<String> publicIds = new LinkedList<>();
                publicIds.add(rs.getString("publicId"));
                if (HinhAnh.xoaTrenCloud(publicIds)) {
                    return super.xoa(ma, luu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaTrenCloud(int maSach) {
        List<String> pubicIds = new ArrayList<>();
        try {
            String sql = String.format("SELECT publicId FROM hinhAnhSach WHERE maSach = %d", maSach);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) pubicIds.add(rs.getString(1));
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return HinhAnh.xoaTrenCloud(pubicIds);
    }
}
