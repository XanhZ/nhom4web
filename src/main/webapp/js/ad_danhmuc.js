var danhSachDanhMuc = document.querySelector("#danhSachDanhMuc");
var nutSua;
var modal;
var capNhat;
var nutDangXuat = document.querySelector('#dangXuat');
var nutXoa;
var soLoai=0;
function capNhatCacNut() {
    nutXoa =document.querySelectorAll('.nutXoa');
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
            let maDanhMuc =  document.querySelector('input[name="modalMaDanhMuc"]');
            tenDanhMuc.value = data.ten;
            thoiGianTao.value =data.thoiGianTao;
            capNhatGanNhat.value = data.thoiGianCapNhat;
            maDanhMuc.value=data.ma;
        })
        .catch(async function(err) {
            alert("Lỗi !");
        })
}
function soLoaiSach(e){
    let url = '/api/sach?danhMuc=';
    url+=e;
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
            console.log("60");
            soLoai = data.length;
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
function xoaDanhMuc(e){
    console.log(e);
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
            let check = confirm("Bạn có chắc chắn muốn xóa danh mục"+ data.tenDanhMuc + " không?");
            if(check==true){
                console.log(url);
                fetch(url, {
                    method: 'DELETE',
                })
                    .then(resp => {
                        alert("Xóa Thành Công !");
                        taiDanhSachDanhMuc();
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
function themSuKien() {
    for (let i = 0; i < nutSua.length; i++) {
        nutSua[i].addEventListener('click', function (){
            hienThiDanhMuc(nutSua[i]);
        });
    }
    for (let i = 0; i < nutXoa.length; i++) {
        nutXoa[i].addEventListener('click', function (){
            xoaDanhMuc(nutXoa[i]);
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
            location.assign('https://localhost:8443/');
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
                // soLoaiSach(data[i].ma);
                // console.log(soLoai);
                htmlDanhSachDangMuc+=''+
                    '<tr>'+
                    '<td>'+data[i].ten+'</td>'+
                    '<td>'+data[i].thoiGianTao+'</td>'+
                    '<td>'+data[i].thoiGianCapNhat+'</td>'+
                    // '<td>'+soLoai+'</td>'+
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
            alert("Lỗi hệ thống 194!");
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