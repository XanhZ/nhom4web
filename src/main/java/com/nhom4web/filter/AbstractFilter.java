package com.nhom4web.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {
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
                resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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

    protected abstract boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    protected abstract boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    protected abstract boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    protected abstract boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
