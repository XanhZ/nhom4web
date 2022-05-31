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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gioithieu.css">
    <link rel="icon" href="${pageContext.request.contextPath}/ìmg/logo.png" sizes="32x32">
</head>

<body>
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
            <a href="dangnhap.html" class="dieuHuong__duongDan">Đăng nhập</a>
            <a href="dangky.html" class="dieuHuong__duongDan">Đăng ký</a>
        </nav>
    </div>
</header>
<main class="phan-than">
    <div class="gioi-thieu-con">
        <div class="phan-dau-gioi-thieu">
            <h1>Giới thiệu</h1>
        </div>
        <div class="noi-dung">
            <p>Từ một cộng đồng của những người đam mê viết và chia sẻ kiến thức, Spiderum đã không ngừng phát triển và
                cho ra mắt bộ ba cuốn sách tập hợp bài viết của nhiều tác giả dành cho người trẻ: <strong>Dăm ba cái tuổi
                    trẻ, Du học kí: Vạn dặm có chi? và Người trong muôn nghề và Người trong muôn nghề: Ngành IT có
                    gì?</strong></p>
            <p>Kết tinh từ góc nhìn của chính những người trẻ đầy bản lĩnh giữa muôn vàn môi trường sống, mỗi sản phẩm của
                Spiderum lại phản ánh một vấn đề nổi bật trong xã hội một cách toàn diện nhưng không kém phần gai góc khiến
                nhiều bạn trẻ khác ngoài kia đang cùng trăn trở: Mình nên đọc gì để làm phong phú tâm hồn, có những kĩ năng
                cần thiết và hiểu được rằng thế giới ngoài kia vận hành như thế nào.</p>
            <p>Thấu hiểu được những trăn trở đó, cửa hàng của Spiderum được ra đời để đồng hành, giới thiệu đến các bạn
                những cuốn sách đầy tâm huyết của đội ngũ Spiderum cùng nhiều dự định chúng tôi đang ấp ủ trong tương lai.
                Bên cạnh đó, rất nhiều những cuốn sách bổ ích khác đến từ bất cứ thể loại nào cũng sẽ góp mặt tại đây qua
                các chuyên mục review, chia sẻ sách hay. Chúng tôi mong muốn sẽ tạo ra một nền tảng tương tác thân thiện,
                gần gũi và hữu dụng, gắn kết những tâm hồn yêu sách đang không ngừng tìm tòi, học hỏi để phát triển bản
                thân.</p>
            <p>Hãy cùng Nhện Book thám hiểm thế giới sách và lan tỏa văn hóa đọc nhé! Các bạn có thể tìm hiểu thêm thông
                tin chi tiết về dòng sách của Spiderum tại đây: </p>
            <p>1. Dăm Ba Cái Tuổi Trẻ </p>
            <p>2. Du học kí: Vạn dặm có chi? </p>
            <p>3. Người Trong Muôn Nghề</p>
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