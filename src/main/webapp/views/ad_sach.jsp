<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Sách</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_sach.css">
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

            <li>
                <a href="${pageContext.request.contextPath}/views/ad_khachhang.jsp">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-users"></i>
                        </span>
                    <span class="tieuDe">Khách hàng</span>
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/views/ad_danhmuc.jsp">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-book-bookmark"></i>
                        </span>
                    <span class="tieuDe">Danh mục</span>
                </a>
            </li>

            <li class="hoatDong">
                <a href="">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-book"></i>
                        </span>
                    <span class="tieuDe">Sách</span>
                </a>
            </li>

            <li>
                <a id="dangXuat" href="#">
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
<%--                <form onsubmit="return themSach()" class="bangThemSach">--%>
<%--                    <input type="text" placeholder="Tên sách">--%>
<%--                    <input type="text" placeholder="Danh mục">--%>
<%--                    <input type="text" placeholder="Giá">--%>
<%--                    <input type="text" placeholder="Số lượng">--%>
<%--                    <input type="text" placeholder="Đường dẫn ảnh">--%>
<%--                    <input type="submit" value="Thêm sách" class="nutThemSach">--%>
<%--                </form>--%>
                <table>
                    <thead>
                    <tr>
                        <td>Tên</td>
                        <td>Giá bán</td>
                        <td>Số lượng tồn</td>
                        <td>Cập nhật gần đây</td>
                    </tr>
                    </thead>

                    <tbody id="danhSachSach">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%--Modal--%>
<div class="modal hide">
    <div class="modal-nen">
    </div>
    <div class="modal-noidung">
        <div class="modal-noidungchinh">
            <div class="modal-tieude">
                Xóa sách
            </div>
            <div class="modal-thanhphansach">
                <form action="">
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Tên sách</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="password" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Danh mục sách</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="password" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Giá sách</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="password" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Số lượng</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="password" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Đường dẫn ảnh</label>
                    </div>
                </form>
            </div>
            <div class="modal-nutsua">
                <button type="button">Xác nhận xóa</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
<script>
    var danhSachSach = document.querySelector("#danhSachSach");
    var nutXoa;
    var modal;
    var capNhat;
    var nutDangXuat;
    function capNhatCacNut() {
        nutXoa = document.querySelectorAll('.nutXoa');
        modal = document.querySelector('.modal');
        capNhat = document.querySelector('.modal-nutsua button');
        nutDangXuat = document.querySelector('#dangXuat');
    }
    function hienThiModal(e){
        modal.classList.toggle('hide');
    }
    function capNhatSach() {
        for (let i = 0; i < nutXoa.length; i++) {
            nutXoa[i].addEventListener('click', hienThiModal);
        }
        modal.addEventListener('click', function (e) {
            // console.log(e.target);
            // console.log(modal.childNodes[3]);
            if (e.target == modal.childNodes[3]) {
                hienThiModal();
            }
        });
        capNhat.addEventListener('click', hienThiModal);
    }
    function dangXuat(){
        fetch('/api/dang-xuat', {
            method: 'POST',
        })
            .then(response => {
                if (response.status !== 200 && response.status !== 201) {
                    throw response
                }
                return response.json()
            })
            .then(data => {
                location.assign('${pageContext.request.contextPath}/views/trangchu.jsp');
            })
            .catch(async function(err) {
                alert("Lỗi !");
            })
    }
    function taiDuLieu(){
        fetch('/api/sach', {
            method: 'GET',
        })
            .then(response => response.json())
            .then(data => {
                let htmlDanhSachSach ='';
                for(let i=0;i<data.length;i++){
                    htmlDanhSachSach+=''+
                        '<tr>'+
                            '<td>'+data[i].ten+'</td>'+
                            '<td>'+data[i].giaTien+'</td>'+
                            '<td>'+data[i].soLuongTrongKho+'</td>'+
                            '<td>'+data[i].thoiGianCapNhat+'</td>'+
                            '<td><img src="'+data[i].hinhAnhSachs[0].duongDan+'"></img></td>'+
                            '<td>'+
                                '<div class="chinhSua">'+
                                    '<i class="fa-solid fa-trash nutXoa"></i>'+
                                '</div>'+
                            '</td>'+
                        '</tr>';
                }
                danhSachSach.innerHTML = htmlDanhSachSach;
                capNhatCacNut();
                capNhatSach();
                nutDangXuat.addEventListener('click',dangXuat);
            })
            .catch(async function(err) {
                alert("Lỗi hệ thống !");
            })
    }
    taiDuLieu();
</script>
</html>