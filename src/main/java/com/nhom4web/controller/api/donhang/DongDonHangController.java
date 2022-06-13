package com.nhom4web.controller.api.donhang;

import com.nhom4web.dao.impl.DongDonHangDAO;
import com.nhom4web.dao.impl.SachDAO;
import com.nhom4web.model.DongDonHang;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig
public class DongDonHangController extends HttpServlet {
    private static final DongDonHangDAO DAO = new DongDonHangDAO();

    public static void xoa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ma = (Integer) req.getAttribute("ma");
        if (DAO.tim(ma) == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        resp.setStatus(DAO.xoa(ma, true) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void tim(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    public static void timTatCa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    public static void them(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DongDonHang dongDonHang = new DongDonHang(
                Integer.parseInt(req.getParameter("soLuong")),
                new SachDAO().tim(Integer.parseInt(req.getParameter("maSach")))
        );
        if (dongDonHang.getSach() == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (dongDonHang.getSoLuong() > dongDonHang.getSach().getSoLuongTrongKho()) {
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
        if (DAO.them(Integer.parseInt(req.getParameter("maDonHang")), dongDonHang, true)) {
            Json.chuyenThanhJson(resp, dongDonHang);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static void capNhat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
