const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)

const album = $$(".hinh-anh--don")
const hinhAnhHienThi = $(".hinh-anh-hien-thi img")
const noiDungDuongDan = $(".noi-dung__duong-dan ul")
const thongTinSach = $(".sach__thong-tin")

album.forEach(img => {
  img.addEventListener("click", () => {
    const url = img.firstElementChild.getAttribute("src")
    album.forEach(ele => ele.classList.remove("dang-hien-thi"))
    img.classList.add("dang-hien-thi")
    hinhAnhHienThi.setAttribute("src", url)
  })
})

const chiMuc = window.location.href.indexOf('=') + 1
const maSach = window.location.href.slice(chiMuc)

fetch('/api/sach/' + maSach)
    .then(function (resp){
      return resp.json()
    })
    .then(function (sach) {
      const li = document.createElement("li")
      const tenSach1 = document.createTextNode(sach.ten)
      li.appendChild(tenSach1)
      noiDungDuongDan.appendChild(li)

      const div = document.createElement("div")
      div.classList.add("gia-ban")
      const span = document.createElement("span")
      const giaBan = document.createTextNode(`Giá bán: ${sach.giaTien}`)
      span.classList.add("gia-ban__thuc-te")
      span.appendChild(giaBan)
      div.appendChild(span)
      thongTinSach.insertBefore(div, thongTinSach.children[0])

      const h2 = document.createElement("h2")
      const tenSach2 = document.createTextNode(sach.ten)
      h2.appendChild(tenSach2)
      thongTinSach.insertBefore(h2, thongTinSach.children[0])
    })
    .catch(function (loi) {
      console.log(loi)
    })

const sanPhamLienQuan = $(".noi-dung__sp-lien-quan .loaiSanPham .trungBay")
const api_sanPhamLienQuan = `api/sach/${maSach}/lien-quan`;
fetch(api_sanPhamLienQuan)
    .then(function (resp){
      return resp.json()
    })
      .then(function (sachs) {
        let dachSach = sachs.map(function (sach) {
          return `
          <div class="sach">
              <a href="#"><img src="../img/logo.png"></a>
              <div class="manMo">
                  <p>
                      <a href="/sach?ma=${sach.ma}">Mua ngay</a>
                  </p>
                  <p>
                      <a href="/sach?ma=${sach.ma}">Thêm vào giỏ</a>
                  </p>
              </div>
              <div class="thong-tin-sach">
                  <div class="ten-sach--tran">${sach.ten}</div>
                  <div class="gia-ban">${sach.giaTien}₫</div>
              </div>
          </div>
          `
        })

        sanPhamLienQuan.innerHTML = dachSach.join('');
      })
      .catch(function (loi) {
        console.log(loi)
      })

const thayDoiSoLuong = $$(".thong-tin-so-luong input")
const tangSoLuong = thayDoiSoLuong[2]
const giamSoLuong = thayDoiSoLuong[0]
const soLuong = thayDoiSoLuong[1]

const themGioHang = $(".them-gio-hang-form")
const divGioHang = document.createElement("div")
themGioHang.classList.add("form-hanh-dong")
divGioHang.innerHTML =
    `
        <a class="btn-mua">Thanh toán</a>
    `
themGioHang.appendChild(divGioHang)
const a = $(".form-hanh-dong div a")
a.href = `/thanh-toan?sach=${maSach}?soLuong=1`

tangSoLuong.onclick = function () {
    soLuong.value = parseInt(soLuong.value) + 1
    a.href = `/thanh-toan?sach=${maSach}?soLuong=${soLuong.value}`
}

giamSoLuong.onclick = function () {
    let sl = parseInt(soLuong.value)
    if(sl > 1) {
        soLuong.value = parseInt(soLuong.value) - 1
        a.href = `/thanh-toan?sach=${maSach}?soLuong=${soLuong.value}`
    }
}

