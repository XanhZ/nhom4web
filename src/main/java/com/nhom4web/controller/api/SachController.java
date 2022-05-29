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
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/api/sach/*")
@MultipartConfig
public class SachController extends HttpServlet {
    private static final SachDAO DAO = new SachDAO();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(
                DAO.xoa((Integer) req.getAttribute("ma"), true) ?
                        HttpServletResponse.SC_OK :
                        HttpServletResponse.SC_BAD_REQUEST
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object maObj = req.getAttribute("ma");

        if (maObj == null) {
            Json.chuyenThanhJson(resp, DAO.layTatCa());
            return;
        }

        Sach sach = DAO.tim((Integer) maObj);
        if (sach == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Json.chuyenThanhJson(resp, sach);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sach sach = new Sach(
                req.getParameter("tenSach"),
                Integer.parseInt(req.getParameter("giaTien")),
                Integer.parseInt(req.getParameter("soLuongTrongKho")),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        if (DAO.them(
                sach,
                req.getParts()
                        .stream()
                        .filter(part -> part.getName().equals("anh"))
                        .collect(Collectors.toList()),
                Arrays.stream(req.getParameterMap().get("maDanhMuc"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        )) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            Json.chuyenThanhJson(resp, sach);
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sach sach = DAO.tim((Integer) req.getAttribute("ma"));
        if (sach == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sach.setTen(req.getParameter("tenSach"));
        sach.setGiaTien(Integer.parseInt(req.getParameter("giaTien")));
        sach.setSoLuongTrongKho(Integer.parseInt(req.getParameter("soLuongTrongKho")));
        sach.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
        if (DAO.capNhat(
                sach,
                req.getParts().stream().filter(part -> part.getName().equals("anh")).collect(Collectors.toList()),
                Arrays.stream(req.getParameterMap().get("maDanhMuc")).map(Integer::parseInt).collect(Collectors.toList())
        )) {
            Json.chuyenThanhJson(resp, sach);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
