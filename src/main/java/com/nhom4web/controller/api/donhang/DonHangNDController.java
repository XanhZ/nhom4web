package com.nhom4web.controller.api.donhang;

import com.nhom4web.dao.impl.DonHangDAO;
import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.DonHang;
import com.nhom4web.model.DongDonHang;
import com.nhom4web.model.GioHang;
import com.nhom4web.model.NguoiDung;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
public class DonHangNDController extends HttpServlet {
    private static final DonHangDAO DAO = new DonHangDAO();

    public static void tim(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DonHang donHang = DAO.tim((Integer) req.getAttribute("ma"));
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (donHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (donHang.getMaNguoiDung() != nguoiDung.getMa()) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        Json.chuyenThanhJson(resp, donHang);
    }

    public static void timTatCa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        Json.chuyenThanhJson(resp, DAO.layTheoNguoiDung(nguoiDung.getMa(), req.getParameter("trangThai")));
    }

    public static void them(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SachDAO sachDAO = new SachDAO();
        List<Integer> maSachs = Arrays.stream(req.getParameterValues("maSach"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> soLuongs = Arrays.stream(req.getParameterValues("soLuong"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<DongDonHang> dongDonHangs = new ArrayList<>();
        for (int i = 0; i < maSachs.size(); ++i) {
            DongDonHang dongDonHang = new DongDonHang(soLuongs.get(i), sachDAO.tim(maSachs.get(i)));
            if (dongDonHang.getSach() == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            if (dongDonHang.getSach().getSoLuongTrongKho() < soLuongs.get(i)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Json.chuyenThanhJson(
                        resp,
                        String.format(
                                "Số lượng sách %s chỉ còn lại %d",
                                dongDonHang.getSach().getTen(),
                                dongDonHang.getSach().getSoLuongTrongKho()
                        )
                );
                return;
            }
            dongDonHangs.add(dongDonHang);
        }

        DonHang donHang = new DonHang(
                req.getParameter("diaChi"),
                dongDonHangs,
                (NguoiDung) req.getSession().getAttribute("nguoiDung"),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );

        if (DAO.them(donHang, true)) {
            GioHang gioHang = (GioHang) req.getSession().getAttribute("gioHang");
            gioHang.loaiBo(maSachs);
            donHang.setNguoiDung(null);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            Json.chuyenThanhJson(resp, donHang);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void capNhat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DonHang donHang = DAO.tim((Integer) req.getAttribute("ma"));
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (donHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (donHang.getMaNguoiDung() != nguoiDung.getMa()) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (donHang.getTrangThai().equals("huy")) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }
        donHang.setTrangThai("huy");
        donHang.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
        if (!DAO.capNhat(donHang, true)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Json.chuyenThanhJson(resp, donHang);
    }
}
