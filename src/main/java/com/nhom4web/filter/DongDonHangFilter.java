package com.nhom4web.filter;

import com.nhom4web.utils.Json;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "Dong-Don-Hang-Filter")
@MultipartConfig
public class DongDonHangFilter extends AbstractFilter {
    public DongDonHangFilter() {
        this.luat.put("maSach", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("maDonHang", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("soLuong", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);

        this.e.put("maSach." + KHONG_BO_TRONG, "Không được bỏ trống mã sách");
        this.e.put("maSach." + SO_NGUYEN_DUONG, "Mã sách không hợp lệ");
        this.e.put("maDonHang." + KHONG_BO_TRONG, "Không được bỏ trống mã đơn hàng");
        this.e.put("maDonHang." + SO_NGUYEN_DUONG, "Mã đơn hàng không hợp lệ");
        this.e.put("soLuong." + KHONG_BO_TRONG, "Không được bỏ trống số lượng");
        this.e.put("soLuong." + SO_NGUYEN_DUONG, "Số lượng không hợp lệ");
    }

    @Override
    protected boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null && req.getParameter("maDonHang") != null) {
            return true;
        }
        else if (duongDan != null && Pattern.matches("/\\d+", duongDan)) {
            Pattern patternMa = Pattern.compile("\\d+");
            Matcher matcher = patternMa.matcher(duongDan);
            int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
            req.setAttribute("ma", ma > 0 ? ma : null);
            return true;
        }
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return false;
    }

    @Override
    protected boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null || !Pattern.matches("/\\d+", duongDan)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(duongDan);
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        Map<String, String> loi = this.getLoi(req);
        loi.remove("maSach." + KHONG_BO_TRONG);
        loi.remove("maSach." + SO_NGUYEN_DUONG);
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }
}
