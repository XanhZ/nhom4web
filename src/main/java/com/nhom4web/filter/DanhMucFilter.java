package com.nhom4web.filter;

import com.nhom4web.utils.Json;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter("/api/danh-muc/*")
public class DanhMucFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Kiểm tra hợp lệ
        boolean hopLe = true;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String httpMethod = req.getMethod();
        switch (httpMethod) {
            case "DELETE": {
                hopLe = this.kiemTraDelete(req, resp);
                break;
            }
            case "GET": {
                hopLe = this.kiemTraGet(req, resp);
                break;
            }
            case "POST": {
                hopLe = this.kiemTraPost(req, resp);
                break;
            }
            case "PUT": {
                hopLe = this.kiemTraPut(req, resp);
                break;
            }
            default: {
                hopLe = false;
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
            }
        }
        // Chuyển tiếp tới filter, controller tiếp theo nếu hợp lệ
        if (hopLe) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null || !Pattern.matches("/\\d+", duongDan)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(duongDan);
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }

    private boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null) {
            req.setAttribute("ma", null);
        } else {
            if (!Pattern.matches("/\\d*", duongDan)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return false;
            }
            Pattern patternMa = Pattern.compile("\\d+");
            Matcher matcher = patternMa.matcher(duongDan);
            int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
            req.setAttribute("ma", ma > 0 ? ma : null);
        }
        return true;
    }

    private boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan != null && !duongDan.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        String tenDanhMuc = req.getParameter("tenDanhMuc");
        if (tenDanhMuc == null || tenDanhMuc.equals("")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            HashMap<String, String> loi = new HashMap<>();
            loi.put("tenDanhMuc", "Không được để trống");
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        return true;
    }

    private boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null || !Pattern.matches("/\\d+", duongDan)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(duongDan);
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        String tenDanhMuc = req.getParameter("tenDanhMuc");
        if (tenDanhMuc == null || tenDanhMuc.equals("")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            HashMap<String, String> loi = new HashMap<>();
            loi.put("tenDanhMuc", "Không được để trống");
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }
}
