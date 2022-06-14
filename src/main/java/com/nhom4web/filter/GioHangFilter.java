package com.nhom4web.filter;

import com.nhom4web.utils.Json;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "Gio-Hang-Filter")
@MultipartConfig
public class GioHangFilter extends AbstractFilter {
    public GioHangFilter() {
        this.luat.put("maSach", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("soLuong", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);

        this.e.put("maSach." + KHONG_BO_TRONG, "Không được bỏ trống mã sách");
        this.e.put("maSach." + SO_NGUYEN_DUONG, "Mã sách không hợp lệ");
        this.e.put("soLuong." + KHONG_BO_TRONG, "Không được bỏ trống số lượng");
        this.e.put("soLuong." + SO_NGUYEN_DUONG, "Số lượng không hợp lệ");
    }

    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getPathInfo() != null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Map<String, String> loi = this.getLoi(req);
        loi.remove("soLuong");
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        return true;
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getPathInfo() != null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        return true;
    }

    @Override
    public boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return this.kiemTraPost(req, resp);
    }
}
