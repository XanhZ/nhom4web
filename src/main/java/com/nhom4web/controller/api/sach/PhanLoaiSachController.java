package com.nhom4web.controller.api.sach;

import com.nhom4web.dao.impl.PhanLoaiSachDAO;
import com.nhom4web.model.PhanLoaiSach;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig
public class PhanLoaiSachController {
    private static final PhanLoaiSachDAO DAO = new PhanLoaiSachDAO();

    public static void xoa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("maPhanLoaiSach");
        PhanLoaiSach phanLoaiSach = DAO.tim(ma);
        if (phanLoaiSach == null || phanLoaiSach.getMaSach() != (Integer) req.getAttribute("maSach")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        resp.setStatus(DAO.xoa(ma, true) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void tim(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("maPhanLoaiSach");
        PhanLoaiSach phanLoaiSach = DAO.tim(ma);
        if (phanLoaiSach == null || phanLoaiSach.getMaSach() != (Integer) req.getAttribute("maSach")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Json.chuyenThanhJson(resp, phanLoaiSach);
    }

    public static void timTatCa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = (Integer) req.getAttribute("maSach");
        List<PhanLoaiSach> phanLoaiSachs = DAO.timDanhMucSach(maSach);
        if (phanLoaiSachs != null) {
            Json.chuyenThanhJson(resp, phanLoaiSachs);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void them(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PhanLoaiSach phanLoaiSach = new PhanLoaiSach(
                (Integer) req.getAttribute("maSach"),
                Integer.parseInt(req.getParameter("maDanhMuc"))
        );
        if (DAO.them(phanLoaiSach, true)) {
            Json.chuyenThanhJson(resp, phanLoaiSach);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void capNhat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("maPhanLoaiSach");
        PhanLoaiSach phanLoaiSach = DAO.tim(ma);
        if (phanLoaiSach == null || phanLoaiSach.getMaSach() != (Integer) req.getAttribute("maSach")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        phanLoaiSach.setMaDanhMuc(Integer.parseInt(req.getParameter("maDanhMuc")));
        if (DAO.capNhat(phanLoaiSach, true)) {
            Json.chuyenThanhJson(resp, phanLoaiSach);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
