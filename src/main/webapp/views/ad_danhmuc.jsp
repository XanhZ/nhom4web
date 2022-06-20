<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Danh mục</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_danhmuc.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
</head>
<body>
<div class="thungChua">
    <div class="dieuHuong">
        <ul>
            <li>
                <a href="#">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-layer-group"></i>
                        </span>
                    <span class="tieuDe logo">Book Shop</span>
                </a>
            </li>
            <li class="hoatDong">
                <a href="">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-book-bookmark"></i>
                        </span>
                    <span class="tieuDe">Danh mục</span>
                </a>
            </li>
            <li>
                <a href="/admin/sach">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-book"></i>
                        </span>
                    <span class="tieuDe">Sách</span>
                </a>
            </li>
            <li>
                <a href="/admin/don-hang">
                      <span class="bieuTuong">
                        <i class="fa-solid fa-money-bill-trend-up"></i>
                      </span>
                    <span class="tieuDe">Đơn hàng</span>
                </a>
            </li>
            <li>
                <a id="dangXuat" href="#">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-right-from-bracket"></i>
                        </span>
                    <span class="tieuDe">Đăng xuất</span>
                </a>
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
                <form onsubmit="return themSach()" class="bangThemDanhMuc">
                    <input type="text" name="tenDanhMuc" placeholder="Tên danh mục">
                    <input type="button" onclick="themDanhMuc()" value="Thêm danh mục" class="nutThemSach">
                </form>
                <table>
                    <thead>
                    <tr>
                        <td>Danh mục</td>
                        <td>Thời gian tạo</td>
                        <td>Thời gian cập nhập</td>
<%--                        <td>Số loại sách</td>--%>
                    </tr>
                    </thead>

                    <tbody id="danhSachDanhMuc">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%--Modal--%>
<div class="modal hide">
    <div class="modal-nen">
    </div>
    <div class="modal-noidung">
        <div class="modal-noidungchinh">
            <div class="modal-tieude">
                Sửa Danh Mục
            </div>
            <div class="modal-thanhphansach">
                <form action="">
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " readonly name="modalMaDanhMuc">
                        <label class="nhanNhapLieu">Mã Danh Mục</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " name="modalTenDanhMuc">
                        <label class="nhanNhapLieu">Tên danh mục</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " readonly name="modalThoiGianTao">
                        <label class="nhanNhapLieu">Thời gian tạo</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " readonly name="modalCapNhatGanNhat">
                        <label class="nhanNhapLieu">Cập nhật gần nhất</label>
                    </div>
                </form>
            </div>
            <div class="modal-nutsua">
                <button type="button">Cập nhật</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
<script src="${pageContext.request.contextPath}/js/ad_danhmuc.js"></script>
</html>