package com.nhom4web.filter.donhang;

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
public class DongDonHangFilter extends AbstractFilter {
    public DongDonHangFilter() {
        this.luat.put("maSach", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("soLuong", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);

        this.e.put("maSach." + KHONG_BO_TRONG, "Không được bỏ trống mã sách");
        this.e.put("maSach." + SO_NGUYEN_DUONG, "Mã sách không hợp lệ");
        this.e.put("soLuong." + KHONG_BO_TRONG, "Không được bỏ trống số lượng");
        this.e.put("soLuong." + SO_NGUYEN_DUONG, "Số lượng không hợp lệ");
    }

    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maDonHang = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maDongDonHang = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maDonHang", maDonHang > 0 ? maDonHang : null);
        req.setAttribute("maDongDonHang", maDongDonHang > 0 ? maDongDonHang : null);
        return true;
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maDonHang = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maDonHang", maDonHang > 0 ? maDonHang : null);
        return true;
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maDonHang = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maDongDonHang = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maDonHang", maDonHang > 0 ? maDonHang : null);
        req.setAttribute("maDongDonHang", maDongDonHang > 0 ? maDongDonHang : null);
        return true;
    }

    @Override
    public boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        loi.remove("maSach");
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maDonHang = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maDongDonHang = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maDonHang", maDonHang > 0 ? maDonHang : null);
        req.setAttribute("maDongDonHang", maDongDonHang > 0 ? maDongDonHang : null);
        return true;
    }
}