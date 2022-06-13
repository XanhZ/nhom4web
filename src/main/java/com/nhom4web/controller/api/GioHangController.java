package com.nhom4web.controller.api;

import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.DongDonHang;
import com.nhom4web.model.Sach;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
public class GioHangController extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Integer> maSachs = Arrays.stream(req.getParameterValues("maSach"))
                                        .map(Integer::parseInt)
                                        .collect(Collectors.toList());
        HashSet<DongDonHang> dongDonHangs = (HashSet<DongDonHang>) req.getSession().getAttribute("gioHang");
        dongDonHangs.removeIf(dongDonHang -> maSachs.contains(dongDonHang.getSach().getMa()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Json.chuyenThanhJson(resp, req.getSession().getAttribute("gioHang"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = Integer.parseInt(req.getParameter("maSach"));
        int soLuong = Integer.parseInt(req.getParameter("soLuong"));
        DongDonHang dongDonHang = new DongDonHang();
        Sach sach = new SachDAO().tim(maSach);
        if (soLuong > sach.getSoLuongTrongKho()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, "Số lượng sách trong kho chỉ còn " + sach.getSoLuongTrongKho());
            return;
        }
        dongDonHang.setSach(sach);
        dongDonHang.setSoLuong(soLuong);
        dongDonHang.setDonGia(sach.getGiaTien());
        HashSet<DongDonHang> gioHang = (HashSet<DongDonHang>)req.getSession().getAttribute("gioHang");
        if (gioHang.contains(dongDonHang)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, "Sách đã ở trong giỏ hàng");
            return;
        }
        gioHang.add(dongDonHang);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = Integer.parseInt(req.getParameter("maSach"));
        int soLuong = Integer.parseInt(req.getParameter("soLuong"));
        HashSet<DongDonHang> gioHang = (HashSet<DongDonHang>)req.getSession().getAttribute("gioHang");
        DongDonHang t = gioHang.stream().filter(o -> o.getSach().getMa() == maSach).findFirst().orElse(null);
        if (t == null) {
            t = new DongDonHang();
            Sach sach = new SachDAO().tim(maSach);
            t.setSach(sach);
            t.setDonGia(sach.getGiaTien());
            gioHang.add(t);
        }
        if (soLuong > t.getSach().getSoLuongTrongKho()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, "Số lượng sách trong kho chỉ còn " + t.getSach().getSoLuongTrongKho());
            return;
        }
        t.setSoLuong(soLuong);
    }
}