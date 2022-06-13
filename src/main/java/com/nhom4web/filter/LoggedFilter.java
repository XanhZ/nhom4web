package com.nhom4web.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Logged")
public class LoggedFilter extends AbstractFilter {
    @Override
    public boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }

    @Override
    public boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }

    @Override
    public boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("nguoiDung") != null) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

    @Override
    public boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }
}
