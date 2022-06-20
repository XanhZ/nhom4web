let maDonHang = document.querySelector('.thungChua').id;
function xacNhanDonHang(){
    let check =confirm("Bạn có chắc chắn muốn xác nhận đơn hàng này không?");
    if(check==true){
        let url='/api/don-hang/';
        url+=maDonHang;
        duLieu =new FormData();
        duLieu.append('trangThai','xacNhan');
        fetch(url, {
            method: 'PUT',
            body:duLieu,
        })
            .then(resp => {
                alert("Xác nhận thành Công !");
                location.assign('/admin/don-hang');
            })
            .catch(async function(err) {
                alert("Lỗi !");
            })
    }
}
function huyDonHang(){
    let check =confirm("Bạn có chắc chắn muốn hủy đơn hàng này không?");
    if(check==true){
        let url='/api/don-hang/';
        url+=maDonHang;
        duLieu =new FormData();
        duLieu.append('trangThai','huy');
        fetch(url, {
            method: 'PUT',
            body:duLieu,
        })
            .then(resp => {
                alert("Hủy thành Công !");
                location.assign('/admin/don-hang');
            })
            .catch(async function(err) {
                alert("Lỗi !");
            })
    }
}
function xoaDonHang(){
    let check =confirm("Bạn có chắc chắn muốn xóa đơn hàng này không?");
    if(check==true){
        let url ='/api/don-hang/';
        url+=maDonHang;
        fetch(url, {
            method: 'DELETE',
        })
            .then(resp => {
                alert("Xóa Thành Công !");
                location.assign('/admin/don-hang');
            })
            .catch(async function(err) {
                alert("Lỗi !");
            })
    }
}
function taiDuLieu(){
    let url ='/api/don-hang/';
    url+=maDonHang;
    fetch(url, {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            let tenKhachHang = document.querySelector('#tenKhachHang');
            let sdtKhachHang = document.querySelector('#sdtKhachHang');
            let emailKhachHang =document.querySelector('#emailKhachHang');
            let diaChiKhachHang = document.querySelector('#diaChiKhachHang');
            tenKhachHang.innerHTML = data.nguoiDung.ten;
            sdtKhachHang.innerHTML = data.nguoiDung.sdt;
            emailKhachHang.innerHTML = data.nguoiDung.email;
            diaChiKhachHang.innerHTML = data.diaChi;

            let sachDatMua = document.querySelector('#danhSachSach');
            let htmlDanhSachSach = '';
            let tongTien = 0;
            for(let i=0;i<data.dongDonHangs.length;i++){
                tongTien+= (data.dongDonHangs[i].donGia * data.dongDonHangs[i].soLuong);
                htmlDanhSachSach+=''+
                    '<tr>'+
                    '<td>'+data.dongDonHangs[i].sach.ten+'</td>'+
                    '<td>'+data.dongDonHangs[i].soLuong+'</td>'+
                    '<td>'+data.dongDonHangs[i].donGia+'</td>'+
                    '</tr>';
            }
            htmlDanhSachSach+=''+
                '<tr>'+
                '<td class="tongTien" colspan="2">Tổng tiền</td>'+
                '<td class="tongTien" colspan="1">'+tongTien+'</td>'+
                '</tr>';
            sachDatMua.innerHTML =htmlDanhSachSach;
        })
        .catch(async function(err) {
            alert("Lỗi hệ thống !");
        })
}
taiDuLieu();