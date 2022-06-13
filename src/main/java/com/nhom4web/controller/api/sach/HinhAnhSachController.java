package com.nhom4web.controller.api.sach;

import com.nhom4web.dao.impl.HinhAnhSachDAO;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.utils.HinhAnh;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig
public class HinhAnhSachController {
    private static final HinhAnhSachDAO DAO = new HinhAnhSachDAO();

    public static void xoa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("maHinhAnhSach");
        HinhAnhSach hinhAnhSach = DAO.tim(ma);
        if (hinhAnhSach == null || hinhAnhSach.getMaSach() != (Integer) req.getAttribute("maSach")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        resp.setStatus(DAO.xoa(ma, true) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void tim(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("maHinhAnhSach");
        HinhAnhSach hinhAnhSach = DAO.tim(ma);
        if (hinhAnhSach == null || hinhAnhSach.getMaSach() != (Integer) req.getAttribute("maSach")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Json.chuyenThanhJson(resp, hinhAnhSach);
    }

    public static void timTatCa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = (Integer) req.getAttribute("maSach");
        List<HinhAnhSach> hinhAnhSachs = DAO.timTatCa(maSach);
        if (hinhAnhSachs != null) {
            Json.chuyenThanhJson(resp, hinhAnhSachs);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void them(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HinhAnhSach hinhAnhSach = HinhAnh.luuVaoCloud(req.getPart("anh"));
        hinhAnhSach.setMaSach((Integer) req.getAttribute("maSach"));
        if (DAO.them(hinhAnhSach, true)) {
            Json.chuyenThanhJson(resp, hinhAnhSach);
            return;
        }
        HinhAnh.xoaTrenCloud(hinhAnhSach.getPublicId());
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void capNhat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HinhAnhSach hinhAnhSach = DAO.tim((Integer) req.getAttribute("maHinhAnhSach"));
        if (hinhAnhSach == null || hinhAnhSach.getMaSach() != (Integer) req.getAttribute("maSach")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String publicId = hinhAnhSach.getPublicId();
        HinhAnhSach anh = HinhAnh.luuVaoCloud(req.getPart("anh"));
        hinhAnhSach.setDuongDan(anh.getDuongDan());
        hinhAnhSach.setPublicId(anh.getPublicId());
        if (DAO.capNhat(hinhAnhSach, true)) {
            HinhAnh.xoaTrenCloud(publicId);
            Json.chuyenThanhJson(resp, hinhAnhSach);
            return;
        }
        HinhAnh.xoaTrenCloud(anh.getPublicId());
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
