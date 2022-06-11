package com.nhom4web.controller.api;

import com.nhom4web.dao.IHinhAnhSachDAO;
import com.nhom4web.dao.impl.HinhAnhSachDAO;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.utils.HinhAnh;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/api/hinh-anh-sach/*")
@MultipartConfig
public class HinhAnhSachController extends HttpServlet {
    private static final HinhAnhSachDAO DAO = new HinhAnhSachDAO();

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
        int maSach = Integer.parseInt(req.getParameter("maSach"));
        List<HinhAnhSach> hinhAnhSachs = DAO.timTatCa(maSach);
        if (hinhAnhSachs != null) {
            Json.chuyenThanhJson(resp, hinhAnhSachs);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = Integer.parseInt(req.getParameter("maSach"));
        List<HinhAnhSach> hinhAnhSachs = HinhAnh.luuVaoCloud(
                req.getParts().stream().filter(o -> o.getName().equals("anh")).collect(Collectors.toList())
        );
        if (hinhAnhSachs != null && DAO.them(maSach, hinhAnhSachs, true)) {
            Json.chuyenThanhJson(resp, hinhAnhSachs);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
