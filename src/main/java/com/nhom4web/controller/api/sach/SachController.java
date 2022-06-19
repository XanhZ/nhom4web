package com.nhom4web.controller.api.sach;

import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.model.PhanLoaiSach;
import com.nhom4web.model.Sach;
import com.nhom4web.utils.HinhAnh;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
public class SachController {
    private static final SachDAO DAO = new SachDAO();

    public static void xoa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("ma");
        if (DAO.tim(ma) == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        resp.setStatus(DAO.xoa(ma, true) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void tim(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object maObj = req.getAttribute("ma");
        Sach sach = DAO.tim((Integer) maObj);
        if (sach == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Json.chuyenThanhJson(resp, sach);
    }

    public static void timSachLienQuan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Sach> sachs = DAO.timSachLienQuan((Integer) req.getAttribute("ma"));
        if (sachs != null) {
            Json.chuyenThanhJson(resp, sachs);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void timTatTa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().isEmpty()) {
            Json.chuyenThanhJson(resp, DAO.layTatCa());
            return;
        }
        Json.chuyenThanhJson(resp, DAO.timSachTheo(req.getParameterMap()));
    }

    public static void them(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<HinhAnhSach> hinhAnhSachs = HinhAnh.luuVaoCloud(
                req.getParts()
                        .stream()
                        .filter(part -> part.getName().equals("anh"))
                        .collect(Collectors.toList())
        );

        List<PhanLoaiSach> phanLoaiSachs = new ArrayList<>();
        for (String maDanhMuc : req.getParameterValues("maDanhMuc")) {
            phanLoaiSachs.add(new PhanLoaiSach(Integer.parseInt(maDanhMuc)));
        }

        Sach sach = new Sach(
                req.getParameter("tenSach"),
                Integer.parseInt(req.getParameter("giaTien")),
                Integer.parseInt(req.getParameter("soLuongTrongKho")),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                hinhAnhSachs,
                phanLoaiSachs
        );

        if (DAO.them(sach, true)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            Json.chuyenThanhJson(resp, sach);
            return;
        }

        try {
            HinhAnh.xoaTrenCloud(hinhAnhSachs.stream().map(HinhAnhSach::getPublicId).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void capNhat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sach sach = DAO.tim((Integer) req.getAttribute("ma"));
        if (sach == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        List<String> publicIds = sach.getHinhAnhSachs().stream().map(HinhAnhSach::getPublicId).collect(Collectors.toList());

        List<HinhAnhSach> hinhAnhSachs = HinhAnh.luuVaoCloud(
                req.getParts()
                        .stream()
                        .filter(part -> part.getName().equals("anh"))
                        .collect(Collectors.toList())
        );
        for (HinhAnhSach hinhAnhSach : hinhAnhSachs) {
            hinhAnhSach.setMaSach(sach.getMa());
        }

        List<PhanLoaiSach> phanLoaiSachs = new ArrayList<>();
        for (String maDanhMuc : req.getParameterValues("maDanhMuc")) {
            phanLoaiSachs.add(new PhanLoaiSach(sach.getMa(), Integer.parseInt(maDanhMuc)));
        }

        sach.setTen(req.getParameter("tenSach"));
        sach.setGiaTien(Integer.parseInt(req.getParameter("giaTien")));
        sach.setSoLuongTrongKho(Integer.parseInt(req.getParameter("soLuongTrongKho")));
        sach.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
        sach.setHinhAnhSachs(hinhAnhSachs);
        sach.setPhanLoaiSachs(phanLoaiSachs);

        if (DAO.capNhatToanBo(sach, true)) {
            try {
                HinhAnh.xoaTrenCloud(publicIds);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            Json.chuyenThanhJson(resp, sach);
            return;
        }

        try {
            HinhAnh.xoaTrenCloud(hinhAnhSachs.stream().map(HinhAnhSach::getPublicId).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}