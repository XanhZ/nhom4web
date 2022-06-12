<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<header>
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
            <c:if test="${not empty nguoiDung}">
                <h1 style="color: white">${nguoiDung.ten}</h1>
            </c:if>
            <c:if test="${empty nguoiDung}">
                <a href="dangnhap.html" class="dieuHuong__duongDan">Đăng nhập</a>
                <a href="dangky.html" class="dieuHuong__duongDan">Đăng ký</a>
            </c:if>
        </nav>
    </div>
</header>

