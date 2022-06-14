package com.nhom4web.filter.donhang;

import com.nhom4web.filter.AbstractFilter;
import com.nhom4web.model.NguoiDung;
import com.nhom4web.utils.Json;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nhom4web.controller.api.donhang.DonHangController.chiSoTrangThai;
import static com.nhom4web.filter.AuthFilter.IS_ADMIN;

@MultipartConfig
public class DonHangFilter extends AbstractFilter {
    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getLoaiNguoiDung() != IS_ADMIN) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return super.kiemTraDelete(req, resp);
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getLoaiNguoiDung() != IS_ADMIN) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return super.kiemTraGet(req, resp);
    }

    @Override
    public boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getLoaiNguoiDung() != IS_ADMIN) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        Map<String, String> loi = this.getLoi(req);
        if (chiSoTrangThai(req.getParameter("trangThai")) == -1) {
            loi.put("trangThai", "Trạng thái đơn hàng không hợp lệ");
        }
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }
}
