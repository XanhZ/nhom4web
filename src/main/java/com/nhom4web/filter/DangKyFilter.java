package com.nhom4web.filter;

import com.nhom4web.utils.Json;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter("/api/dang-ky/")
@MultipartConfig
public class DangKyFilter extends AbstractFilter {
    public DangKyFilter() {
        this.luat.put("sdt", SDT);
        this.luat.put("email", EMAIL);

        this.e.put("sdt.khong-hop-le", "Số điện thoại không hợp lệ");
        this.e.put("email.khong-hop-le", "Số điện thoại không hợp lệ");
    }

    @Override
    protected boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return false;
    }

    @Override
    protected boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return false;
    }

    @Override
    protected boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }

        return true;
    }

    @Override
    protected boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return false;
    }
}
