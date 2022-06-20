package com.nhom4web.controller.web;

import com.nhom4web.model.NguoiDung;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/admin/danh-muc")
public class AdminDanhMucController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NguoiDung nguoiDung = (NguoiDung) req.getSession().getAttribute("nguoiDung");
        if (nguoiDung == null) {
           resp.sendError(HttpServletResponse.SC_UNAUTHORIZED); //401
        }else{
            if(nguoiDung.getLoaiNguoiDung() == 0){
                resp.sendError(HttpServletResponse.SC_FORBIDDEN); //403
            }else if(nguoiDung.getLoaiNguoiDung() ==1){
                RequestDispatcher rd = req.getRequestDispatcher("/views/ad_danhmuc.jsp");
                rd.forward(req, resp);
            }
        }
    }
}
