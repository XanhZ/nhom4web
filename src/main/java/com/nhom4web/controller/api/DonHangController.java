package com.nhom4web.controller.api;

import com.nhom4web.dao.impl.DonHangDAO;
import com.nhom4web.model.DonHang;
import com.nhom4web.model.DongDonHang;
import com.nhom4web.model.NguoiDung;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/don-hang/*")
@MultipartConfig
public class DonHangController extends HttpServlet {
    private static final String[] TRANG_THAI = {"dangCho", "xacNhan", "huy"};
    private static final DonHangDAO DAO = new DonHangDAO();

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
        Object maObj = req.getAttribute("ma");

        if (maObj == null) {
            Json.chuyenThanhJson(resp, DAO.layTatCa());
            return;
        }

        DonHang donHang = DAO.tim((Integer) maObj);
        if (donHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Json.chuyenThanhJson(resp, donHang);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] maSachs = req.getParameterValues("maSach");
        String[] soLuongs = req.getParameterValues("soLuong");
        List<DongDonHang> dongDonHangs = new ArrayList<>();
        for (int i = 0; i < maSachs.length; ++i) {
            dongDonHangs.add(new DongDonHang(
                    Integer.parseInt(maSachs[i]),
                    Integer.parseInt(soLuongs[i])
            ));
        }

        DonHang donHang = new DonHang(
                req.getParameter("diaChi"),
                dongDonHangs,
                (NguoiDung) req.getSession().getAttribute("nguoiDung"),
                "dangCho",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );

        if (DAO.them(donHang, true)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            Json.chuyenThanhJson(resp, donHang);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DonHang donHang = DAO.tim((Integer) req.getAttribute("ma"));

        if (donHang == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String trangThaiHienTai = donHang.getTrangThai();
        String trangThaiCapNhat = req.getParameter("trangThai");

        if (this.chiSoTrangThai(trangThaiHienTai) >= this.chiSoTrangThai(trangThaiCapNhat)) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }

        donHang.setTrangThai(trangThaiCapNhat);
        donHang.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));

        if (DAO.capNhat(donHang, true)) {
            Json.chuyenThanhJson(resp, donHang);
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private int chiSoTrangThai(String trangThai) {
        int i = -1;
        while (++i < TRANG_THAI.length && !TRANG_THAI[i].equals(trangThai));
        return i;
    }
}
