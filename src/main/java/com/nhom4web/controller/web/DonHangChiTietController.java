package com.nhom4web.controller.web;

import com.nhom4web.model.NguoiDung;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nguoi-dung/don-hang/chi-tiet")
public class DonHangChiTietController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung != null) {
            RequestDispatcher rd = req.getRequestDispatcher("/views/chitietdonhang.jsp");
            rd.forward(req, resp);
            return;
        }
        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
