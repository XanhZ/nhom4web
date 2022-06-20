<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Shop</title>
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_sach_chitiet.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
</head>

<body onload="chayChuongTrinh()">
<main id="${ma}">
    <div class="noi-dung__duong-dan">
        <ul>
            <li id="tenSachDauTrang"></li>
        </ul>
    </div>
    <div class="noi-dung__sach">
        <div class="sach__hinh-anh">
            <div class="hinh-anh__album">
            </div>
            <div class="hinh-anh-hien-thi">
            </div>
        </div>
        <div class="sach__thong-tin">
            <form action="">
                <div class="truongNhapLieu">
                    <input type="text" class="nhapLieu" placeholder=" " name="tenSach">
                    <label class="nhanNhapLieu">Tên sách</label>
                </div>
                <div class="thanhCuon">
                    <div class="thanhCuon-noiDung">
                        <ul id="tatCaDanhMuc">
                        </ul>
                    </div>
                </div>
                <div class="truongNhapLieu">
                    <input type="text" class="nhapLieu" placeholder=" " name="giaSach">
                    <label class="nhanNhapLieu">Giá sách</label>
                </div>
                <div class="truongNhapLieu">
                    <input type="text" class="nhapLieu" placeholder=" " name="soLuong">
                    <label class="nhanNhapLieu">Số lượng</label>
                </div>
                <div class="truongNhapLieu">
                    <label >Chọn ảnh:</label>
                    <input type="file" multiple="multiple" placeholder=" " name="hinhAnhs">
                </div>
            </form>
            <div class="modal-nutsua">
                <c:if test="${ma != 0}">
                    <button onclick="capNhatSach()" type="button">Cập nhật</button>
                </c:if>
                <c:if test="${ma == 0}">
                    <button onclick="themSach()" type="button">Thêm sách</button>
                </c:if>
            </div>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/ad_sach_chitiet.js"></script>
</body>
</html>
