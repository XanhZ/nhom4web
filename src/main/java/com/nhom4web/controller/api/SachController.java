package com.nhom4web.controller.api;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhom4web.service.impl.SachService;
import com.nhom4web.utils.Json;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebServlet("/api/sach/*")
@MultipartConfig
public class SachController extends HttpServlet {
    private static final String CLOUDINARY_URL = "cloudinary://923346781537818:IXWJ1uydnFdLioVJ8-4G4HcB4Kc@xanhz";
    private static final SachService SERVICE = new SachService();
    private static final Cloudinary CLOUDINARY = new Cloudinary(SachController.CLOUDINARY_URL);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maSach = this.themSach(req);
        if (maSach == -1) {
            Json.chuyenThanhJson(resp, "Đã xảy ra lỗi");
            return;
        }

        boolean thanhCong = this.themPhanLoaiSach(req, maSach);
        if (!thanhCong) {
            Json.chuyenThanhJson(resp, "Đã xảy ra lỗi");
            return;
        }

        thanhCong = this.themHinhAnh(req, maSach);
        if (!thanhCong) {
            Json.chuyenThanhJson(resp, "Đã xảy ra lỗi");
            return;
        }

        Json.chuyenThanhJson(resp, "Thêm thành công");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    private String getFileExtension(Part file) {
        Pattern regex = Pattern.compile("filename=\".*\"");
        Matcher matcher = regex.matcher(file.getHeader("content-disposition"));
        if (matcher.find()) {
            String s = matcher.group();
            String tenFile = s.substring(10, s.length() - 1);
            return tenFile.substring(tenFile.lastIndexOf("."));
        }
        return null;
    }

    private boolean themHinhAnh(HttpServletRequest req, int maSach) throws ServletException, IOException {
        Collection<Part> files = req.getParts()
                .stream()
                .filter(part -> part.getName().equals("anh"))
                .collect(Collectors.toList());
        List<String> duongDans = new ArrayList<>();
        List<String> publicIds = new ArrayList<>();
        files.forEach(file -> {
            try {
                File temp = File.createTempFile("temp", this.getFileExtension(file));
                FileUtils.copyInputStreamToFile(file.getInputStream(), temp);
                Map res = SachController.CLOUDINARY
                        .uploader()
                        .upload(temp, null);
                duongDans.add((String) res.get("secure_url"));
                publicIds.add((String) res.get("public_id"));
                boolean xoaTemp = temp.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        boolean rt = SERVICE.themHinhAnhSach(maSach, duongDans);
        if (!rt) {
            try {
                CLOUDINARY.api().deleteResources(publicIds, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rt;
    }

    private int themSach(HttpServletRequest req) {
        LinkedHashMap<String, Object> duLieu = new LinkedHashMap<>();
        duLieu.put("tenSach", req.getParameter("tenSach"));
        duLieu.put("giaTien", Integer.parseInt(req.getParameter("giaTien")));
        duLieu.put("soLuongTrongKho", Integer.parseInt(req.getParameter("soLuongTrongKho")));
        return SERVICE.them(duLieu);
    }

    private boolean themPhanLoaiSach(HttpServletRequest req, int maSach) {
        return SERVICE.phanLoaiSach(
                maSach,
                Arrays.stream(req.getParameterMap().get("maDanhMuc"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
    }
}
