package com.nhom4web.filter;

import com.nhom4web.filter.donhang.DonHangFilter;
import com.nhom4web.filter.donhang.DongDonHangFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@WebFilter(filterName = "Don-Hang-Filter")
@MultipartConfig
public class DonHangMainFilter extends HttpFilter {
    private static final HashMap<String, Method> DELETE_MAP = new HashMap<>();
    private static final HashMap<String, Method> GET_MAP = new HashMap<>();
    private static final HashMap<String, Method> POST_MAP = new HashMap<>();
    private static final HashMap<String, Method> PUT_MAP = new HashMap<>();
    private static final DonHangFilter DON_HANG_FILTER = new DonHangFilter();
    private static final DongDonHangFilter DONG_DON_HANG_FILTER = new DongDonHangFilter();

    private boolean urlKhongKhop;

    private boolean thucThi(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        this.urlKhongKhop = false;
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
                if (DON_HANG_FILTER.getClass().equals(dClass)) o = DON_HANG_FILTER;
                else o = DONG_DON_HANG_FILTER;
                return (boolean) entry.getValue().invoke(o, req, resp);
            }
        }
        this.urlKhongKhop = true;
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
        if (this.urlKhongKhop) res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    static {
        try {
            DELETE_MAP.put(
                    "/api/don-hang/\\d+",
                    DonHangFilter.class.getMethod("kiemTraDelete", HttpServletRequest.class, HttpServletResponse.class)
            );
            DELETE_MAP.put(
                    "/api/don-hang/\\d+/dong-don-hang/\\d+",
                    DongDonHangFilter.class.getMethod("kiemTraDelete", HttpServletRequest.class, HttpServletResponse.class)
            );

            GET_MAP.put(
                    "/api/don-hang",
                    DonHangFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/don-hang/\\d+",
                    DonHangFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/don-hang/\\d+/dong-don-hang",
                    DongDonHangFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/don-hang/\\d+/dong-don-hang/\\d+",
                    DongDonHangFilter.class.getMethod("kiemTraGet", HttpServletRequest.class, HttpServletResponse.class)
            );

            POST_MAP.put(
                    "/api/don-hang",
                    DonHangFilter.class.getMethod("kiemTraPost", HttpServletRequest.class, HttpServletResponse.class)
            );
            POST_MAP.put(
                    "/api/don-hang/\\d+/dong-don-hang",
                    DongDonHangFilter.class.getMethod("kiemTraPost", HttpServletRequest.class, HttpServletResponse.class)
            );

            PUT_MAP.put(
                    "/api/don-hang/\\d+",
                    DonHangFilter.class.getMethod("kiemTraPut", HttpServletRequest.class, HttpServletResponse.class)
            );
            PUT_MAP.put(
                    "/api/don-hang/\\d+/dong-don-hang/\\d+",
                    DongDonHangFilter.class.getMethod("kiemTraPut", HttpServletRequest.class, HttpServletResponse.class)
            );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}