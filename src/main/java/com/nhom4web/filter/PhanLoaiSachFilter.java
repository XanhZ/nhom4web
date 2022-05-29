package com.nhom4web.filter;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Phan-Loai-Sach-Filter")
@MultipartConfig
public class PhanLoaiSachFilter extends AbstractFilter {
    public PhanLoaiSachFilter() {
        this.luat.put("maSach", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);
        this.luat.put("maDanhMuc", KHONG_BO_TRONG + "/" + SO_NGUYEN_DUONG);

        this.e.put("maSach." + KHONG_BO_TRONG, "Không được bỏ trống mã sách");
        this.e.put("maSach." + SO_NGUYEN_DUONG, "Mã sách không hợp lệ");
        this.e.put("maDanhMuc." + KHONG_BO_TRONG, "Không được bỏ trống mã danh mục");
        this.e.put("maDanhMuc." + SO_NGUYEN_DUONG, "Mã danh mục không hợp lệ");
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
    protected boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }
}
