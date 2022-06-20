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
      </div>
      <div class="hinh-anh-hien-thi">
        <img>
      </div>
    </div>
    <div>
      <div class="sach__thong-tin">
      </div>
      <form class="them-gio-hang-form">
        <div class="thong-tin-so-luong">
          <input type="button" value="-" class="btn-so-luong">
          <input type="number" name="soLuong" id="soLuong" value="1" min="1">
          <input type="button" value="+" class="btn-so-luong">
        </div>
      </form>
      <form onsubmit="return themSach()" class="bangThanhToan">
        <c:if test="${not empty nguoiDung}">
          <div class="thongTinThanhToan">
            Tên khách hàng : ${nguoiDung.ten}
          </div>
          <div class="thongTinThanhToan">
              Số điện thoại : ${nguoiDung.sdt}
          </div>
        </c:if>
        <input type="text" placeholder="Địa chỉ" class="diaChi">
        <span>Thanh toán khi giao hàng</span>
        <input value="Gửi" class="nutGui">
      </form>
    </div>
  </div>
</div>
<%@ include file="footer.jsp"%>
<script src="../js/header.js"></script>
<script type="text/javascript" src="../js/thanhtoan.js"></script>
</body>
</html>