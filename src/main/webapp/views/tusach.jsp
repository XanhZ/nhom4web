<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tủ sách</title>
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
    <div class="sach">
    </div>
</main>
</body>
<script src="../js/header.js"></script>
<script src="../js/tusach.js"></script>
</html>
