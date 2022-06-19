<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Danh mục</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_danhmuc.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
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

            <li class="hoatDong">
                <a href="">
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
                <form onsubmit="return themSach()" class="bangThemDanhMuc">
                    <input type="text" name="tenDanhMuc" placeholder="Tên danh mục">
                    <input type="button" onclick="themDanhMuc()" value="Thêm danh mục" class="nutThemSach">
                </form>
                <table>
                    <thead>
                    <tr>
                        <td>Danh mục</td>
                        <td>Thời gian tạo</td>
                        <td>Thời gian cập nhập</td>
                        <td>Số loại sách</td>
                    </tr>
                    </thead>

                    <tbody id="danhSachDanhMuc">
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
                Sửa Danh Mục
            </div>
            <div class="modal-thanhphansach">
                <form action="">
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " readonly name="modalMaDanhMuc">
                        <label class="nhanNhapLieu">Mã Danh Mục</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " name="modalTenDanhMuc">
                        <label class="nhanNhapLieu">Tên danh mục</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " readonly name="modalThoiGianTao">
                        <label class="nhanNhapLieu">Thời gian tạo</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " readonly name="modalCapNhatGanNhat">
                        <label class="nhanNhapLieu">Cập nhật gần nhất</label>
                    </div>
                    <div class="truongNhapLieu">
                        <input type="text" class="nhapLieu" placeholder=" " readonly name="modalSoLuongSach">
                        <label class="nhanNhapLieu">Số lượng sách</label>
                    </div>
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
    var danhSachDanhMuc = document.querySelector("#danhSachDanhMuc");
    var nutSua;
    var modal;
    var capNhat;
    var nutDangXuat = document.querySelector('#dangXuat');;
    function capNhatCacNut() {
        nutSua = document.querySelectorAll('.nutSua');
        modal = document.querySelector('.modal');
        capNhat = document.querySelector('.modal-nutsua button');
    }
    function hienThiModal(e){
        modal.classList.toggle('hide');
    }
    function hienThiDanhMuc(e){
        hienThiModal();
        let url = '/api/danh-muc/';
        url+=e.id;
        fetch(url, {
            method: 'GET',
        })
            .then(response => {
                if (response.status !== 200 && response.status !== 201) {
                    throw response
                }
                return response.json()
            })
            .then(data => {
                let tenDanhMuc = document.querySelector('input[name="modalTenDanhMuc"]');
                let thoiGianTao = document.querySelector('input[name="modalThoiGianTao"]');
                let capNhatGanNhat = document.querySelector('input[name="modalCapNhatGanNhat"]');
                let soLuongSach = document.querySelector('input[name="modalSoLuongSach"]');
                let maDanhMuc =  document.querySelector('input[name="modalMaDanhMuc"]');
                tenDanhMuc.value = data.ten;
                thoiGianTao.value =data.thoiGianTao;
                capNhatGanNhat.value = data.thoiGianCapNhat;
                soLuongSach.value = 20;
                maDanhMuc.value=data.ma;
            })
            .catch(async function(err) {
                alert("Lỗi !");
            })
    }
    function capNhatDanhMuc(){
        hienThiModal();
        let url = '/api/danh-muc/';
        let maDanhMuc = document.querySelector('input[name="modalMaDanhMuc"]').value;
        let tenDanhMuc = document.querySelector('input[name="modalTenDanhMuc"]').value;
        url+=maDanhMuc
        const foo = new FormData();
        foo.append('tenDanhMuc',tenDanhMuc);
        fetch(url, {
            method: 'PUT',
            body: foo,
        })
            .then(response => {
                if (response.status !== 200 && response.status !== 201) {
                    throw response
                }
                return response.json()
            })
            .then(data => {
                alert("Cập nhật danh mục thành công !");
                taiDanhSachDanhMuc();
            })
            .catch(async function(err) {
                alert("Lỗi !");
            })
    }
    function themSuKien() {
        for (let i = 0; i < nutSua.length; i++) {
            nutSua[i].addEventListener('click', function (){
                hienThiDanhMuc(nutSua[i]);
            });
        }
        modal.addEventListener('click', function (e) {
            if (e.target == modal.childNodes[3]) {
                hienThiModal();
            }
        });
        capNhat.addEventListener('click', function (){
            capNhatDanhMuc();
        });
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
    nutDangXuat.addEventListener('click',dangXuat);
    function taiDanhSachDanhMuc(){
        fetch('/api/danh-muc',{
            method:'GET'
        })
            .then(resp => resp.json())
            .then(data=>{
                let htmlDanhSachDangMuc = '';
                for(let i=0;i<data.length;i++){
                    htmlDanhSachDangMuc+=''+
                        '<tr>'+
                        '<td>'+data[i].ten+'</td>'+
                        '<td>'+data[i].thoiGianTao+'</td>'+
                        '<td>'+data[i].thoiGianCapNhat+'</td>'+
                        '<td>20</td>'+
                        '<td>'+
                        '<div class="chinhSua">'+
                        '<i class="fa-solid fa-pen nutSua" id="'+data[i].ma+'"></i>'+
                        '<i class="fa-solid fa-trash nutXoa" id="'+data[i].ma+'"></i>'+
                        '</div>'+
                        '</td>'+
                        '</tr>';
                }
                danhSachDanhMuc.innerHTML = htmlDanhSachDangMuc;
                capNhatCacNut();
                themSuKien();
            })
            .catch(async function(err) {
                alert("Lỗi hệ thống !");
            })
    }

    taiDanhSachDanhMuc();
    async function themDanhMuc(){
        let tenDanhMuc = document.querySelector('input[name="tenDanhMuc"]').value;
        let foo = new FormData();
        foo.append('tenDanhMuc', tenDanhMuc);
        console.log(foo);
        const resp = await fetch('/api/danh-muc', {
            method:'POST',
            body: foo,
        });
        const data = await resp.json();
        if (resp.ok) {
            alert("Thêm danh mục thành công !");
            taiDanhSachDanhMuc();
        }
        else {
            if (data !== undefined && data.tenDanhMuc !== undefined) {
                alert(data.tenDanhMuc)
            }
        }
    }
</script>
</html>