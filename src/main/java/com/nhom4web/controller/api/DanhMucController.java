package com.nhom4web.controller.api;

import com.nhom4web.model.DanhMuc;
import com.nhom4web.service.impl.DanhMucService;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet("/api/danh-muc/*")
@MultipartConfig
public class DanhMucController extends HttpServlet {
    private static final DanhMucService SERVICE = new DanhMucService();

    // Url: /api/danh-muc/{id}
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SERVICE.xoaTheoMa((Integer) req.getAttribute("ma"))) {
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
            Json.chuyenThanhJson(resp, SERVICE.layTatCa());
            return;
        }

        DanhMuc danhMuc = SERVICE.timTheoMa((Integer) maObj);
        if (danhMuc != null) {
            Json.chuyenThanhJson(resp, danhMuc);
            return;
        }
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    // Url: /api/danh-muc/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        duLieu.put("tenDanhMuc", req.getParameter("tenDanhMuc"));

        if (SERVICE.them(duLieu) > 0) {
            Json.chuyenThanhJson(resp, "Thêm thành công");
            return;
        }

        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    // Url: /api/danh-muc/{id}
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("ma");
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        duLieu.put("tenDanhMuc", req.getParameter("tenDanhMuc"));

        if (SERVICE.capNhat(duLieu, ma)) {
            Json.chuyenThanhJson(resp, "Cập nhật thành công");
            return;
        }
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
