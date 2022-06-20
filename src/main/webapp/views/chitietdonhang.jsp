<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đơn hàng</title>
  <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="../../css/chitietdonhang.css">
  <link rel="icon" href="../../img/logo.png" sizes="32x32">
</head>
<body onload="layDonHang(<%= request.getParameter("ma") %>)">
<%@ include file="header.jsp" %>
<main>
  <div class="don-hang">
    <div class="don-hang__chi-tiet">
      <div class="don-hang__nhan">Thông Tin Chung Đơn Hàng</div>
      <div class="don-hang__thong-tin">
        <div class="don-hang-dia-chi">
          <span class="nhan">Địa Chỉ Giao Hàng:</span>
          <span class="noi-dung"></span>
        </div>
        <div class="don-hang-thoi-gian-tao">
          <span class="nhan">Thời Gian Tạo:</span>
          <span class="noi-dung"></span>
        </div>
        <div class="don-hang-thoi-gian-cap-nhat">
          <span class="nhan">Thời Gian Cập Nhật:</span>
          <span class="noi-dung"></span>
        </div>
        <div class="don-hang-trang-thai">
          <span class="nhan">Trạng Thái:</span>
          <span class="noi-dung"></span>
        </div>
      </div>
    </div>
    <div class="don-hang__san-pham">
      <div class="san-pham__noi-dung">
        <div class="noi-dung__sach">Sách</div>
        <div class="noi-dung__so-luong">Số Lượng</div>
        <div class="noi-dung__don-gia">Đơn Giá</div>
        <div class="noi-dung__thanh-tien">Thành Tiền</div>
      </div>
      <div class="san-pham__danh-sach"></div>
    </div>
    <div class="don-hang__thao-tac"></div>
  </div>
</main>
<%@ include file="footer.jsp" %>
<script src="../../js/chitietdonhang.js"></script>
</body>
</html>