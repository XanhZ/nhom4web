package com.nhom4web.filter;

import com.nhom4web.model.NguoiDung;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@WebFilter(filterName = "auth")
public class AuthFilter extends AbstractFilter {
    private static final Map<String, List<Pattern>> urlNguoiDungs = new HashMap<>();
    private static final int IS_ADMIN = 1;
    private static final int IS_NGUOI_DUNG = 0;
    private static final String[] REGEX_DUONG_DAN_CONG_KHAIS = {
            "/api/sach",
            "/api/sach/\\d+",
            "/api/danh-muc"
    };

    static {
        urlNguoiDungs.put("DELETE", new ArrayList<>());
        urlNguoiDungs.put("GET", new ArrayList<>());
        urlNguoiDungs.put("POST", new ArrayList<>());
        urlNguoiDungs.put("PUT", new ArrayList<>());

        // DELETE
        // GET
        // POST
        // PUT
    }

    @Override
    protected boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int vaiTro = this.getVaiTro(req, resp);
        if (vaiTro == IS_NGUOI_DUNG) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return vaiTro == IS_ADMIN;
    }

    @Override
    protected boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (this.isDuongDanCongKhai(duongDan)) return true;

        int vaiTro = this.getVaiTro(req, resp);
        if (vaiTro == IS_NGUOI_DUNG) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return vaiTro == IS_ADMIN;
    }

    @Override
    protected boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int vaiTro = this.getVaiTro(req, resp);
        if (vaiTro == IS_NGUOI_DUNG) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return vaiTro == IS_ADMIN;
    }

    @Override
    protected boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int vaiTro = this.getVaiTro(req, resp);
        if (vaiTro == IS_NGUOI_DUNG) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return vaiTro == IS_ADMIN;
    }

    private int getVaiTro(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return -1;
        }
        return nguoiDung.getLoaiNguoiDung();
    }

    private boolean isDuongDanCongKhai(String url) {
        for (String regex : REGEX_DUONG_DAN_CONG_KHAIS) {
            if (Pattern.matches(regex, url)) return true;
        }
        return false;
    }
}
