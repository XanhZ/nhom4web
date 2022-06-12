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
<div class="thungDung">
    <div class="thungDungBieuMau">
        <div class="hinh-anh">
            <img src="${pageContext.request.contextPath}/img/icon-sign.jpg" alt="">
        </div>
        <form action="#" class="bieuMauDangKi">
            <div class="veTrangChu">
                <a href="/trang-chu">
                    <img src="${pageContext.request.contextPath}/img/logo.png" alt="logo">
                </a>
            </div>
            <h2 class="title">Đăng kí</h2>
            <div class="truongNhapLieu" id="hoVaTen">
                <i class="far fa-id-card"></i>
                <input type="text" name="hoVaTen"class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Họ và tên</label>
                <div class="danhGia"><i class="fas fa-check-circle"></i></div>
            </div>
            <div class="truongNhapLieu" id="sdt">
                <i class="fa-solid fa-phone"></i>
                <input type="text" name="soDienThoai" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Số điện thoại</label>
                <div class="danhGia"><i class="fas fa-check-circle"></i></div>
            </div>
            <div class="truongNhapLieu" id="email">
                <i class="fas fa-envelope"></i>
                <input type="email" name="email" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Email</label>
                <div class="danhGia"><i class="fas fa-check-circle"></i></div>
            </div>
            <div class="truongNhapLieu" id="taiKhoan">
                <i class="fas fa-user"></i>
                <input type="text" name="taiKhoan" class="nhapLieu" placeholder=" ">
                <label class="nhanNhapLieu">Tài khoản</label>
                <div class="danhGia"><i class="fas fa-check-circle"></i></div>
            </div>
            <div class="truongNhapLieu" id="matKhau">
                <i class="fas fa-lock"></i>
                <input type="password" name="matKhau" id="pass" class="nhapLieu" placeholder=" ">
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

<script src="${pageContext.request.contextPath}/js/dangky.js"></script>
<script>
    XacNhan({
        bieuMau: ".bieuMauDangKi",
        luats: [
            XacNhan.batBuoc("#hoVaTen"),
            XacNhan.batBuoc("#sdt"),
            XacNhan.email("#email"),
            XacNhan.batBuoc("#taiKhoan"),
            XacNhan.doDaiNhoNhat("#matKhau", 8),
            XacNhan.xacNhanMatKhau("#xacNhanMatKhau", function () {
                return document.querySelector(".bieuMauDangKi #matKhau input").value;
            }, 8)
        ],
        onSubmit: async function (data) {
            console.log(data)
            const api = "/api/dang-ky";

                const nguoiDung = new FormData();
                nguoiDung.append('sdt', data.soDienThoai)
                nguoiDung.append('email', data.email)
                nguoiDung.append('ten', data.hoVaTen)
                nguoiDung.append('tenDangNhap', data.taiKhoan)
                nguoiDung.append('matKhau', data.matKhau)

                fetch(api, {
                    method: 'POST',
                    body: nguoiDung
                })
                    .then(function(resp) {
                        if (resp.status !== 200 || resp.status !== 201) {
                            location.assign("/trang-chu")
                            return resp.json();
                        }
                    })
        }
    })
</script>
</body>

</html>
