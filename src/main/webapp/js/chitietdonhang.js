let maDonHang = undefined
const mTrangThai = new Map([
    ["dangCho", "Đang Chờ Xác Nhận"],
    ["xacNhan", "Đã Xác Nhận"],
    ["huy", "Đã Hủy"]
])

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

/**
 * Lấy đơn hàng theo mã
 * @param {number} ma
 * @returns {Promise<void>}
 */
async function layDonHang(ma) {
  maDonHang = ma
  const resp = await fetch(`/api/nguoi-dung/don-hang/${ma}`)
  const donHang = await resp.json()
  if (donHang.trangThai == "dangCho") {
    document.querySelector(".don-hang__thao-tac").innerHTML = `
      <button class="btn-huy" onclick="huyDatHang()">Hủy Đặt Hàng</button>
      <div class="don-hang__tong-tien">
        <span class="tong-tien-nhan">Tổng Thanh Toán</span>
        <span class="tong-tien-gia-tri">${tongTienDonHang(donHang)}</span>
      </div>
    `
  }
  else {
    document.querySelector(".don-hang__thao-tac").innerHTML = `
      <div class="don-hang__tong-tien">
        <span class="tong-tien-nhan">Tổng Thanh Toán</span>
        <span class="tong-tien-gia-tri">${tongTienDonHang(donHang)}</span>
      </div>
    `
  }

  document.querySelector(".don-hang-dia-chi .noi-dung").innerHTML = donHang.diaChi
  document.querySelector(".don-hang-thoi-gian-tao .noi-dung").innerHTML = dinhDangNgay(donHang.thoiGianTao)
  document.querySelector(".don-hang-thoi-gian-cap-nhat .noi-dung").innerHTML = dinhDangNgay(donHang.thoiGianCapNhat)
  document.querySelector(".don-hang-trang-thai .noi-dung").innerHTML = mTrangThai.get(donHang.trangThai)
  document.querySelector(".san-pham__danh-sach").innerHTML = donHang.dongDonHangs.map(dongDonHang => `
      <div class="san-pham">
        <div class="san-pham__sach">
          <div class="sach__hinh-anh">
            <img src="${dongDonHang.sach.hinhAnhSachs[0].duongDan}" alt="${dongDonHang.sach.hinhAnhSachs[0].duongDan}">
          </div>
          <div class="sach__ten-sach">${dongDonHang.sach.ten}</div>         
        </div>
        <div class="san-pham__so-luong">${dongDonHang.soLuong}</div>
        <div class="san-pham__don-gia">${dinhDangTienTe(dongDonHang.donGia)}</div>
        <div class="san-pham__thanh-tien">${dinhDangTienTe(dongDonHang.donGia * dongDonHang.soLuong)}</div>
      </div>`
  ).join('')
}

async function huyDatHang() {
  const trangThai = new FormData();
  trangThai.append("trangThai", "huy")
  const resp = await fetch(`/api/nguoi-dung/don-hang/${maDonHang}`, {
    method: 'PUT',
    body: trangThai
  })
  if (resp.ok) {
    const donHang = await resp.json()
    document.querySelector(".don-hang__thao-tac").innerHTML = `
      <div class="don-hang__tong-tien">
        <span class="tong-tien-nhan">Tổng Thanh Toán</span>
        <span class="tong-tien-gia-tri">${tongTienDonHang(donHang)}</span>
      </div>
    `
    document.querySelector(".don-hang-thoi-gian-cap-nhat .noi-dung").innerHTML = dinhDangNgay(donHang.thoiGianCapNhat)
    document.querySelector(".don-hang-trang-thai .noi-dung").innerHTML = mTrangThai.get(donHang.trangThai)
  }
  else {
    alert("Không thể hủy đơn hàng")
  }
}