package com.nhom4web.controller.api;

import com.nhom4web.model.Example;
import com.nhom4web.service.impl.ExampleService;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet("/api/examples/*")
public class ExampleController extends HttpServlet {
    private static ExampleService service = new ExampleService();

    // Uri: /api/examples/{id}
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        int ma = Integer.parseInt(path.split("/")[1]);
        if (service.xoaTheoMa(ma)) {
            Json.chuyenThanhJson(resp, "Xóa thành công");
            return;
        }
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    // Uri: /api/examples/
    // Uri: /api/examples/{id}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        // Uri: /api/examples or /api/examples/
        if (path == null || path == "/") {
            Json.chuyenThanhJson(resp, service.layTatCa());
            return;
        }

        // Uri: /api/examples/{id}
        int ma = Integer.parseInt(path.split("/")[1]);
        Example ex = service.timTheoMa(ma);
        if (ex == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Json.chuyenThanhJson(resp, service.timTheoMa(ma));
    }

    // Uri: /api/examples/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        duLieu.put("hoTen", req.getParameter("hoTen"));
        duLieu.put("tuoi", Integer.parseInt(req.getParameter("tuoi")));
        if (service.them(duLieu) > 0) {
            Json.chuyenThanhJson(resp, "Thêm thành công");
        }
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    // Uri: /api/examples/{id}
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = Integer.parseInt(req.getParameter("ma"));
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        duLieu.put("hoTen", req.getParameter("hoTen"));
        duLieu.put("tuoi", Integer.parseInt(req.getParameter("tuoi")));
        if (service.capNhat(duLieu, ma)) {
            Json.chuyenThanhJson(resp, "Cập nhật thành công");
        }
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
