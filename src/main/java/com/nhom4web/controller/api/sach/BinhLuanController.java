package com.nhom4web.controller.api.sach;

import com.nhom4web.dao.impl.BinhLuanDAO;
import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.BinhLuan;
import com.nhom4web.model.NguoiDung;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class BinhLuanController extends HttpServlet {
    private static final BinhLuanDAO BL_DAO = new BinhLuanDAO();
    private static final SachDAO SACH_DAO = new SachDAO();

    public static void timTatCaTheoMaSach(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = (Integer) req.getAttribute("maSach");

        if (SACH_DAO.tim(maSach) == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Json.chuyenThanhJson(resp, BL_DAO.timBinhLuanTheoMaSach(maSach));
    }

    public static void them(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = (Integer) req.getAttribute("maSach");
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");

        if (SACH_DAO.tim(maSach) == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String noiDungBinhLuan = req.getParameter("noiDung");

        BinhLuan binhLuan = new BinhLuan(
                maSach,
                noiDungBinhLuan,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                nguoiDung
        );

        if (BL_DAO.them(binhLuan, true)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            Json.chuyenThanhJson(resp, binhLuan);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void xoa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("maBinhLuan");
        BinhLuan binhLuan = BL_DAO.tim(ma);

        if (binhLuan == null || binhLuan.getMaSach() != (Integer) req.getAttribute("maSach")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getMa() != binhLuan.getMaNguoiDung()) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Json.chuyenThanhJson(resp, "Người dùng không có quyền xóa bình luận");
            return;
        }

        resp.setStatus(BL_DAO.xoa(ma, true) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void capNhat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("maBinhLuan");
        BinhLuan binhLuan = BL_DAO.tim(ma);

        if (binhLuan == null || binhLuan.getMaSach() != (Integer) req.getAttribute("maSach")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung.getMa() != binhLuan.getMaNguoiDung()) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Json.chuyenThanhJson(resp, "Người dùng không có quyền xóa bình luận");
            return;
        }

        binhLuan.setNoiDung(req.getParameter("noiDung"));
        binhLuan.setThoiGianCapNhat(new Timestamp(System.currentTimeMillis()));
        if (!BL_DAO.capNhat(binhLuan, true)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Json.chuyenThanhJson(resp, binhLuan);
    }
}
