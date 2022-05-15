package com.nhom4web.dao.impl;

import com.cloudinary.Cloudinary;
import com.nhom4web.dao.IHinhAnhSachDAO;
import com.nhom4web.model.HinhAnhSach;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HinhAnhSachDAO extends AbstractDAO<HinhAnhSach> implements IHinhAnhSachDAO {
    private static final String CLOUDINARY_URL = "cloudinary://923346781537818:IXWJ1uydnFdLioVJ8-4G4HcB4Kc@xanhz";
    private static final Cloudinary CLOUDINARY = new Cloudinary(CLOUDINARY_URL);

    public HinhAnhSachDAO() {
        super("hinhAnhSach");
    }

    @Override
    protected HinhAnhSach sangThucThe(ResultSet rs) {
        try {
            HinhAnhSach hinhAnhSach = new HinhAnhSach();
            hinhAnhSach.setMa(rs.getInt(1));
            hinhAnhSach.setDuongDan(rs.getString(2));
            hinhAnhSach.setPublicId(rs.getString(3));
            return hinhAnhSach;
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
    public boolean them(int maSach, List<Part> hinhAnhs) {
        List<HinhAnhSach> hinhAnhSachs = this.luuVaoCloud(hinhAnhs);
        try {
            String[] temp = new String[hinhAnhSachs.size()];
            Arrays.fill(temp, "(?, ?, ?)");
            String sql = String.format(
                    "INSERT INTO hinhAnhSach (maSach, duongDan, publicId) VALUES %s",
                    String.join(", ", temp)
            );
            ketNoi.setAutoCommit(false);
            PreparedStatement stmt = ketNoi.prepareStatement(sql);
            int i = 0;
            for (HinhAnhSach hinhAnhSach : hinhAnhSachs) {
                this.setThamSoTai(stmt, ++i, maSach);
                this.setThamSoTai(stmt, ++i, hinhAnhSach.getDuongDan());
                this.setThamSoTai(stmt, ++i, hinhAnhSach.getPublicId());
            }
            stmt.executeUpdate();
            ketNoi.commit();
            this.dongTruyVan(stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                ketNoi.rollback();
                this.xoaTrenCloud(hinhAnhSachs.stream().map(HinhAnhSach::getPublicId).collect(Collectors.toList()));
            } catch (SQLException er) {
                er.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public List<HinhAnhSach> layTatCa(int maSach) {
        List<HinhAnhSach> hinhAnhSachs = new ArrayList<>();
        try {
            String sql = "SELECT ma, duongDan, publicId FROM hinhAnhSach WHERE maSach = ?";
            PreparedStatement stmt = ketNoi.prepareStatement(sql);
            this.setThamSoTai(stmt, 1, maSach);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) hinhAnhSachs.add(this.sangThucThe(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hinhAnhSachs;
    }

    private String getFileExtension(Part file) {
        Pattern regex = Pattern.compile("filename=\".*\"");
        Matcher matcher = regex.matcher(file.getHeader("content-disposition"));
        if (matcher.find()) {
            String s = matcher.group();
            String tenFile = s.substring(10, s.length() - 1);
            return tenFile.substring(tenFile.lastIndexOf("."));
        }
        return null;
    }

    private List<HinhAnhSach> luuVaoCloud(List<Part> hinhAnhs) {
        List<HinhAnhSach> hinhAnhSachs = new ArrayList<>();
        hinhAnhs.forEach(hinhAnh -> {
            try {
                HinhAnhSach hinhAnhSach = new HinhAnhSach();
                File temp = File.createTempFile("temp", this.getFileExtension(hinhAnh));
                FileUtils.copyInputStreamToFile(hinhAnh.getInputStream(), temp);
                Map res = CLOUDINARY.uploader().upload(temp, null);
                System.out.println();
                hinhAnhSach.setDuongDan((String) res.get("secure_url"));
                hinhAnhSach.setPublicId((String) res.get("public_id"));
                hinhAnhSachs.add(hinhAnhSach);
                boolean xoaTemp = temp.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return hinhAnhSachs;
    }

    private void xoaTrenCloud(List<String> publicIds) {
        try {
            CLOUDINARY.api().deleteResources(publicIds, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaTrenCloud(int maSach) {
        List<String> pubicIds = new ArrayList<>();
        try {
            String sql = "SELECT publicId FROM hinhAnhSach WHERE maSach = ?";
            PreparedStatement stmt = ketNoi.prepareStatement(sql);
            this.setThamSoTai(stmt, 1, maSach);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) pubicIds.add(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.xoaTrenCloud(pubicIds);
    }
}
