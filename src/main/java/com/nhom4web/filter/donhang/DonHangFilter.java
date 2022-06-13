package com.nhom4web.filter.donhang;

import com.nhom4web.filter.AbstractFilter;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig
public class DonHangFilter extends AbstractFilter {
    public DonHangFilter() {
        this.luat.put("diaChi", KHONG_BO_TRONG);
        this.luat.put("maSach", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("soLuong", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("trangThai", KHONG_BO_TRONG);

        this.e.put("diaChi." + KHONG_BO_TRONG, "Không được bỏ trống địa chỉ");
        this.e.put("maSach." + KHONG_BO_TRONG, "Không được bỏ trống mã sách");
        this.e.put("maSach." + SO_NGUYEN_DUONG, "Mã sách không hợp lệ");
        this.e.put("soLuong." + KHONG_BO_TRONG, "Không được bỏ trống số lượng");
        this.e.put("soLuong." + SO_NGUYEN_DUONG, "Số lượng không hợp lệ");
        this.e.put("trangThai." + KHONG_BO_TRONG, "Không được bỏ trống trạng thái đơn hàng");
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        loi.remove("trangThai");
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        return true;
    }

    @Override
    public boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        Map<String, String> loi = this.getLoi(req);
        loi.remove("diaChi");
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }
}
