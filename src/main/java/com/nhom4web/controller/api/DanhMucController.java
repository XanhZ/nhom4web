package com.nhom4web.controller.api;

import com.nhom4web.dao.impl.DanhMucDAO;
import com.nhom4web.model.DanhMuc;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/api/danh-muc/*")
@MultipartConfig
public class DanhMucController extends HttpServlet {
    private static final DanhMucDAO DAO = new DanhMucDAO();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("ma");
        if (DAO.tim(ma) == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        resp.setStatus(DAO.xoa(ma, true) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object maObj = req.getAttribute("ma");

        if (maObj == null) {
            Json.chuyenThanhJson(resp, DAO.layTatCa());
            return;
        }

        DanhMuc danhMuc = DAO.tim((Integer) maObj);
        if (danhMuc != null) {
            Json.chuyenThanhJson(resp, danhMuc);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DanhMuc danhMuc = new DanhMuc(
                req.getParameter("tenDanhMuc"),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        if (DAO.them(danhMuc, true)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            Json.chuyenThanhJson(resp, danhMuc);
        }
        else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DanhMuc danhMuc = DAO.tim((Integer) req.getAttribute("ma"));
        if (danhMuc == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        danhMuc.setTen(req.getParameter("ten").trim());
        danhMuc.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
        if (DAO.capNhat(danhMuc, true)) {
            Json.chuyenThanhJson(resp, danhMuc);
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
