package com.nhom4web.dao.impl;

import com.nhom4web.dao.IHinhAnhSachDAO;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.utils.HinhAnh;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        if (hinhAnhSach.getMa() > 0) duLieu.put("ma", hinhAnhSach.getMa());
        if (hinhAnhSach.getMaSach() > 0) duLieu.put("maSach", hinhAnhSach.getMaSach());
        if (hinhAnhSach.getDuongDan() != null) duLieu.put("duongDan", hinhAnhSach.getDuongDan());
        if (hinhAnhSach.getPublicId() != null) duLieu.put("publicId", hinhAnhSach.getPublicId());
        return duLieu;
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

    @Override
    public List<HinhAnhSach> timTatCa(int maSach) {
        try {
            String sql = String.format("SELECT * FROM hinhAnhSach WHERE maSach = %d", maSach);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<HinhAnhSach> hinhAnhSachs = this.sangThucThes(rs);
            ps.close();
            return hinhAnhSachs;
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
                HinhAnh.xoaTrenCloud(publicIds);
                return super.xoa(ma, luu);
            }
        } catch (Exception e) {
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
            HinhAnh.xoaTrenCloud(pubicIds);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
