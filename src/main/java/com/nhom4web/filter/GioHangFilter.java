package com.nhom4web.filter;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "Gio-Hang-Filter")
@MultipartConfig
public class GioHangFilter extends AbstractFilter {
    @Override
    protected boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return super.kiemTraDelete(req, resp);
    }

    @Override
    protected boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return super.kiemTraGet(req, resp);
    }

    @Override
    protected boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return super.kiemTraPost(req, resp);
    }

    @Override
    protected boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return super.kiemTraPut(req, resp);
    }
}
