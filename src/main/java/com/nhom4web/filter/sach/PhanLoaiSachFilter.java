package com.nhom4web.filter.sach;

import com.nhom4web.filter.AbstractFilter;
import com.nhom4web.utils.Json;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig
public class PhanLoaiSachFilter extends AbstractFilter {
    public PhanLoaiSachFilter() {
        this.luat.put("maDanhMuc", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);

        this.e.put("maDanhMuc." + KHONG_BO_TRONG, "Không được bỏ trống mã danh mục");
        this.e.put("maDanhMuc." + SO_NGUYEN_DUONG, "Mã danh mục không hợp lệ");
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maPhanLoaiSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        req.setAttribute("maPhanLoaiSach", maPhanLoaiSach > 0 ? maPhanLoaiSach : null);
        return true;
    }

    @Override
    public boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        if (!loi.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maPhanLoaiSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        req.setAttribute("maPhanLoaiSach", maPhanLoaiSach > 0 ? maPhanLoaiSach : null);
        return true;
    }

    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maPhanLoaiSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        req.setAttribute("maPhanLoaiSach", maPhanLoaiSach > 0 ? maPhanLoaiSach : null);
        return true;
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        if (!loi.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        return true;
    }
}
