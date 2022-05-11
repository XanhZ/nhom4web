package com.nhom4web.controller.view;

import com.nhom4web.model.NguoiDung;
import com.nhom4web.model.ThongTinDangNhap;
import com.nhom4web.service.impl.DangNhapService;
import com.nhom4web.service.impl.NguoiDungService;
import com.nhom4web.utils.Hashing;
import com.nhom4web.utils.Json;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;

@MultipartConfig
@WebServlet("/api/dang-nhap")
public class DangNhapController extends HttpServlet {
    private static final DangNhapService DANG_NHAP_SERVICE = new DangNhapService();
    private static final NguoiDungService NGUOI_DUNG_SERVICE = new NguoiDungService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedHashMap<String, Object> taiKhoanMatKhau = new LinkedHashMap<>();

        try {
            taiKhoanMatKhau.put("tenDangNhap", req.getParameter("tenDangNhap"));
            String matKhauMaHoa = Hashing.maHoaSHA256(req.getParameter("matKhau"));
            taiKhoanMatKhau.put("matKhau", matKhauMaHoa);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        ThongTinDangNhap thongTinDangNhap = DANG_NHAP_SERVICE.timTheoTaiKhoanMatKhau(taiKhoanMatKhau);
        if (thongTinDangNhap != null) {
            Json.chuyenThanhJson(resp, "Đăng nhập thành công");

            NguoiDung nguoiDung = NGUOI_DUNG_SERVICE.timTheoMa(thongTinDangNhap.getMaNguoiDung());

            // Tao session dang nhap
            HttpSession sessionDangNhap = req.getSession();
            sessionDangNhap.setAttribute("nguoiDung", nguoiDung);

            /*
             * Phan quyen
             * 1. Nguoi dung co loaiNguoiDung = 0 => User => Redirect den trang chu
             * 2. Nguoi dung co loaiNguoiDung = 1 => Admin => Redirect den trang admin
             */
            if (nguoiDung.getLoaiNguoiDung() == 0) {
                req.getRequestDispatcher("views/homepage.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("views/admin.jsp").forward(req, resp);
            }
            return;
        } else {
            Json.chuyenThanhJson(resp, "Đăng nhập thất bại");
        }

        RequestDispatcher rd = req.getRequestDispatcher("views/login.jsp");
        rd.forward(req, resp);
    }
}
