let maSach = document.querySelector('main').id;
let tatCaDanhMuc = [];
let danhMucSachCuaMotSach = [];
async function taiTatCaDanhMuc(){
    fetch('/api/danh-muc',{
        method:'GET'
    })
        .then(resp => resp.json())
        .then(data=>{
            console.log("TaiTatCaDanhMuc");
            tatCaDanhMuc = data;
            let danhMucs = document.querySelector('#tatCaDanhMuc');
            let htmlDanhMucs = '';
            for(let i=0;i<tatCaDanhMuc.length;i++){
                htmlDanhMucs+=''+
                    '<li>'+
                    '<div class="thanhphan">'+
                    '<label for="'+tatCaDanhMuc[i].ma+'">'+tatCaDanhMuc[i].ten+'</label>'+
                    '<input class="checkboxSach" type="checkbox" id="'+tatCaDanhMuc[i].ma+'">'+
                    '</div>'+
                    '</li>';
            }
            danhMucs.innerHTML=htmlDanhMucs;
        })
        .catch(async function(err) {
            alert("Lỗi hệ thống!");
        })
}
function danhMucCuaMotSach(){
    let url = '/api/sach/';
    url+=maSach;
    url+='/phan-loai-sach';
    fetch(url, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log("danhMucCuaMotSach");
            danhMucSachCuaMotSach = data;
            console.log(data);
            for(let i=0;i<data.length;i++){
                let maDanhMuc ='input[id="';
                maDanhMuc+=data[i].danhMuc.ma;
                maDanhMuc+='"]';
                let theInput = document.querySelector(maDanhMuc);
                theInput.setAttribute("checked", "checked");
            }
        })
        .catch(async function(err) {
            // alert("Lỗi hệ thống!");
        })
}
function capNhatSach(){
    let tenSach = document.querySelector('input[name="tenSach"]');
    let giaSach = document.querySelector('input[name="giaSach"]');
    let soLuong = document.querySelector('input[name="soLuong"]');
    let danhMucSach = document.querySelectorAll('.checkboxSach');
    let duLieu = new FormData();
    for(let i=0;i<danhMucSach.length;i++){
        if(danhMucSach[i].checked == true){
            duLieu.append('maDanhMuc',danhMucSach[i].id);
        }
    }
    let hinhAnhSach = document.querySelector('input[name="hinhAnhs"]');
    duLieu.append('tenSach',tenSach.value);
    duLieu.append('giaTien',giaSach.value);
    duLieu.append('soLuongTrongKho',soLuong.value);
    for (let i = 0; i < hinhAnhSach.files.length; ++i) {
        duLieu.append("anh", hinhAnhSach.files[i]);
    }
    let url = '/api/sach/';
    url+=maSach;
    fetch(url, {
        method: 'PUT',
        body: duLieu,
    })
        .then(response => response.json())
        .then(data => {
            alert("Cập nhật thành công !");
        })
        .catch(async function(err) {
            alert("Lỗi hệ thống!");
        })
}
function themSach(){
    let tenSach = document.querySelector('input[name="tenSach"]');
    let giaSach = document.querySelector('input[name="giaSach"]');
    let soLuong = document.querySelector('input[name="soLuong"]');
    let danhMucSach = document.querySelectorAll('.checkboxSach');
    let duLieu = new FormData();
    for(let i=0;i<danhMucSach.length;i++){
        if(danhMucSach[i].checked == true){
            duLieu.append('maDanhMuc',danhMucSach[i].id);
        }
    }
    let hinhAnhSach = document.querySelector('input[name="hinhAnhs"]');
    duLieu.append('tenSach',tenSach.value);
    duLieu.append('giaTien',giaSach.value);
    duLieu.append('soLuongTrongKho',soLuong.value);
    for (let i = 0; i < hinhAnhSach.files.length; ++i) {
        duLieu.append("anh", hinhAnhSach.files[i]);
    }
    let url = '/api/sach';
    fetch(url, {
        method: 'POST',
        body: duLieu,
    })
        .then(response => response.json())
        .then(data => {
            alert("Thêm thành công !");
        })
        .catch(async function(err) {
            alert("Lỗi hệ thống!");
        })
}
async function taiDuLieu(){
    let url = '/api/sach/';
    url+=maSach;
    fetch(url, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log("taiDuLieu");
            let tenSach = document.querySelector('input[name="tenSach"]');
            let giaSach = document.querySelector('input[name="giaSach"]');
            let soLuong = document.querySelector('input[name="soLuong"]');
            tenSach.value = data.ten;
            giaSach.value = data.giaTien;
            soLuong.value = data.soLuongTrongKho;
            let hinhAnhAlbum = document.querySelector('.hinh-anh__album');
            let htmlHinhAnhAlbum ='';
            for(let i=0;i<data.hinhAnhSachs.length;i++){
                htmlHinhAnhAlbum+=''+
                    '<div class="hinh-anh--don dang-hien-thi">'+
                    '<img src="'+data.hinhAnhSachs[i].duongDan+'">'+
                    '</div>';
            }
            hinhAnhAlbum.innerHTML = htmlHinhAnhAlbum;
            let hinhAnhHienThi = document.querySelector('.hinh-anh-hien-thi');
            let htmlHinhAnhHienThi = ''+
                '<img src="'+data.hinhAnhSachs[0].duongDan+'"/>';
            hinhAnhHienThi.innerHTML = htmlHinhAnhHienThi;
            let tenSachDauTrang = document.querySelector('#tenSachDauTrang');
            tenSachDauTrang.innerHTML = data.ten;
            const jsalbum = document.querySelectorAll(".hinh-anh--don")
            const jshinhAnhHienThi = document.querySelector(".hinh-anh-hien-thi img")
            jsalbum.forEach(img => {
                img.addEventListener("click", () => {
                    const url = img.firstElementChild.getAttribute("src")
                    jsalbum.forEach(ele => ele.classList.remove("dang-hien-thi"))
                    img.classList.add("dang-hien-thi")
                    jshinhAnhHienThi.setAttribute("src", url);
                })
            })
        })
        .catch(async function(err) {
        })
}
async function chayChuongTrinh(){
    await taiDuLieu();
    await taiTatCaDanhMuc();
    danhMucCuaMotSach();
}