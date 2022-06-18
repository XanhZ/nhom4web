package com.nhom4web.filter;

import com.nhom4web.model.NguoiDung;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@WebFilter(filterName = "Auth")
public class AuthFilter extends HttpFilter {
    private static final Map<String, Integer> PHAN_QUYEN_GET = new HashMap<>();
    private static final Map<String, Integer> PHAN_QUYEN_DELETE = new HashMap<>();
    private static final Map<String, Integer> PHAN_QUYEN_POST = new HashMap<>();
    private static final Map<String, Integer> PHAN_QUYEN_PUT = new HashMap<>();

    public static final int IS_CONG_KHAI = -1;
    public static final int IS_ADMIN = 1;
    public static final int IS_NGUOI_DUNG = 0;

    private boolean tonTaiUrl;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Map<String, Integer> phanQuyenMap = null;
        switch (req.getMethod()) {
            case "GET": {
                phanQuyenMap = PHAN_QUYEN_GET;
                break;
            }
            case "DELETE": {
                phanQuyenMap = PHAN_QUYEN_DELETE;
                break;
            }
            case "POST": {
                phanQuyenMap = PHAN_QUYEN_POST;
                break;
            }
            case "PUT": {
                phanQuyenMap = PHAN_QUYEN_PUT;
                break;
            }
        }
        int vaiTro = this.getVaiTro(req);
        if (phanQuyenMap == null) {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }
        if (this.isHopLe(vaiTro, req.getRequestURI(), phanQuyenMap)) {
            chain.doFilter(req, res);
            return;
        }
        if (this.tonTaiUrl) {
            res.setStatus(vaiTro == -1 ? HttpServletResponse.SC_UNAUTHORIZED : HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    private boolean isHopLe(int vaiTro, String uri, Map<String, Integer> phanQuyenMap) {
        this.tonTaiUrl = false;
        for (Map.Entry<String, Integer> o : phanQuyenMap.entrySet()) {
            if (Pattern.matches(o.getKey(), uri)) {
                this.tonTaiUrl = true;
                return vaiTro >= o.getValue();
            }
        }
        return false;
    }

    private int getVaiTro(HttpServletRequest req) {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        return nguoiDung == null ? -1 : nguoiDung.getLoaiNguoiDung();
    }

    static {
        // GET
        PHAN_QUYEN_GET.put("/api/sach", IS_CONG_KHAI);
        PHAN_QUYEN_GET.put("/api/sach/\\d+", IS_CONG_KHAI);
        PHAN_QUYEN_GET.put("/api/sach/\\d+/hinh-anh-sach", IS_CONG_KHAI);
        PHAN_QUYEN_GET.put("/api/sach/\\d+/hinh-anh-sach/\\d+", IS_CONG_KHAI);
        PHAN_QUYEN_GET.put("/api/sach/\\d+/phan-loai-sach", IS_CONG_KHAI);
        PHAN_QUYEN_GET.put("/api/sach/\\d+/phan-loai-sach/\\d+", IS_CONG_KHAI);
        PHAN_QUYEN_GET.put("/api/danh-muc", IS_CONG_KHAI);
        PHAN_QUYEN_GET.put("/api/danh-muc/\\d+", IS_CONG_KHAI);
        PHAN_QUYEN_GET.put("/api/gio-hang", IS_NGUOI_DUNG);

        // POST
        PHAN_QUYEN_POST.put("/api/dang-xuat", IS_NGUOI_DUNG);
        PHAN_QUYEN_POST.put("/api/danh-muc", IS_ADMIN);
        PHAN_QUYEN_POST.put("/api/sach", IS_ADMIN);
        PHAN_QUYEN_POST.put("/api/sach/\\d+/binh-luan", IS_NGUOI_DUNG);
        PHAN_QUYEN_POST.put("/api/sach/\\d+/hinh-anh-sach", IS_ADMIN);
        PHAN_QUYEN_POST.put("/api/sach/\\d+/phan-loai-sach", IS_ADMIN);
        PHAN_QUYEN_POST.put("/api/nguoi-dung/don-hang", IS_NGUOI_DUNG);
        PHAN_QUYEN_POST.put("/api/gio-hang", IS_NGUOI_DUNG);

        // PUT
        PHAN_QUYEN_PUT.put("/api/nguoi-dung/don-hang/\\d+", IS_NGUOI_DUNG);
        PHAN_QUYEN_PUT.put("/api/nguoi-dung/binh-luan/\\d+", IS_NGUOI_DUNG);
        PHAN_QUYEN_PUT.put("/api/danh-muc/\\d+", IS_ADMIN);
        PHAN_QUYEN_PUT.put("/api/sach/\\d+/binh-luan/\\d+", IS_ADMIN);
        PHAN_QUYEN_PUT.put("/api/sach/\\d+", IS_ADMIN);
        PHAN_QUYEN_PUT.put("/api/sach/\\d+/hinh-anh-sach/\\d+", IS_ADMIN);
        PHAN_QUYEN_PUT.put("/api/sach/\\d+/phan-loai-sach/\\d+", IS_ADMIN);
        PHAN_QUYEN_PUT.put("/api/don-hang/\\d+", IS_ADMIN);
        PHAN_QUYEN_PUT.put("/api/gio-hang", IS_NGUOI_DUNG);

        // DELETE
        PHAN_QUYEN_DELETE.put("/api/nguoi-dung/binh-luan/\\d+", IS_NGUOI_DUNG);
        PHAN_QUYEN_DELETE.put("/api/danh-muc/\\d+", IS_ADMIN);
        PHAN_QUYEN_DELETE.put("/api/sach/\\d+", IS_ADMIN);
        PHAN_QUYEN_DELETE.put("/api/sach/\\d+/binh-luan/\\d+", IS_ADMIN);
        PHAN_QUYEN_DELETE.put("/api/sach/\\d+/hinh-anh-sach/\\d+", IS_ADMIN);
        PHAN_QUYEN_DELETE.put("/api/sach/\\d+/phan-loai-sach/\\d+", IS_ADMIN);
        PHAN_QUYEN_DELETE.put("/api/don-hang/\\d+", IS_ADMIN);
        PHAN_QUYEN_DELETE.put("/api/gio-hang", IS_NGUOI_DUNG);
    }
}
