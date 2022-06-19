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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_khachhang.css">
</head>
<body>
<div class="thungChua">
    <div class="dieuHuong">
        <ul>
            <li>
                <a href="">
                        <span class="bieuTuong">
                          <i class="fa-solid fa-layer-group"></i>
                        </span>
                    <span class="tieuDe logo">TEAM 4</span>
                </a>
            </li>

            <li class="hoatDong">
                <a href="">
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

            <li>
                <a href="${pageContext.request.contextPath}/views/ad_sach.jsp">
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

                    <tbody id="danhSachKhachHang">
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
                Chỉnh sửa khách hàng
            </div>
            <div class="modal-thanhphansach">
                <form action="">
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Tên khách hàng</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="password" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Số điện thoại</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="password" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Email</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="password" class="nhapLieu" placeholder=" ">
                        <label class="nhanNhapLieu">Loại người dùng</label>
                    </div>
                    <!-- <div class="truongNhapLieu">
                      <input type="password" class="nhapLieu" placeholder=" ">
                      <label class="nhanNhapLieu">Đường dẫn ảnh</label>
                    </div> -->
                </form>
            </div>
            <div class="modal-nutsua">
                <button type="button">Cập nhật</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
<script>
    var danhSachKhachHang = document.querySelector("#danhSachKhachHang");
    function taiDanhSachKhachHang(){
        fetch('/api/danh-muc',{
            method:'GET'
        })
            .then(resp => resp.json())
            .then(data=>{
                let htmlDanhSachKhachHang = '';
                for(let i=0;i<data.length;i++){
                    htmlDanhSachKhachHang+=''+
                        '<tr>'+
                            '<td>'+'hh'+'</td>'+
                            '<td>'+'hh'+'</td>'+
                            '<td>'+'hh'+'</td>'+
                            '<td>'+'hh'+'</td>'
                            '<td>'+'hh'+'</td>'+
                            '<td>'+
                                '<div class="chinhSua">'+
                                    '<i class="fa-solid fa-pen nutSua"></i>'+
                                    '<i class="fa-solid fa-trash nutXoa"></i>'+
                                '</div>'+
                            '</td>'+
                        '</tr>';
                }
                danhSachKhachHang.innerHTML = htmlDanhSachKhachHang;
            })
            .catch(err=>{
                alert("Lỗi hệ thống");
            })
    }
    taiDanhSachKhachHang();
    var nutSua = document.querySelectorAll('.nutSua');
    var modal = document.querySelector('.modal');
    var capNhat = document.querySelector('.modal-nutsua button');
    var nutDangXuat = document.querySelector("#dangXuat");
    function hienThiModal(e){
        modal.classList.toggle('hide');
    }
    for(let i=0;i<nutSua.length;i++){
        nutSua[i].addEventListener('click',hienThiModal);
    }
    modal.addEventListener('click',function(e){
        // console.log(e.target);
        // console.log(modal.childNodes[3]);
        if(e.target == modal.childNodes[3]){
            hienThiModal();
        }
    });
    capNhat.addEventListener('click',hienThiModal);

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
    nutDangXuat.addEventListener('click',dangXuat);
</script>
</html>