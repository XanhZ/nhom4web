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

    // Url: /api/danh-muc/{id}
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (DAO.xoa((Integer) req.getAttribute("ma"))) {
            Json.chuyenThanhJson(resp, true);
            return;
        }
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    // Url: /api/danh-muc
    // Url: /api/danh-muc/
    // Url: /api/danh-muc/{id}
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
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    // Url: /api/danh-muc/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getCharacterEncoding());
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getCharacterEncoding());
        System.out.println(req.getParameter("tenDanhMuc"));
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setTen("Hoàng Tiến Đạt 123");
        System.out.println(danhMuc.getTen());
        if (DAO.them(danhMuc)) {
            Json.chuyenThanhJson(resp, "Thêm thành công");
            return;
        }
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    // Url: /api/danh-muc/{id}
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setMa((Integer) req.getAttribute("ma"));
        danhMuc.setTen(req.getParameter("tenDanhMuc"));
        danhMuc.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));

        if (DAO.capNhat(danhMuc)) {
            Json.chuyenThanhJson(resp, "Cập nhật thành công");
            return;
        }
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
