package com.nhom4web.filter;

import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@WebFilter(filterName = "Hinh-Anh-Sach-Filter")
@MultipartConfig
public class HinhAnhSachFilter extends AbstractFilter {
    public HinhAnhSachFilter() {
        this.luat.put("maSach", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);

        this.e.put("maSach." + KHONG_BO_TRONG, "Không được bỏ trống mã sách");
        this.e.put("maSach." + SO_NGUYEN_DUONG, "Mã sách không hợp lệ");
    }

    @Override
    protected boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan != null || req.getParameter("maSach") == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        return true;
    }

    @Override
    protected boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        try {
            String e = this.kiemTraFiles(req.getParts()
                                            .stream()
                                            .filter(o -> o.getName().equals("anh"))
                                            .collect(Collectors.toList())
            );
            if (e != null) {
                loi.put("anh", e);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            loi.put("anh", "File không được vượt quá 4MB");
        }
        if (!loi.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        return true;
    }

    @Override
    protected boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }
}
