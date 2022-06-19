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
                <div class="hinh-anh--don dang-hien-thi">
                    <img src="https://product.hstatic.net/200000123069/product/01_2d46620cb39244419293ed2a27642676_master.jpg">
                </div>
                <div class="hinh-anh--don">
                    <img src="http://product.hstatic.net/200000123069/product/01_2d46620cb39244419293ed2a27642676_master.jpg">
                </div>
                <div class="hinh-anh--don">
                    <img src="http://product.hstatic.net/200000123069/product/06_593f7f745177481aa52a24774942c11c_master.jpg">
                </div>
                <div class="hinh-anh--don">
                    <img src="https://product.hstatic.net/200000123069/product/02_d89e11769cf24fe6bf3ac1686e0eecae_master.jpg">
                </div>
                <div class="hinh-anh--don">
                    <img src="https://product.hstatic.net/200000123069/product/05_3dc4173f00e3491084ff244d689da617_master.jpg">
                </div>
            </div>
            <div class="hinh-anh-hien-thi">
                <img src="https://product.hstatic.net/200000123069/product/01_2d46620cb39244419293ed2a27642676_master.jpg" alt="">
            </div>
        </div>
        <div class="sach__thong-tin">
            <div class="tac-gia">
                <span class="thong-tin__tieu-de">Tác giả:</span>
                <span class="tac-gia__ten">Nhóm tác giả Spiderum</span>
            </div>
            <form class="them-gio-hang-form">
                <div class="thong-tin-so-luong">
                    <input type="button" value="-" class="btn-so-luong">
                    <input type="number" name="soLuong" id="soLuong" value="1" min="1">
                    <input type="button" value="+" class="btn-so-luong">
                </div>
            </form>
        </div>
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
<footer class="cuoiTrang">
    <div class="veChungToi">
        <p class="tieuDe">giới thiệu</p>
        <div style="font-size: 15px;color:#acb0b1;margin:20px 0;">Bản quyền của Công Ty Cổ Phần Ahora - Giấy chứng nhận
            ĐKKD số: 0107886528 do Phòng ĐKKD Sở kế hoạch và đầu tư Tp Hà Nội cấp ngày 14/06/2017</div>
        <div class="mangXaHoi">
            <a href="#" class="mxh-bieuTuong">
                <i class="fab fa-facebook-f"></i>
            </a>
            <a href="#" class="mxh-bieuTuong">
                <i class="fab fa-twitter"></i>
            </a>
            <a href="#" class="mxh-bieuTuong">
                <i class="fab fa-google"></i>
            </a>
            <a href="#" class="mxh-bieuTuong">
                <i class="fab fa-linkedin-in"></i>
            </a>
        </div>
    </div>
    <div class="cauHoi">
        <p class="tieuDe">pháp lý & câu hỏi</p>
        <ul>
            <li><a href="#">Tìm kiếm</a></li>
            <li><a href="#">Chủ nghĩa Khắc Kỷ</a></li>
            <li><a href="#">Điều khoản và điều kiện giao dịch chung</a></li>
            <li><a href="#">Chính sách giao nhận sản phẩm</a></li>
            <li><a href="#">Chính sách bảo vệ thông tin cá nhân</a></li>
            <li><a href="#">Chính sách thanh toán</a></li>
            <li><a href="#">Tủ sách hướng nghiệp</a></li>
            <li><a href="#">Chủ nghĩa Khắc Kỷ</a></li>
            <li><a href="#">Hành trang Ngành IT</a></li>
            <li><a href="#">Quà tặng cuộc sống</a></li>
        </ul>
    </div>
    <div class="thongTin">
        <p class="tieuDe">thông tin liên hệ</p>
        <ul>
            <li>Địa chỉ: 96A Trần Phú, P. Mộ Lao, Hà Đông, Hà Nội, Việt Nam</li>
            <li>Điện thoại: 0988888888</li>
            <li>Fax:</li>
            <li>Mail: contact@nhom4.com</li>
        </ul>
    </div>
    <div class="fanpage">
        <p class="tieuDe">fanpage</p>
    </div>
</footer>
<script src="../js/sach.js"></script>
<script src="../js/header.js"></script>
</body>

</html>