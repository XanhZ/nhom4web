<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liên hệ</title>
    <script async src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=initMap"></script>
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lienhe.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
</head>

<body>
<%@ include file="header.jsp"%>
<div class="phan-than">
    <div class="ban-do">
        <iframe class="ptit"
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3725.3050672217873!2d105.78563351485356!3d20.980404986024595!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135accdd8a1ad71%3A0xa2f9b16036648187!2zSOG7jWMgdmnhu4duIEPDtG5nIG5naOG7hyBCxrB1IGNow61uaCB2aeG7hW4gdGjDtG5n!5e0!3m2!1svi!2s!4v1651197890491!5m2!1svi!2s"
                width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                referrerpolicy="no-referrer-when-downgrade"></iframe>
    </div>
    <div class="form-lien-he">
        <ul class="tieu-de-form">
            <li>
                Liên hệ
                <ul class="lien-he-con">
                    <li>
                        <p>Địa chỉ chúng tôi</p>
                        <strong>96A Trần Phú, P. Mộ Lao, Hà Đông, Hà Nội, Việt Nam</strong>
                    </li>
                    <li>
                        <p>Email chúng tôi</p>
                        <strong>contact@nhom4.com</strong>
                    </li>
                    <li>
                        <p>Điện thoại</p>
                        <strong>0988888888</strong>
                    </li>
                    <li>
                        <p>Thời gian làm việc</p>
                        <strong>Thứ 2 đến Thứ 6 từ 8h đến 18h; Thứ 7 và Chủ nhật từ 8h00 đến 17h00</strong>
                    </li>
                </ul>
            </li>
            <li>
                Gửi thắc mắc cho chúng tôi
                <form action="" class="form-thac-mac">
                    <input type="text" name="" placeholder="Tên của bạn" class="chieu-cao-nho do-dai">
                    <span class="do-dai">
							<input type="text" name="" placeholder="Email của bạn" class="chieu-cao-nho">
							<input type="text" name="" placeholder="Số điện thoại của bạn" class="chieu-cao-nho">
						</span>
                    <input type="text" name="" placeholder="Nội dung" class="chieu-cao-lon do-dai">
                    <input type="submit" value="GỬI CHO CHÚNG TÔI" name="" class="submit-form">
                </form>
            </li>
        </ul>
    </div>
</div>
<script src="../js/header.js"></script>
<%@ include file="footer.jsp"%>s
</body>

</html>