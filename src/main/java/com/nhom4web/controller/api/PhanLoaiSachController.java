package com.nhom4web.controller.api;

import com.nhom4web.dao.impl.PhanLoaiSachDAO;
import com.nhom4web.model.PhanLoaiSach;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/api/phan-loai-sach/*")
@MultipartConfig
public class PhanLoaiSachController extends HttpServlet {
    private static final PhanLoaiSachDAO DAO = new PhanLoaiSachDAO();

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
        List<PhanLoaiSach> phanLoaiSachs = DAO.timDanhMucSach(maSach);
        if (phanLoaiSachs != null) {
            Json.chuyenThanhJson(resp, phanLoaiSachs);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = Integer.parseInt(req.getParameter("maSach"));
        if (DAO.them(
                maSach,
                Arrays.stream(req.getParameterMap().get("maDanhMuc")).map(Integer::parseInt).collect(Collectors.toList()),
                true
        )) {
            Json.chuyenThanhJson(resp, true);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
