package com.nhom4web.controller.view;

import com.nhom4web.dao.impl.NguoiDungDAO;
import com.nhom4web.dao.impl.ThongTinDangNhapDAO;
import com.nhom4web.utils.Hashing;
import com.nhom4web.utils.Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;

@MultipartConfig
@WebServlet("/api/dang-ky")
public class DangKyController extends HttpServlet {
    private static final NguoiDungDAO ND_DAO = new NguoiDungDAO();
    private static final ThongTinDangNhapDAO TTDN_DAO = new ThongTinDangNhapDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thong tin nguoi dung
        LinkedHashMap<String, Object> thongTinNguoiDung = new LinkedHashMap<>();

        thongTinNguoiDung.put("sdt", req.getParameter("sdt"));
        thongTinNguoiDung.put("email", req.getParameter("email"));
        thongTinNguoiDung.put("ten", req.getParameter("ten"));
        thongTinNguoiDung.put("loaiNguoiDung", Integer.parseInt(req.getParameter("loaiNguoiDung")));

        int maNguoiDung = ND_DAO.them(thongTinNguoiDung);

        if (maNguoiDung <= 0) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Thong tin dang nhap
        LinkedHashMap<String, Object> thongTinDangNhap = new LinkedHashMap<>();

        thongTinDangNhap.put("maNguoiDung", maNguoiDung);

        try {
            thongTinDangNhap.put("tenDangNhap", req.getParameter("tenDangNhap"));
            String matKhauMaHoa = Hashing.maHoaSHA256(req.getParameter("matKhau"));
            thongTinDangNhap.put("matKhau", matKhauMaHoa);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (TTDN_DAO.them(thongTinDangNhap) > 0) {
            Json.chuyenThanhJson(resp, "Đăng ký thành công");
            return;
        }

        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
