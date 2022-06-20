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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sach.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
</head>

<body>
<%@ include file="header.jsp"%>
<main>
    <div class="noi-dung__duong-dan">
        <ul>
            <li>
                <a href="/trang-chu">Trang chủ</a>
                <i class="fas fa-chevron-right fa-sm"></i>
            </li>
            <li>
                <a href="#">Tủ sách</a>
                <i class="fas fa-chevron-right fa-sm"></i>
            </li>
        </ul>
    </div>
    <div class="noi-dung__sach">
        <div class="sach__hinh-anh">
            <div class="hinh-anh__album">
            </div>
            <div class="hinh-anh-hien-thi">
                <img>
            </div>
        </div>
        <div class="sach__thong-tin">
            <div class="tac-gia">
                <span class="thong-tin__tieu-de">Tác giả:</span>
                <span class="tac-gia__ten">Nhóm tác giả Spiderum</span>
            </div>
        </div>
    </div>
    <div class="binhLuans">
        <c:if test="${not empty nguoiDung}">
            <div class="themBinhLuan">
                <div class="thongTinPhu">
                    <span class="tenNguoiBinhLuan"> ${nguoiDung.ten}: </span>
                </div>
                <div class="noiDungChinh">
                    <input type="text" placeholder="nhập bình luận" class="nhapNoiDung">
                </div>
            </div>
        </c:if>
        <div class="cacBinhLuan"></div>
    </div>
    <div class="noi-dung__sp-lien-quan">
        <div class="loaiSanPham">
            <div class="tieuDe">
                <p>Sách liên quan</p>
            </div>
            <div class="trungBay">
            </div>
        </div>
    </div>
</main>
<%@ include file="footer.jsp"%>
<script src="../js/sach.js"></script>
<script src="../js/header.js"></script>
</body>

</html>