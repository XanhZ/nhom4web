<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign.css">
    <title>Đăng nhập</title>
    <link rel="icon" href="${pageContext.request.contextPath}/logo/logo.png" sizes="32x32">
</head>

<body>
<!-- <header>
    <div class="dongGoi">
        <div class="logo">
            <a href="trangchu.html">BookShop</a>
        </div>
        <div class="timKiem">
            <input type="text" name="q" id="q" placeholder="Tìm kiếm...">
        </div>
        <nav class="dieuHuong">
            <a href="trangchu.html" class="dieuHuong__duongDan">Trang chủ</a>
            <div class="dieuHuong__danhMuc">
                <div class="btn-danhMuc">Tủ sách</div>
                <ul class="danhMuc">
                    <li><a href="#">Sách hướng nghiệp</a></li>
                    <li><a href="#">Sách IT</a></li>
                    <li><a href="#">Sách văn học</a></li>
                    <li><a href="#">Sách toán học</a></li>
                    <li><a href="#">Sách kỹ năng</a></li>
                </ul>
            </div>
            <a href="gioithieu.html" class="dieuHuong__duongDan">Giới thiệu</a>
            <a href="lienhe.html" class="dieuHuong__duongDan">Liên hệ</a>
            <a href="dangnhap.html" class="dieuHuong__duongDan">Đăng nhập</a>
            <a href="dangky.html" class="dieuHuong__duongDan">Đăng ký</a>
        </nav>
    </div>
</header> -->
<div class="thungDung">
    <div class="thungDungBieuMau">
        <div class="hinh-anh">
            <img src="${pageContext.request.contextPath}/img/icon-sign.jpg" alt="">
        </div>
        <form action="#" class="bieuMauDangKi">
            <div class="veTrangChu">
                <a href="#">
                    <img src="${pageContext.request.contextPath}/img/logo.png" alt="logo">
                </a>
            </div>
            <h2 class="tieuDe">Đăng nhập</h2>
            <div class="truongNhapLieu">
                <i class="fas fa-user"></i>
                <input type="text" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Tài khoản</label>
            </div>
            <div class="truongNhapLieu">
                <i class="fas fa-lock"></i>
                <input type="password" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Mật khẩu</label>
            </div>
            <input type="submit" value="Đăng nhập" class="nut" />
        </form>
    </div>
</div>
</body>

</html>