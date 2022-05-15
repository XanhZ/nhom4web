package com.nhom4web.controller.api;

import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.Sach;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/api/sach/*")
@MultipartConfig
public class SachController extends HttpServlet {
    private static final SachDAO SACH_DAO = new SachDAO();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SACH_DAO.xoa((Integer) req.getAttribute("ma"))) {
            Json.chuyenThanhJson(resp, true);
            return;
        }
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object maObj = req.getAttribute("ma");

        if (maObj == null) {
            Json.chuyenThanhJson(resp, SACH_DAO.layTatCa());
            return;
        }

        Sach sach = SACH_DAO.tim((Integer) maObj);
        if (sach != null) {
            Json.chuyenThanhJson(resp, sach);
            return;
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sach sach = new Sach();
        sach.setTen(req.getParameter("tenSach"));
        sach.setGiaTien(Integer.parseInt(req.getParameter("giaTien")));
        sach.setSoLuongTrongKho(Integer.parseInt(req.getParameter("soLuongTrongKho")));
        Json.chuyenThanhJson(
                resp,
                SACH_DAO.them(
                        sach,
                        req.getParts()
                                .stream()
                                .filter(part -> part.getName().equals("anh"))
                                .collect(Collectors.toList()),
                        Arrays.stream(req.getParameterMap().get("maDanhMuc"))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList())
                ) ? "Thêm thành công" : "Đã xảy ra lỗi"
        );
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
