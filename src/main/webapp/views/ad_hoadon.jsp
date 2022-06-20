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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_hoadon.css">
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
      <li class="">
        <a href="/admin/danh-muc">
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
      <li class="hoatDong">
        <a href="">
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

      <div class="timKiem">
        <label>
          <input type="text" placeholder="Tìm kiếm ...">
          <i class="fa-solid fa-magnifying-glass"></i>
        </label>
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
            <td>Người dùng</td>
            <td>Số điện thoại</td>
            <td>Địa chỉ</td>
            <td>Tổng tiền</td>
            <td>Thời gian tạo</td>
            <td></td>
          </tr>
          </thead>
          <tbody id="danhSachDonHang">
          <tr>
            <td>Tên người dùng</td>
            <td>Số điện thoại</td>
            <td>Địa chỉ</td>
            <td>Tổng tiền</td>
            <td>Thời gian tạo</td>
            <td>
              <div class="chinhSua">
                <i class="fa-solid fa-circle-check nutXacNhan"></i>
                <i class="fa-solid fa-trash nutXoa"></i>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
<script src="${pageContext.request.contextPath}/js/ad_hoadon.js"></script>
</html>
