<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đơn hàng</title>
  <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="../css/donHang.css">
  <link rel="icon" href="../img/logo.png" sizes="32x32">
</head>
<body onload="layDonHang('<%= request.getParameter("trangThai") %>')">
<%@ include file="header.jsp" %>
<main>
  <div class="danh-sach-don-hang">
    <div class="danh-sach-don-hang__dieu-huong">
      <a class="<%= "dieu-huong-duong-dan" + (request.getParameter("trangThai").equals("dangCho") ? " active" : "") %>"
         href="/nguoi-dung/don-hang?trangThai=dangCho">Đang Chờ</a>
      <a class="<%= "dieu-huong-duong-dan" + (request.getParameter("trangThai").equals("xacNhan") ? " active" : "") %>"
         href="/nguoi-dung/don-hang?trangThai=xacNhan">Đã Xác Nhận</a>
      <a class="<%= "dieu-huong-duong-dan" + (request.getParameter("trangThai").equals("huy") ? " active" : "") %>"
         href="/nguoi-dung/don-hang?trangThai=huy">Đã Hủy</a>
    </div>
    <div id="danh-sach" class="danh-sach-don-hang__noi-dung"></div>
  </div>
</main>
<%@ include file="footer.jsp" %>
</body>
<script src="../js/donhang.js"></script>
</html>