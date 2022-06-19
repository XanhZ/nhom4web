package com.nhom4web.filter.sach;

import com.nhom4web.filter.AbstractFilter;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@MultipartConfig(maxFileSize = 1024 * 1024 * 2, // 2 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class SachFilter extends AbstractFilter {
    public SachFilter() {
        this.luat.put("tenSach", KHONG_BO_TRONG);
        this.luat.put("giaTien", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("soLuongTrongKho", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);

        this.e.put("tenSach." + KHONG_BO_TRONG, "Tên sách không được bỏ trống");
        this.e.put("giaTien." + KHONG_BO_TRONG, "Giá tiền không được bỏ trống");
        this.e.put("giaTien." + SO_NGUYEN_DUONG, "Giá tiền phải là số nguyên dương");
        this.e.put("soLuongTrongKho." + KHONG_BO_TRONG, "Số lượng không được bỏ trống");
        this.e.put("soLuongTrongKho." + SO_NGUYEN_DUONG, "Số lượng phải là số nguyên dương");
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> loi = this.getLoi(req);
        try {
            Collection<Part> files = req.getParts()
                                        .stream()
                                        .filter(part -> part.getName().equals("anh"))
                                        .collect(Collectors.toList());
            String er = this.kiemTraFiles(files);
            if (er != null) {
                loi.put("anh", er);
            }
        } catch (IllegalStateException | ServletException e) {
            loi.put("anh", "Hình ảnh không được quá 4 MB");
            e.printStackTrace();
        }
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
        try {
            Collection<Part> files = req.getParts()
                    .stream()
                    .filter(part -> part.getName().equals("anh"))
                    .collect(Collectors.toList());
            if (files.size() != 0) {
                String er = this.kiemTraFiles(files);
                if (er != null) {
                    loi.put("anh", er);
                }
            }
        } catch (IllegalStateException | ServletException e) {
            loi.put("anh", "Hình ảnh không được quá 4 MB");
            e.printStackTrace();
        }
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }

    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(req.getRequestURI());
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("ma", ma > 0 ? ma : null);
        return ma != -1 || this.kiemTraThuocTinhLocs(req);
    }

    private boolean kiemTraThuocTinhLocs(HttpServletRequest req) {
        for (Map.Entry<String, String[]> thuocTinh : req.getParameterMap().entrySet()) {
            switch (thuocTinh.getKey()) {
                case "tenSach": {
                    Pattern kiTuDacBiet = Pattern.compile ("[!@#$%&*()_+=/\\\\|<>?{}\\[\\]'\"~-]");
                    if (kiTuDacBiet.matcher(thuocTinh.getValue()[0]).find()) return false;
                    break;
                }
                case "giaTu":
                case "giaDen":
                case "gioiHan": {
                    if (!Pattern.matches(REGEX_SO_NGUYEN_DUONG, thuocTinh.getValue()[0])) return false;
                    break;
                }
                case "danhMuc": {
                    for (String maDanhMuc : thuocTinh.getValue()) {
                        if (!Pattern.matches(REGEX_SO_NGUYEN_DUONG, maDanhMuc)) return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
}
