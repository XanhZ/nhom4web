package com.nhom4web.controller.api.donhang;

import com.nhom4web.dao.impl.DonHangDAO;
import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.DonHang;
import com.nhom4web.model.DongDonHang;
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
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.nhom4web.filter.AuthFilter.IS_ADMIN;

@MultipartConfig
public class DonHangController extends HttpServlet {
    private static final String[] TRANG_THAI = {"dangCho", "xacNhan", "huy"};
    private static final DonHangDAO DAO = new DonHangDAO();

    public static void xoa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("ma");
        DonHang donHang = DAO.tim(ma);
        if (donHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getLoaiNguoiDung() == IS_ADMIN || nguoiDung.getMa() == donHang.getMaNguoiDung()) {
            resp.setStatus(DAO.xoa(ma, true) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    public static void tim(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DonHang donHang = DAO.tim((Integer) req.getAttribute("ma"));
        if (donHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getLoaiNguoiDung() == IS_ADMIN || nguoiDung.getMa() == donHang.getMaNguoiDung()) {
            Json.chuyenThanhJson(resp, donHang);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    public static void timTatCa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trangThai = req.getParameter("trangThai");
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getLoaiNguoiDung() == IS_ADMIN) {
            Json.chuyenThanhJson(resp, DAO.layTatCa(trangThai));
            return;
        }
        Json.chuyenThanhJson(resp, DAO.layTheoNguoiDung(nguoiDung.getMa(), trangThai));
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
            HashSet<DongDonHang> s = (HashSet<DongDonHang>) req.getSession().getAttribute("gioHang");
            s.removeIf(o -> maSachs.contains(o.getSach().getMa()));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            Json.chuyenThanhJson(resp, donHang);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void capNhat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DonHang donHang = DAO.tim((Integer) req.getAttribute("ma"));

        if (donHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        int chiSoHienTai = DonHangController.chiSoTrangThai(donHang.getTrangThai());
        int chiSoCapNhat = DonHangController.chiSoTrangThai(req.getParameter("trangThai"));

        if (chiSoCapNhat == -1 || chiSoHienTai >= chiSoCapNhat) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }

        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getLoaiNguoiDung() == 0 && chiSoCapNhat != 2) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        donHang.setTrangThai(TRANG_THAI[chiSoCapNhat]);
        donHang.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));

        if (DAO.capNhat(donHang, true)) {
            Json.chuyenThanhJson(resp, donHang);
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private static int chiSoTrangThai(String trangThai) {
        int i = 0;
        while (i < TRANG_THAI.length) {
            if (TRANG_THAI[i++].equals(trangThai)) return i;
        }
        return -1;
    }
}
