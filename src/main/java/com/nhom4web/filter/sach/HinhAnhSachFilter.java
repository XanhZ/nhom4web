package com.nhom4web.filter.sach;

import com.nhom4web.filter.AbstractFilter;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig(maxFileSize = 1024 * 1024 * 4, // 4 MB
        maxRequestSize = 1024 * 1024 * 5 // 5 MB
)
public class HinhAnhSachFilter extends AbstractFilter {
    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maHinhAnhSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        req.setAttribute("maHinhAnhSach", maHinhAnhSach > 0 ? maHinhAnhSach : null);
        return true;
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = new HashMap<>();
        try {
            String e = this.kiemTraFile(req.getPart("anh"));
            if (e != null) loi.put("anh", e);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            loi.put("anh", "File không được vượt quá 4MB");
        }
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
        Map<String, String> loi = new HashMap<>();
        try {
            String e = this.kiemTraFile(req.getPart("anh"));
            if (e != null) loi.put("anh", e);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            loi.put("anh", "File không được vượt quá 4MB");
        }
        if (!loi.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maHinhAnhSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        req.setAttribute("maHinhAnhSach", maHinhAnhSach > 0 ? maHinhAnhSach : null);
        return true;
    }

    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int maSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        int maHinhAnhSach = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("maSach", maSach > 0 ? maSach : null);
        req.setAttribute("maHinhAnhSach", maHinhAnhSach > 0 ? maHinhAnhSach : null);
        return true;
    }
}
