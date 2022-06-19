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
        this.luat.put("sdt", SDT);
        this.luat.put("email", EMAIL);
        this.luat.put("matKhau", MAT_KHAU);

        this.e.put("sdt." + SDT, "Số điện thoại không hợp lệ");
        this.e.put("email." + EMAIL, "Email không hợp lệ");
        this.e.put("matKhau." + MAT_KHAU, "Mật khẩu phải từ 8 ký tự bao gồm chữ hoa, chữ thường, chữ số, ký tự đặc biệt");
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
