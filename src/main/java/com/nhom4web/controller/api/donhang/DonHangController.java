package com.nhom4web.controller.api.donhang;

import com.nhom4web.dao.impl.DonHangDAO;
import com.nhom4web.model.DonHang;
import com.nhom4web.model.NguoiDung;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

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
        resp.setStatus(DAO.xoa(ma, true) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void tim(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DonHang donHang = DAO.tim((Integer) req.getAttribute("ma"));
        if (donHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Json.chuyenThanhJson(resp, donHang);
    }

    public static void timTatCa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trangThai = req.getParameter("trangThai");
        Json.chuyenThanhJson(resp, trangThai == null ? DAO.layTatCa() : DAO.layTatCa(trangThai));
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

    public static int chiSoTrangThai(String trangThai) {
        int i = -1;
        while (++i < TRANG_THAI.length) {
            if (TRANG_THAI[i].equals(trangThai)) return i;
        }
        return -1;
    }
}
