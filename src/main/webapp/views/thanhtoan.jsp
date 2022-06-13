<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Book Shop</title>
  <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/thanhToan.css">
  <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
</head>

<body>
<%@ include file="header.jsp"%>
<div class="thungDung">
  <h1>Thông tin thanh toán</h1>
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
    <div>
      <div class="sach__thong-tin">
      </div>
      <form onsubmit="return themSach()" class="bangThanhToan">
        <input type="text" placeholder="Họ và tên">
        <input type="text" placeholder="Số điện thoại người nhận">
        <input type="text" placeholder="Địa chỉ">
        <span>Thanh toán khi giao hàng</span>
        <input type="submit" value="Gửi" class="nutGui">
      </form>
    </div>
  </div>
</div>
<%@ include file="footer.jsp"%>
<script src="../js/header.js"></script>
<script type="text/javascript" src="../js/thanhtoan.js"></script>
</body>

</html>