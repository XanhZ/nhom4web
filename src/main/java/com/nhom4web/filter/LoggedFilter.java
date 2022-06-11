package com.nhom4web.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Logged")
public class LoggedFilter extends AbstractFilter {
    @Override
    protected boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }

    @Override
    protected boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }

    @Override
    protected boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("nguoiDung") != null) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

    @Override
    protected boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return false;
    }
}
