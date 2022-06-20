var danhSachSach = document.querySelector("#danhSachSach");
var nutSua;
var nutDangXuat = document.querySelector('#dangXuat');
var nutXoa;
var thanhTimKiem =document.querySelector('input[name="timKiemSach"]');
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
            location.assign('https://localhost:8443/');
        })
        .catch(async function(err) {
            alert("Lỗi !");
        })
}
nutDangXuat.addEventListener('click',dangXuat);
function capNhatCacNut() {
    nutSua = document.querySelectorAll('.nutSua');
    nutXoa = document.querySelectorAll('.nutXoa');
    nutDangXuat = document.querySelector('#dangXuat');
}
function themSuKien() {
    for (let i = 0; i < nutSua.length; i++) {
        nutSua[i].addEventListener('click', function (){
            capNhatSach(nutSua[i]);
        });
    }
    for (let i = 0; i < nutXoa.length; i++) {
        nutXoa[i].addEventListener('click', function (){
            xoaSach(nutXoa[i]);
        });
    }
    thanhTimKiem.addEventListener('input',()=>{
        timKiemSach(thanhTimKiem);
    })
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
function timKiemSach(e){
    let url = '/api/sach?tenSach=';
    url+=e.value;
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
            let htmlDanhSachSach ='';
            console.log(data);
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
                    '<i class="fa-solid fa-pen nutSua" id="'+data[i].ma+'"></i>'+
                    '<i class="fa-solid fa-trash nutXoa" id="'+data[i].ma+'"></i>'+
                    '</div>'+
                    '</td>'+
                    '</tr>';
                console.log(i);
            }
            danhSachSach.innerHTML = htmlDanhSachSach;
        })
        .catch(async function(err) {
            alert("Lỗi !");
        })
}
function capNhatSach(e){
    console.log(e);
    let url = '/admin/sach-chi-tiet?maSach=';
    url+=e.id;
    location.assign(url);
}
function themSach(){
    let url = '/admin/sach-chi-tiet?maSach=0';
    location.assign(url);
}
function xoaSach(e){
    console.log(e);
    let url = '/api/sach/';
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
            let check = confirm("Bạn có chắc chắn muốn xóa sách"+ data.ten + " không?");
            if(check==true){
                console.log(url);
                fetch(url, {
                    method: 'DELETE',
                })
                    .then(resp => {
                        alert("Xóa Thành Công !");
                        taiDuLieu();
                    })
                    .catch(async function(err) {
                        alert("Lỗi !");
                    })
            }
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
            console.log(data);
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
                    '<i class="fa-solid fa-pen nutSua" id="'+data[i].ma+'"></i>'+
                    '<i class="fa-solid fa-trash nutXoa" id="'+data[i].ma+'"></i>'+
                    '</div>'+
                    '</td>'+
                    '</tr>';
                console.log(i);
            }
            danhSachSach.innerHTML = htmlDanhSachSach;
            capNhatCacNut();
            themSuKien();
            nutDangXuat.addEventListener('click',dangXuat);
        })
        .catch(async function(err) {
            alert("Lỗi hệ thống !");
        })
}
taiDuLieu();