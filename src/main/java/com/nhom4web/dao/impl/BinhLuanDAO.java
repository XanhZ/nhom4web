package com.nhom4web.dao.impl;

import com.nhom4web.dao.IBinhLuanDAO;
import com.nhom4web.model.BinhLuan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BinhLuanDAO extends AbstractDAO<BinhLuan> implements IBinhLuanDAO {
    public BinhLuanDAO() {
        super("binhLuan");
    }

    @Override
    protected List<BinhLuan> sangThucThes(ResultSet rs) {
        List<BinhLuan> binhLuan = new ArrayList<>();
        try {
            while (rs.next()) binhLuan.add(IBinhLuanDAO.rsSangThucThe(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return binhLuan;
    }

    @Override
    protected BinhLuan sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return IBinhLuanDAO.rsSangThucThe(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected LinkedHashMap<String, Object> sangMap(BinhLuan binhLuan) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        if (binhLuan.getMa() > 0) duLieu.put("ma", binhLuan.getMa());
        if (binhLuan.getMaNguoiDung() > 0) duLieu.put("maNguoiDung", binhLuan.getMaNguoiDung());
        if (binhLuan.getMaSach() > 0) duLieu.put("maSach", binhLuan.getMaSach());
        if (binhLuan.getNoiDung() != null) duLieu.put("noiDung", binhLuan.getNoiDung());
        if (binhLuan.getThoiGianTao() != null) duLieu.put("thoiGianTao", binhLuan.getThoiGianTao());
        if (binhLuan.getThoiGianCapNhat() != null) duLieu.put("thoiGianCapNhat", binhLuan.getThoiGianCapNhat());
        return duLieu;
    }

    public List<BinhLuan> timBinhLuanTheoMaSach(int maSach) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE maSach = %s", this.tenBang, maSach);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            List<BinhLuan> binhLuans = this.sangThucThes(ps.executeQuery());
            for (BinhLuan binhLuan: binhLuans) {
                if (!this.nguoiDung(binhLuan)) return null;
            }
            return binhLuans;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
