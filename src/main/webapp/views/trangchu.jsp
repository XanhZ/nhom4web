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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trangchu.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
</head>

<body>
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
                    <li><a href="#">Sách hướng nghiệp</a></li>
                    <li><a href="#">Sách IT</a></li>
                    <li><a href="#">Sách văn học</a></li>
                    <li><a href="#">Sách toán học</a></li>
                    <li><a href="#">Sách kỹ năng</a></li>
                </ul>
            </div>
            <a href="/gioi-thieu" class="dieuHuong__duongDan">Giới thiệu</a>
            <a href="/lien-he" class="dieuHuong__duongDan">Liên hệ</a>
            <a href="/dang-nhap" class="dieuHuong__duongDan">Đăng nhập</a>
            <a href="/dang-ky" class="dieuHuong__duongDan">Đăng ký</a>
        </nav>
    </div>
</header>
<div id="thungQuangCao">
    <div class="quangCao">
    </div>
</div>
<div class="sanPham">
    <div class="loaiSanPham">
        <div class="tieuDe">
            <p>Sản phẩm nổi bật</p>
        </div>
        <div class="trungBay">
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
        </div>
    </div>
    <div class="loaiSanPham">
        <div class="tieuDe">
            <p>Sách hướng nghiệp</p>
        </div>
        <div class="trungBay">
            <a href="/san-pham" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
        </div>
    </div>
    <div class="loaiSanPham">
        <div class="tieuDe">
            <p>Hành trang ngành it</p>
        </div>
        <div class="trungBay">
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
            <a href="sanpham.html" class="sach">
                <div class="hinh-anh-sach">
                    <span class="giamGia">-12%</span>
                    <img
                            src="https://product.hstatic.net/200000123069/product/seneca_shop_spioderum_c074ac85b0ed4b81a28cff98b7135e20_grande.png">
                    <div class="manMo">
                        <button type="button" class="btn-mua">Mua</button>
                        <button type="button" class="btn-them-vao-gio">Thêm vào giỏ </button>
                    </div>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">Sách Chủ nghĩa Khắc kỷ - Seneca: Những Bức Thư Đạo Đức - Triết học thức hành Đi tìm bình yên trong tâm trí</div>
                    <div class="gia-ban">129,000₫</div>
                </div>
            </a>
        </div>
    </div>
</div>
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
            <li><a href="#">Giới thiệu</li>
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
</body>

</html>