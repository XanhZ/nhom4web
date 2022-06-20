<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="dongGoi">
        <div class="logo">
            <a href="/trang-chu">BookShop</a>
        </div>
        <div class="timKiem">
            <input type="text" name="q" id="q" placeholder="Tìm kiếm...">
        </div>
        <nav class="dieuHuong">
            <a href="/trang-chu" class="dieuHuong__duongDan">Trang chủ</a>
            <div class="dieuHuong__danhMuc">
                <div class="btn-danhMuc">Tủ sách</div>
                <ul class="danhMuc">
                </ul>
            </div>
            <a href="/gioi-thieu" class="dieuHuong__duongDan">Giới thiệu</a>
            <a href="/lien-he" class="dieuHuong__duongDan">Liên hệ</a>
            <c:if test="${not empty nguoiDung}">
                <a class="gioHang" href="/nguoi-dung/gio-hang">
                   <span class="sl"></span>
                   <i class="fas fa-shopping-cart"></i>
                </a>
                <div class="tenNguoiDung">
                    <a href="/nguoi-dung/don-hang?trangThai=dangCho" style="color: white">
                        ${nguoiDung.ten}
                    </a>
                </div>
                <div class="dieuHuong__duongDan" id="dangXuat">Đăng xuất</div>
            </c:if>
            <c:if test="${empty nguoiDung}">
                <a href="/dang-nhap" class="dieuHuong__duongDan">Đăng nhập</a>
                <a href="/dang-ky" class="dieuHuong__duongDan">Đăng ký</a>
                <a class="gioHang" href="">
                    <span class="sl"></span>
                    <i class="fas fa-shopping-cart"></i>
                </a>
            </c:if>
        </nav>
    </div>
</header>

