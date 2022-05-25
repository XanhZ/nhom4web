package com.nhom4web.dao.impl;

import com.cloudinary.Cloudinary;
import com.nhom4web.dao.IHinhAnhSachDAO;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.model.Sach;
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
    protected List<HinhAnhSach> sangThucThes(ResultSet rs) {
        List<HinhAnhSach> hinhAnhSachs = new ArrayList<>();
        try {
            while (rs.next()) hinhAnhSachs.add(this.rsSangThucThe(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hinhAnhSachs;
    }

    @Override
    protected HinhAnhSach sangThucThe(ResultSet rs) {
        try {
            if (rs.next()) return this.rsSangThucThe(rs);
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

            PreparedStatement ps = ketNoi.prepareStatement(sql);
            int i = 0;
            for (HinhAnhSach hinhAnhSach : hinhAnhSachs) {
                this.setThamSoTai(ps, ++i, maSach);
                this.setThamSoTai(ps, ++i, hinhAnhSach.getDuongDan());
                this.setThamSoTai(ps, ++i, hinhAnhSach.getPublicId());
            }

            ps.executeUpdate();
            ketNoi.commit();
            ps.close();
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
    public boolean capNhat(int maSach, List<Part> hinhAnhs) {
        if (this.xoaTrenCloud(maSach)) {
            try {
                String sql = String.format("DELETE FROM %s WHERE maSach = ?", this.tenBang);
                PreparedStatement ps1 = ketNoi.prepareStatement(sql);
                this.setThamSoTruyVan(ps1, maSach);
                ps1.executeUpdate();
                ps1.close();

                List<HinhAnhSach> hinhAnhSachs = this.luuVaoCloud(hinhAnhs);
                String[] temp = new String[hinhAnhSachs.size()];
                Arrays.fill(temp, "(?, ?, ?)");
                sql = String.format(
                        "INSERT INTO %s (maSach, duongDan, publicId) VALUES %s",
                        this.tenBang,
                        String.join(", ", temp)
                );

                PreparedStatement ps2 = ketNoi.prepareStatement(sql);
                int i = 0;
                for (HinhAnhSach hinhAnhSach : hinhAnhSachs) {
                    this.setThamSoTai(ps2, ++i, maSach);
                    this.setThamSoTai(ps2, ++i, hinhAnhSach.getDuongDan());
                    this.setThamSoTai(ps2, ++i, hinhAnhSach.getPublicId());
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
        }
        return false;
    }

    @Override
    public boolean timTatCa(Sach sach) {
        try {
            String sql = "SELECT ma, duongDan, publicId FROM hinhAnhSach WHERE maSach = ?";
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            this.setThamSoTai(ps, 1, sach.getMa());
            sach.setHinhAnhSachs(this.sangThucThes(ps.executeQuery()));
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    private boolean xoaTrenCloud(List<String> publicIds) {
        try {
            CLOUDINARY.api().deleteResources(publicIds, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaTrenCloud(int maSach) {
        List<String> pubicIds = new ArrayList<>();
        try {
            String sql = "SELECT publicId FROM hinhAnhSach WHERE maSach = ?";
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            this.setThamSoTai(ps, 1, maSach);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) pubicIds.add(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.xoaTrenCloud(pubicIds);
    }
}
