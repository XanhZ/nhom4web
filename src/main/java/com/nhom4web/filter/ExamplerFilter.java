package com.nhom4web.filter;

import com.nhom4web.controller.Json;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebFilter("/api/examples/*")
public class ExamplerFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    // Kiểm tra hợp lệ
    boolean hopLe = true;
    HttpServletRequest req = (HttpServletRequest)servletRequest;
    HttpServletResponse resp = (HttpServletResponse)servletResponse;
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
    String path = req.getPathInfo();
    String[] parts = path.split("/");
    if (parts.length != 2) {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      return false;
    }
    try {
      int id = Integer.parseInt(parts[1]);
      if (id < 0) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        return false;
      }
    } catch (NumberFormatException e) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return false;
    }
    return true;
  }

  private boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String path = req.getPathInfo();
    if (path == null || path.equals("/")) {
      return true;
    }
    String[] parts = path.split("/");
    if (parts.length != 2) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return false;
    }
    try {
      int id = Integer.parseInt(parts[1]);
      if (id < 0) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        return false;
      }
    } catch (NumberFormatException e) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return false;
    }
    return true;
  }

  private boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    boolean hopLe = true;
    HashMap<String, String> loi = new HashMap<>();
    String hoTen = req.getParameter("hoTen");
    String tuoi = req.getParameter("tuoi");
    if (hoTen == null || hoTen.equals("")) {
      loi.put("hoTenError", "Tên không được để trống");
      hopLe = false;
    }
    if (tuoi == null || tuoi.equals("")) {
      loi.put("tuoiError", "Tuổi không được để trống");
      hopLe = false;
    }
    try {
      int tuoiInt = Integer.parseInt(req.getParameter("tuoi"));
      if (tuoiInt < 0) {
        loi.put("tuoiError", "Tuổi không hợp lệ");
        hopLe = false;
      }
    } catch (NumberFormatException e) {
      loi.put("tuoiError", "Tuổi không hợp lệ");
      hopLe = false;
    }
    if (!hopLe) {
      Json.chuyenThanhJson(resp, loi);
    }
    return hopLe;
  }

  private boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String path = req.getPathInfo();
    String[] parts = path.split("/");
    if (parts.length != 2) {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      return false;
    }
    boolean hopLe = true;
    HashMap<String, String> loi = new HashMap<>();
    String hoTen = req.getParameter("hoTen");
    String tuoi = req.getParameter("tuoi");
    if (hoTen == null || hoTen.equals("")) {
      loi.put("hoTenError", "Tên không được để trống");
      hopLe = false;
    }
    if (tuoi == null || tuoi.equals("")) {
      loi.put("tuoiError", "Tuổi không được để trống");
      hopLe = false;
    }
    try {
      int tuoiInt = Integer.parseInt(req.getParameter("tuoi"));
      if (tuoiInt < 0) {
        loi.put("tuoiError", "Tuổi không hợp lệ");
        hopLe = false;
      }
    } catch (NumberFormatException e) {
      loi.put("tuoiError", "Tuổi không hợp lệ");
      hopLe = false;
    }
    if (!hopLe) {
      Json.chuyenThanhJson(resp, loi);
    }
    return hopLe;
  }
}
