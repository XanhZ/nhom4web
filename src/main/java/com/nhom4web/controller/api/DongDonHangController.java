package com.nhom4web.controller.api;

import com.nhom4web.dao.impl.DongDonHangDAO;
import com.nhom4web.model.DongDonHang;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/dong-don-hang/*")
@MultipartConfig
public class DongDonHangController extends HttpServlet {
    private static final DongDonHangDAO DAO = new DongDonHangDAO();

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
        if (req.getParameter("maDonHang") == null) {
            DongDonHang dongDonHang = DAO.tim((Integer) req.getAttribute("ma"));
            if (dongDonHang != null) {
                Json.chuyenThanhJson(resp, dongDonHang);
                return;
            }
        }
        else {
            List<DongDonHang> dongDonHangs = DAO.timTatCa(Integer.parseInt(req.getParameter("maDonHang")));
            if (dongDonHangs != null) {
                Json.chuyenThanhJson(resp, dongDonHangs);
                return;
            }
        }
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DongDonHang dongDonHang = new DongDonHang(
                Integer.parseInt(req.getParameter("maSach")),
                Integer.parseInt(req.getParameter("soLuong"))
        );
        if (DAO.them(Integer.parseInt(req.getParameter("maDonHang")), dongDonHang, true)) {
            Json.chuyenThanhJson(resp, dongDonHang);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DongDonHang dongDonHang = DAO.tim((Integer) req.getAttribute("ma"));
        if (dongDonHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        dongDonHang.setSoLuong(Integer.parseInt(req.getParameter("soLuong")));
        if (DAO.capNhat(dongDonHang, true)) {
            Json.chuyenThanhJson(resp, dongDonHang);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
