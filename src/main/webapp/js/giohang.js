var inputCheckboxs;

/**
 * Chuyển số thành định dạng VNĐ
 * @param {number} soTien
 * @returns string
 */
function dinhDangTienTe(soTien) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(soTien)
}

/**
 * Định ngày ngày
 * @param {string} thoiGianStr
 */
function dinhDangNgay(thoiGianStr) {
    const thoiGian = new Date(thoiGianStr)
    return `${thoiGian.getHours()}:${thoiGian.getMinutes()} - ${thoiGian.getDay()}/${thoiGian.getMonth()}/${thoiGian.getFullYear()}`
}

/**
 * Tính tổng số tiền của một đơn hàng
 * @param {object} donHang
 * @returns string
 */
function tongTienDonHang(donHang) {
    const tongTien = donHang.dongDonHangs.map(dongDonHang => dongDonHang.soLuong * dongDonHang.donGia)
        .reduce((a, b) => a + b, 0)
    return dinhDangTienTe(tongTien)
}

const gioHang = []
async function taiGioHang() {
    const resp = await fetch("https://localhost:8443/api/gio-hang")
    gioHang.push(...await resp.json())

    document.querySelector(".san-pham__danh-sach").innerHTML = gioHang.map((o, i) =>{
        gioHang.push(o.sach)
            return `
              <div class="san-pham">
                <div class="san-pham__sach">
                  <div class="sach__hinh-anh">
                    <img src="${o.sach.hinhAnhSachs[0].duongDan}" alt="${o.sach.hinhAnhSachs[0].duongDan}">
                  </div>
                  <div class="sach__ten-sach">${o.sach.ten}</div>         
                </div>
                <div class="san-pham__so-luong">
                  <form class="them-gio-hang-form">
                    <div class="thong-tin-so-luong">
                      <input type="button" value="-" class="btn-so-luong" onclick="giamSoLuong(${i})">
                      <input type="number" class="soLuong" id="soLuong${i}" value="${o.soLuong}" min="1">
                      <input type="button" value="+" class="btn-so-luong" onclick="tangSoLuong(${i})">
                    </div>
                  </form>
                </div>
                <div class="san-pham__don-gia">${dinhDangTienTe(o.sach.giaTien)}</div>
                <div class="san-pham__thanh-tien" id="thanhTien${i}">${dinhDangTienTe(o.sach.giaTien * o.soLuong)}</div>
                <input type="checkbox">
              </div>`
        }
    ).join('')

    inputCheckboxs = document.querySelectorAll('input[type="checkbox"]')
}

async function datSach() {
    const duLieu = new FormData()
    duLieu.append("diaChi", document.querySelector(".diaChi").value)
    for (let i = 0; i < inputCheckboxs.length; ++i) {
        if (inputCheckboxs[i].checked) {
            duLieu.append("maSach", gioHang[i].sach.ma)
            duLieu.append("soLuong", gioHang[i].soLuong)
        }
    }
    const resp = await fetch("https://localhost:8443/api/nguoi-dung/don-hang", {
        method: "POST",
        body: duLieu
    })
    if (resp.ok) {
        location.assign("https://localhost:8443/nguoi-dung/don-hang?trangThai=dangCho")
    }
    else {
        alert(await resp.json())
    }
}

function tangSoLuong(i) {
    const input = document.getElementById(`soLuong${i}`)
    input.value = ++gioHang[i].soLuong
    const thanhTien = document.getElementById(`thanhTien${i}`)
    thanhTien.innerHTML = dinhDangTienTe(gioHang[i].soLuong * gioHang[i].sach.giaTien)
}

function giamSoLuong(i) {
    const input = document.getElementById(`soLuong${i}`)
    input.value = --gioHang[i].soLuong
    const thanhTien = document.getElementById(`thanhTien${i}`)
    thanhTien.innerHTML = dinhDangTienTe(gioHang[i].soLuong * gioHang[i].sach.giaTien)
}

