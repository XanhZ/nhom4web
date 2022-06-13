package com.nhom4web.controller.api;

import com.nhom4web.controller.api.sach.HinhAnhSachController;
import com.nhom4web.controller.api.sach.PhanLoaiSachController;
import com.nhom4web.controller.api.sach.SachController;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet("/api/sach/*")
@MultipartConfig
public class SachMainController extends HttpServlet {
    private static final HashMap<String, Method> DELETE_MAP = new HashMap<>();
    private static final HashMap<String, Method> GET_MAP = new HashMap<>();
    private static final HashMap<String, Method> POST_MAP = new HashMap<>();
    private static final HashMap<String, Method> PUT_MAP = new HashMap<>();

    private void thucThi(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String method = req.getMethod();
        String uri = req.getRequestURI();
        Set<Map.Entry<String, Method>>  mapEntries = null;
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
                entry.getValue().invoke(null, req, resp);
                return;
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.thucThi(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.thucThi(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.thucThi(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.thucThi(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    static {
        try {
            DELETE_MAP.put(
                    "/api/sach/\\d+",
                    SachController.class.getMethod("xoa", HttpServletRequest.class, HttpServletResponse.class)
            );
            DELETE_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach/\\d+",
                    HinhAnhSachController.class.getMethod("xoa", HttpServletRequest.class, HttpServletResponse.class)
            );
            DELETE_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach/\\d+",
                    PhanLoaiSachController.class.getMethod("xoa", HttpServletRequest.class, HttpServletResponse.class)
            );

            GET_MAP.put(
                    "/api/sach",
                    SachController.class.getMethod("timTatTa", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+",
                    SachController.class.getMethod("tim", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach",
                    HinhAnhSachController.class.getMethod("timTatCa", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach/\\d+",
                    HinhAnhSachController.class.getMethod("tim", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach",
                    PhanLoaiSachController.class.getMethod("timTatCa", HttpServletRequest.class, HttpServletResponse.class)
            );
            GET_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach/\\d+",
                    PhanLoaiSachController.class.getMethod("tim", HttpServletRequest.class, HttpServletResponse.class)
            );

            POST_MAP.put(
                    "/api/sach",
                    SachController.class.getMethod("them", HttpServletRequest.class, HttpServletResponse.class)
            );
            POST_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach",
                    HinhAnhSachController.class.getMethod("them", HttpServletRequest.class, HttpServletResponse.class)
            );
            POST_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach",
                    PhanLoaiSachController.class.getMethod("them", HttpServletRequest.class, HttpServletResponse.class)
            );

            PUT_MAP.put(
                    "/api/sach/\\d+",
                    SachController.class.getMethod("capNhat", HttpServletRequest.class, HttpServletResponse.class)
            );
            PUT_MAP.put(
                    "/api/sach/\\d+/hinh-anh-sach/\\d+",
                    HinhAnhSachController.class.getMethod("capNhat", HttpServletRequest.class, HttpServletResponse.class)
            );
            PUT_MAP.put(
                    "/api/sach/\\d+/phan-loai-sach/\\d+",
                    PhanLoaiSachController.class.getMethod("capNhat", HttpServletRequest.class, HttpServletResponse.class)
            );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
