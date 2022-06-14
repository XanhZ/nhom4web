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

import static com.nhom4web.controller.api.donhang.DonHangController.chiSoTrangThai;

@MultipartConfig
public class DonHangNDFilter extends AbstractFilter {
    public DonHangNDFilter() {
        this.luat.put("diaChi", KHONG_BO_TRONG);
        this.luat.put("maSach", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("soLuong", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);

        this.e.put("diaChi." + KHONG_BO_TRONG, "Không được bỏ trống địa chỉ");
        this.e.put("maSach." + KHONG_BO_TRONG, "Không được bỏ trống mã sách");
        this.e.put("maSach." + SO_NGUYEN_DUONG, "Mã sách không hợp lệ");
        this.e.put("soLuong." + KHONG_BO_TRONG, "Không được bỏ trống số lượng");
        this.e.put("soLuong." + SO_NGUYEN_DUONG, "Số lượng không hợp lệ");
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        if (ma == -1 && chiSoTrangThai(req.getParameter("trangThai")) == -1) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterValues("maSach").length != req.getParameterValues("soLuong").length) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Map<String, String> loi = this.getLoi(req);
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        return true;
    }

    @Override
    public boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!req.getParameter("trangThai").equals("huy")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maDonHang = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("ma", maDonHang > 0 ? maDonHang : null);
        return true;
    }
}