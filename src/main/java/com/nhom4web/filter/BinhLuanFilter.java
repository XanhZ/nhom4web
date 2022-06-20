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

@WebFilter(filterName = "Binh-Luan-Filter")
@MultipartConfig
public class BinhLuanFilter extends AbstractFilter {
    public BinhLuanFilter() {
        this.luat.put("noiDung", KHONG_BO_TRONG);
        this.e.put("noiDung." + KHONG_BO_TRONG, "Nội dung bình luận không được bỏ trống");
    }

    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maBinhLuan = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        req.setAttribute("maBinhLuan", maBinhLuan > 0 ? maBinhLuan : null);
        return true;
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        return true;
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        if (!loi.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        return true;
    }

    @Override
    public boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        if (!loi.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maBinhLuan = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        req.setAttribute("maBinhLuan", maBinhLuan > 0 ? maBinhLuan : null);
        return true;
    }
}
