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
    <title>Đăng kí</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign.css">
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
            <h2 class="title">Đăng kí</h2>
            <div class="truongNhapLieu" id="hoVaTen">
                <i class="far fa-id-card"></i>
                <input type="text" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Họ và tên</label>
                <div class="danhGia"><i class="fas fa-check-circle"></i></div>
            </div>
            <div class="truongNhapLieu" id="sdt">
                <i class="fa-solid fa-phone"></i>
                <input type="text" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Số điện thoại</label>
                <div class="danhGia"><i class="fas fa-check-circle"></i></div>
            </div>
            <div class="truongNhapLieu" id="email">
                <i class="fas fa-envelope"></i>
                <input type="email" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Email</label>
                <div class="danhGia"><i class="fas fa-check-circle"></i></div>
            </div>
            <div class="truongNhapLieu" id="taiKhoan">
                <i class="fas fa-user"></i>
                <input type="text" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Tài khoản</label>
                <div class="danhGia"><i class="fas fa-check-circle"></i></div>
            </div>
            <div class="truongNhapLieu" id="matKhau">
                <i class="fas fa-lock"></i>
                <input type="password" id="pass1" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu" for="pass">Mật khẩu</label>
                <div class="danhGia" id="checkPass1"><i class="fas fa-check-circle"></i></div>
            </div>
            <p id="mess" style="font-size: 12px;color: rgb(49, 48, 48);">
            </p>
            <div class="truongNhapLieu" id="xacNhanMatKhau">
                <i class="fas fa-lock"></i>
                <input type="password" id="cnfPass" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu" for="cnfPass">Xác nhận mật khẩu</label>
                <div class="danhGia" id="checkPass2"><i class="fas fa-check-circle"></i></div>
            </div>
            <input type="submit" class="nut" value="Đăng kí" />
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/validator.js"></script>
<script>
    Validator({
        form: ".bieuMauDangKi",
        rules: [
            Validator.isRequired("#hoVaTen"),
            Validator.isRequired("#sdt"),
            Validator.isEmail("#email"),
            Validator.isRequired("#taiKhoan"),
            Validator.minLength("#matKhau", 8),
            Validator.cnfPass("#xacNhanMatKhau", function () {
                return document.querySelector(".bieuMauDangKi #matKhau #xacNhanMatKhau").value;
            }, 8)
        ],
    })
</script>
</body>

</html>
