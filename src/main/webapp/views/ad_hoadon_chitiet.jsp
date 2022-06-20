<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Sách</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_hoadon_chitiet.css">
</head>
<body>
<div class="thungChua" id="${maDonHang}">
    <div class="dieuHuong">
        <ul>
            <li>
                <a href="#">
                        <span class="bieuTuong">
                            <i class="fa-solid fa-circle-info"></i>
                        </span>
                    <span class="tieuDe logo">Thông tin khách hàng</span>
                </a>
            </li>
            <li>
                <a href="">
                        <span class="bieuTuong">
                            <i class="fa-solid fa-user"></i>
                        </span>
                    <span class="tieuDe" id="tenKhachHang">Tên Khách Hàng</span>
                </a>
            </li>
            <li>
                <a href="">
                        <span class="bieuTuong">
                            <i class="fa-solid fa-phone"></i>
                        </span>
                    <span class="tieuDe" id="sdtKhachHang">Số điện thoại</span>
                </a>
            </li>
            <li>
                <a href="">
                      <span class="bieuTuong">
                        <i class="fa-solid fa-at"></i>
                      </span>
                    <span class="tieuDe" id="emailKhachHang">Email</span>
                </a>
            </li>
            <li>
                <a href="">
                        <span class="bieuTuong">
                            <i class="fa-solid fa-location-dot"></i>
                        </span>
                    <span class="tieuDe" id="diaChiKhachHang">Địa chỉ</span>
                </a>
            </li>
            <li>
                <div class="nutThaoTac">
                    <button onclick="xacNhanDonHang()" class="nut">Xác nhận Đơn</button>
                    <button onclick="huyDonHang()" class="nut">Hủy Đơn</button>
                    <button onclick="xoaDonHang()" class="nut">Xóa Đơn</button>
                </div>
            </li>
        </ul>
    </div>

    <div class="manChinh">
        <div class="topbar">
            <div class="congTac">
                <i class="fa-solid fa-align-justify"></i>
            </div>
        </div>
        <div class="noiDung">
            <div class="danhSach">
                <!-- <form onsubmit="return themSach()" class="bangThemSach">
                  <input type="text" placeholder="Tên sách">
                  <input type="text" placeholder="Danh mục">
                  <input type="text" placeholder="Giá">
                  <input type="text" placeholder="Số lượng">
                  <input type="text" placeholder="Đường dẫn ảnh">
                  <input type="submit" value="Thêm sách" class="nutThemSach">
                </form> -->
                <table>
                    <thead>
                    <tr>
                        <td>Tên sách</td>
                        <td>Số lượng</td>
                        <td>Đơn giá</td>
                    </tr>
                    </thead>

                    <tbody id="danhSachSach">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
<script src="${pageContext.request.contextPath}/js/ad_hoadon_chitiet.js"></script>
</html>
