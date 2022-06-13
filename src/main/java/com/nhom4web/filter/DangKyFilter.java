package com.nhom4web.filter;

import com.nhom4web.utils.Json;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter("/api/dang-ky")
@MultipartConfig
public class DangKyFilter extends AbstractFilter {
    public DangKyFilter() {
        this.luat.put("sdt", KHONG_BO_TRONG + "/" + SDT);
        this.luat.put("email", KHONG_BO_TRONG + "/" + EMAIL);

        this.e.put("sdt.khong-bo-trong", "Không được bỏ trống");
        this.e.put("sdt.sdt", "Số điện thoại không hợp lệ");
        this.e.put("email.khong-bo-trong", "Không được bỏ trống");
        this.e.put("email.email", "Email không hợp lệ");
    }

    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }
}
