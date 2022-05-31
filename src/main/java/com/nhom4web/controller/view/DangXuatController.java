package com.nhom4web.controller.view;

import com.nhom4web.utils.Json;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/dang-xuat")
public class DangXuatController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionDangNhap = req.getSession();
        sessionDangNhap.invalidate();

        Json.chuyenThanhJson(resp, "Đăng xuất");

        RequestDispatcher rd = req.getRequestDispatcher("views/dangnhap.jsp");
        rd.forward(req, resp);
    }
}
