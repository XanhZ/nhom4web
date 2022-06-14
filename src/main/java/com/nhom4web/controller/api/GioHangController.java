package com.nhom4web.controller.api;

import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.GioHang;
import com.nhom4web.model.Sach;
import com.nhom4web.model.SachTrongGioHang;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/api/gio-hang")
@MultipartConfig
public class GioHangController extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GioHang gioHang = (GioHang) req.getSession().getAttribute("gioHang");
        gioHang.loaiBo(Arrays.stream(req.getParameterValues("maSach"))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList())
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GioHang gioHang = (GioHang) req.getSession().getAttribute("gioHang");
        Json.chuyenThanhJson(resp, gioHang.getSachs());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sach sach = new SachDAO().tim(Integer.parseInt(req.getParameter("maSach")));
        int soLuong = Integer.parseInt(req.getParameter("soLuong"));
        if (sach == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, "Không tồn tại sách");
            return;
        }
        if (soLuong > sach.getSoLuongTrongKho()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, String.format("Số lượng sách %s chỉ còn %d", sach.getTen(), sach.getSoLuongTrongKho()));
            return;
        }
        SachTrongGioHang o = new SachTrongGioHang(sach, soLuong);
        GioHang gioHang = (GioHang)req.getSession().getAttribute("gioHang");
        if (!gioHang.themSach(o)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, "Sách đã có trong giỏ hàng");
            return;
        }
        Json.chuyenThanhJson(resp, o);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = Integer.parseInt(req.getParameter("maSach"));
        int soLuong = Integer.parseInt(req.getParameter("soLuong"));
        GioHang gioHang = (GioHang)req.getSession().getAttribute("gioHang");
        SachTrongGioHang o = gioHang.tim(maSach);
        if (o == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, "Không tồn tại sách trong giỏ hàng");
            return;
        }
        if (soLuong > o.getSach().getSoLuongTrongKho()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, String.format("Số lượng sách %s chỉ còn %d", o.getSach().getTen(), o.getSach().getSoLuongTrongKho()));
            return;
        }
        o.setSoLuong(soLuong);
        Json.chuyenThanhJson(resp, o);
    }
}