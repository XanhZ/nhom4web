package com.nhom4web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Logged")
public class LoggedFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (!req.getMethod().equals("POST")) {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }
        if (req.getSession().getAttribute("nguoiDung") != null) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        chain.doFilter(req, res);
    }
}
