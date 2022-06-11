package com.nhom4web.filter;

import com.nhom4web.model.NguoiDung;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebFilter(filterName = "Auth")
public class AuthFilter implements Filter {
    private static final Map<String, List<String>> urlNguoiDungs = new HashMap<>();
    private static final int IS_ADMIN = 1;
    private static final int IS_NGUOI_DUNG = 0;
    private static final String[] REGEX_DUONG_DAN_CONG_KHAIS = {
            "/api/sach.*",
            "/api/danh-muc.*",
            "/api/binh-luan.*"
    };

    static {
        // DELETE
        List<String> delete = new ArrayList<>();
        delete.add("/api/binh-luan/\\d+");

        // GET
        List<String> get = new ArrayList<>();
        get.add("/api/dong-hang.*");
        get.add("/api/dong-don-hang.*");

        // POST
        List<String> post = new ArrayList<>();
        post.add("/api/don-hang");
        post.add("/api/dong-don-hang");
        post.add("/api/binh-luan");

        // PUT
        List<String> put = new ArrayList<>();
        put.add("/api/don-hang/\\d+");
        put.add("/api/dong-don-hang/\\d+");

        urlNguoiDungs.put("DELETE", delete);
        urlNguoiDungs.put("GET", get);
        urlNguoiDungs.put("POST", post);
        urlNguoiDungs.put("PUT", put);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Kiểm tra hợp lệ
        boolean hopLe = true;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String httpMethod = req.getMethod();
        int vaiTro = this.getVaiTro(req);
        switch (vaiTro) {
            case IS_NGUOI_DUNG: {
                hopLe = this.isDuongDanNguoiDung(httpMethod, req.getRequestURI());
                break;
            }
            case IS_ADMIN: {
                break;
            }
            default: {
                hopLe = httpMethod.equals("GET") && this.isDuongDanCongKhai(req.getRequestURI());
                break;
            }
        }
        // Chuyển tiếp tới filter, controller tiếp theo nếu hợp lệ
        if (hopLe) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        resp.setStatus(vaiTro == -1 ? HttpServletResponse.SC_UNAUTHORIZED : HttpServletResponse.SC_FORBIDDEN);
    }

    private int getVaiTro(HttpServletRequest req) {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        return nguoiDung == null ? -1 : nguoiDung.getLoaiNguoiDung();
    }

    private boolean isDuongDanCongKhai(String url) {
        for (String regex : REGEX_DUONG_DAN_CONG_KHAIS) {
            if (Pattern.matches(regex, url)) return true;
        }
        return false;
    }

    private boolean isDuongDanNguoiDung(String method, String url) {
        List<String> regexes = urlNguoiDungs.get(method);
        for (String regex : regexes) {
            if (Pattern.matches(regex, url)) return true;
        }
        return false;
    }
}
