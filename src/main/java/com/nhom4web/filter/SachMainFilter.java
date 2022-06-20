package com.nhom4web.filter;

import com.nhom4web.filter.sach.HinhAnhSachFilter;
import com.nhom4web.filter.sach.PhanLoaiSachFilter;
import com.nhom4web.filter.sach.SachFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

@WebFilter(filterName = "Sach-Filter")
public class SachMainFilter extends HttpFilter {
    private static final HashMap<String, Method> DELETE_MAP = new HashMap<>();
    private static final HashMap<String, Method> GET_MAP = new HashMap<>();
    private static final HashMap<String, Method> POST_MAP = new HashMap<>();
    private static final HashMap<String, Method> PUT_MAP = new HashMap<>();
    private static final SachFilter SACH_FILTER = new SachFilter();
    private static final HinhAnhSachFilter HINH_ANH_SACH_FILTER = new HinhAnhSachFilter();
    private static final PhanLoaiSachFilter PHAN_LOAI_SACH_FILTER = new PhanLoaiSachFilter();
    private static final BinhLuanFilter BINH_LUAN_FILTER = new BinhLuanFilter();

    private boolean thucThi(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String method = req.getMethod();
        String uri = req.getRequestURI();
        Set<Map.Entry<String, Method>> mapEntries = null;
        switch (method) {
            case "DELETE":
                mapEntries = DELETE_MAP.entrySet();
                break;
            case "GET":
                mapEntries = GET_MAP.entrySet();
                break;
            case "POST":
                mapEntries = POST_MAP.entrySet();
                break;
            case "PUT":
                mapEntries = PUT_MAP.entrySet();
                break;
        }
        for (Map.Entry<String, Method> entry : mapEntries) {
            if (Pattern.matches(entry.getKey(), uri)) {
                Object o;
                Class<?> dClass = entry.getValue().getDeclaringClass();
                if (SACH_FILTER.getClass().equals(dClass)) o = SACH_FILTER;
                else if (HINH_ANH_SACH_FILTER.getClass().equals(dClass)) o = HINH_ANH_SACH_FILTER;
                else if (BINH_LUAN_FILTER.getClass().equals(dClass)) o = BINH_LUAN_FILTER;
                else o = PHAN_LOAI_SACH_FILTER;
                return (boolean) entry.getValue().invoke(o, req, resp);
            }
        }
        return false;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String method = req.getMethod();
        if (!method.equals("DELETE") && !method.equals("GET") && !method.equals("POST") && !method.equals("PUT")) {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }
        try {
            if (thucThi(req, res)) {
                chain.doFilter(req, res);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    static {
        try {
            DELETE_MAP.put(
                    "/api/sach/\\d+",
                    SachFilter.class.getMethod("kiemTraDelete", HttpServletRequest.class, HttpServletResponse.class)
            );
            DELETE_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach/\\d+",
                    HinhAnhSachFilter.class.getMethod("kiemTraDelete", HttpServletRequest.class, HttpServletResponse.class)
            );
            DELETE_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach/\\d+",
                    PhanLoaiSachFilter.class.getMethod("kiemTraDelete", HttpServletRequest.class, HttpServletResponse.class)
            );
            DELETE_MAP.put(
                    "/api/sach/\\d+/binh-luan/\\d+",
                    BinhLuanFilter.class.getMethod("kiemTraDelete", HttpServletRequest.class, HttpServletResponse.class)
            );

            GET_MAP.put(
                    "/api/sach",
                    SachFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+",
                    SachFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/lien-quan",
                    SachFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach",
                    HinhAnhSachFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach/\\d+",
                    HinhAnhSachFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach",
                    PhanLoaiSachFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach/\\d+",
                    PhanLoaiSachFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/binh-luan",
                    BinhLuanFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );

            POST_MAP.put(
                    "/api/sach",
                    SachFilter.class.getMethod("kiemTraPost", HttpServletRequest.class, HttpServletResponse.class)
            );
            POST_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach",
                    HinhAnhSachFilter.class.getMethod("kiemTraPost", HttpServletRequest.class, HttpServletResponse.class)
            );
            POST_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach",
                    PhanLoaiSachFilter.class.getMethod("kiemTraPost", HttpServletRequest.class, HttpServletResponse.class)
            );
            POST_MAP.put(
                    "/api/sach/\\d+/binh-luan",
                    BinhLuanFilter.class.getMethod("kiemTraPost", HttpServletRequest.class, HttpServletResponse.class)
            );

            PUT_MAP.put(
                    "/api/sach/\\d+",
                    SachFilter.class.getMethod("kiemTraPut", HttpServletRequest.class, HttpServletResponse.class)
            );
            PUT_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach/\\d+",
                    HinhAnhSachFilter.class.getMethod("kiemTraPut", HttpServletRequest.class, HttpServletResponse.class)
            );
            PUT_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach/\\d+",
                    PhanLoaiSachFilter.class.getMethod("kiemTraPut", HttpServletRequest.class, HttpServletResponse.class)
            );
            PUT_MAP.put(
                    "/api/sach/\\d+/binh-luan/\\d+",
                    BinhLuanFilter.class.getMethod("kiemTraPut", HttpServletRequest.class, HttpServletResponse.class)
            );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
