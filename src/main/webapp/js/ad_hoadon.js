var donHang;
var nutXoa;
var nutXacNhan;
var nutDangXuat = document.querySelector('#dangXuat');
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
function capNhatCacNut(){
    donHang = document.querySelectorAll('.don-hang');
    nutXoa = document.querySelectorAll('.nutXoa');
    nutXacNhan = document.querySelectorAll('.nutXacNhan');
}
function xoaDonHang(e){
    let check =confirm("Bạn có chắc chắn muốn xóa đơn hàng này không?");
    if(check==true){
        let url ='/api/don-hang/';
        url+=e.id;
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
}
function xemDonHang(e){
    let url = '/admin/don-hang-chi-tiet?maDonHang=';
    url+=e.id;
    location.assign(url);
}
function xacNhanDonHang(e){
    let check =confirm("Bạn có chắc chắn muốn xác nhận đơn hàng này không?");
    if(check==true){
        let url='/api/don-hang/';
        url+=e.id;
        duLieu =new FormData();
        duLieu.append('trangThai','xacNhan');
        fetch(url, {
            method: 'PUT',
            body:duLieu,
        })
            .then(resp => {
                alert("Xác nhận thành Công !");
                taiDuLieu();
            })
            .catch(async function(err) {
                alert("Lỗi !");
            })
    }
}
function themSuKien(){
    nutXoa.forEach(e=>{
        e.addEventListener('click', (i)=>{
            i.stopPropagation();
            xoaDonHang(e);
        })
    });
    nutXacNhan.forEach(e=>{
        e.addEventListener('click', (i)=>{
            i.stopPropagation();
            xacNhanDonHang(e);
        })
    })
    donHang.forEach(e=>{
        e.addEventListener('click', ()=>{
            xemDonHang(e);
        })
    })
}
function taiDuLieu(){
    fetch('/api/don-hang?trangThai=dangCho', {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            let danhSachDonHang = document.querySelector('#danhSachDonHang');
            let htmlDanhSachDonHang='';
            for(let i=0;i<data.length;i++){
                let tongTien = 0;
                for(let j=0;j<data[i].dongDonHangs.length;j++){
                    tongTien += Number(data[i].dongDonHangs[j].soLuong * data[i].dongDonHangs[j].donGia);
                }
                htmlDanhSachDonHang+=''+
                    '<tr class="don-hang" id="'+data[i].ma+'">'+
                    '<td>'+data[i].nguoiDung.ten+'</td>'+
                    '<td>'+data[i].nguoiDung.sdt+'</td>'+
                    '<td>'+data[i].diaChi+'</td>'+
                    '<td>'+tongTien+'</td>'+
                    '<td>'+data[i].thoiGianTao+'</td>'+
                    '<td>'+
                    '<div class="chinhSua">'+
                    '<i class="fa-solid fa-circle-check nutXacNhan" id="'+data[i].ma+'"></i>'+
                    '<i class="fa-solid fa-trash nutXoa" id="'+data[i].ma+'"></i>'+
                    '</div>'+
                    '</td>'+
                    '</tr>';
            }
            danhSachDonHang.innerHTML = htmlDanhSachDonHang;
            capNhatCacNut();
            themSuKien();
        })
        .catch(async function(err) {
            alert("Lỗi hệ thống !");
        })
}
taiDuLieu();