<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Khách hàng</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/khachHang_admin.css">
</head>
<body>
<div class="thungChua">
    <div class="dieuHuong">
        <ul>
            <li>
                <a href="#">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-layer-group"></i>
                        </span>
                    <span class="tieuDe logo">TEAM 4</span>
                </a>
            </li>

            <li class="hoatDong">
                <a href="#">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-users"></i>
                        </span>
                    <span class="tieuDe">Khách hàng</span>
                </a>
            </li>

            <li>
                <a href="./category-admin.html">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-book-bookmark"></i>
                        </span>
                    <span class="tieuDe">Danh mục</span>
                </a>
            </li>

            <li>
                <a href="./book_admim.html">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-book"></i>
                        </span>
                    <span class="tieuDe">Sách</span>
                </a>
            </li>

            <li>
                <a href="#">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-right-from-bracket"></i>
                        </span>
                    <span class="tieuDe">Đăng xuất</span>
                </a>
            </li>
        </ul>
    </div>

    <div class="manChinh">
        <div class="topbar">
            <div class="congTac">
                <i class="fa-solid fa-align-justify"></i>
            </div>

            <div class="timKiem">
                <label>
                    <input type="text" placeholder="Tìm kiếm ...">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </label>
            </div>
        </div>


        <div class="noiDung">
            <div class="danhSach">
                <form onsubmit="return themSach()" class="bangThemSach">
                    <input type="text" placeholder="Tên khách hàng">
                    <input type="text" placeholder="Số điện thoại">
                    <input type="email" placeholder="Email">
                    <input type="text" placeholder="Loại người dùng ( 0 - 1 )">
                    <input type="submit" value="Thêm người dùng" class="nutThemSach">
                </form>
                <table>
                    <thead>
                    <tr>
                        <td>Tên khách hàng</td>
                        <td>Số điện thoại</td>
                        <td>Email</td>
                        <td>Loại người dùng</td>
                        <td></td>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Nguyễn Văn A</td>
                        <td>0123456789</td>
                        <td>vana@gmail.com</td>
                        <td>1</td>
                        <td></td>
                        <td>
                            <div class="chinhSua">
                                <i class="fa-solid fa-pen nutSua"></i>
                                <i class="fa-solid fa-trash nutXoa"></i>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>

</html>