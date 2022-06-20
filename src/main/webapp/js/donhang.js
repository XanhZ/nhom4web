const donHangs = []
/**
 * Lấy danh sách đơn hàng theo trạng thái
 * @param {string} trangThai 
 */
async function layDonHang(trangThai) {
  const resp = await fetch(`/api/nguoi-dung/don-hang?trangThai=${trangThai}`)
  const duLieu = await resp.json()
  donHangs.push(...duLieu)
  document.getElementById("danh-sach").innerHTML = donHangs.map(donHang => {
    let spHtml = donHang.dongDonHangs.map(dongDonHang =>
        `
      <div class="san-pham">
        <div class="san-pham__minh-hoa">
          <div class="minh-hoa__hinh-anh">
            <img src="${dongDonHang.sach.hinhAnhSachs[0].duongDan}" alt="${dongDonHang.sach.hinhAnhSachs[0].duongDan}">
          </div>
          <div class="minh-hoa__thong-tin">
            <div class="ten-sach">${dongDonHang.sach.ten}</div>
            <div class="so-luong-mua">x${dongDonHang.soLuong}</div>
          </div>
        </div>
        <div class="san-pham__thanh-tien">${dinhDangTienTe(dongDonHang.donGia)}</div>
      </div>
      `
    ).join('')
    return `<a class="don-hang" href="${`/nguoi-dung/don-hang/chi-tiet?ma=${donHang.ma}`}">
              <div class="don-hang-noi-dung">
                <div class="don-hang__danh-sach-san-pham">${spHtml}</div>
                <div class="don-hang__tong-tien">
                  <span class="tong-tien-nhan">Tổng số tiền:</span>
                  <span class="tong-tien-gia-tri">${tongTienDonHang(donHang)}</span>
                </div>        
              </div>
            </a>`
  }).join('')
}

/**
 * Chuyển số thành định dạng VNĐ
 * @param {number} soTien 
 * @returns string
 */
function dinhDangTienTe(soTien) {
  return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(soTien)
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