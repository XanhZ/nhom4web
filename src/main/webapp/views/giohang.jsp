<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Book Shop</title>
  <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="../../css/giohang.css">
  <link rel="icon" href="../../img/logo.png" sizes="32x32">
</head>

<body onload="taiGioHang()">
<%@ include file="header.jsp"%>
<div class="thungDung">
  <h1>Thông tin giỏ hàng</h1>
  <div class="noi-dung__sach">
    <div>
      <div class="don-hang__san-pham">
        <div class="san-pham__noi-dung">
          <div class="noi-dung__sach">Sách</div>
          <div class="noi-dung__so-luong">Số Lượng</div>
          <div class="noi-dung__don-gia">Đơn Giá</div>
          <div class="noi-dung__thanh-tien">Thành Tiền</div>
        </div>
        <div class="san-pham__danh-sach"></div>
      </div>
    </div>
  </div>
  <form class="bangThanhToan">
    <div>
      <input type="text" placeholder="Địa chỉ" class="diaChi">
    </div>
    <button type="button" class="nutGui" onclick="datSach()">Đặt Hàng</button>
  </form>
</div>
<%@ include file="footer.jsp"%>
<script src="../../js/header.js"></script>
<script type="text/javascript" src="../../js/giohang.js"></script>
</body>
</html>