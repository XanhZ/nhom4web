const hinhAnhHienThi = document.querySelector(".hinh-anh-hien-thi img")
const noiDungDuongDan = document.querySelector(".noi-dung__duong-dan ul")
const thongTinSach = document.querySelector(".sach__thong-tin")
const hinhAnhSach = document.querySelector(".hinh-anh__album")
const cacBinhLuan = document.querySelector(".cacBinhLuan")
const nutNhapBinhLuan = document.querySelector(".nhapNoiDung")
const mangBinhLuan = []

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

    let divAnh = sach.hinhAnhSachs.map(function (anh) {
        return `
        <div class="hinh-anh--don">
            <img src="${anh.duongDan}">
        </div>
        `
    })
    hinhAnhSach.innerHTML = divAnh.join("")
    hinhAnhHienThi.src = sach.hinhAnhSachs[0].duongDan

    let album = document.querySelectorAll(".hinh-anh--don")
    album.forEach(img => {
        img.addEventListener("click", () => {
            const url = img.firstElementChild.getAttribute("src")
            album.forEach(ele => ele.classList.remove("dang-hien-thi"))
            img.classList.add("dang-hien-thi")
            hinhAnhHienThi.setAttribute("src", url)
        })
    })
      const giaTien = dinhDangTienTe(sach.giaTien)
      const div = document.createElement("div")
      div.classList.add("gia-ban")
      const span = document.createElement("span")
      const giaBan = document.createTextNode(`Giá bán: ${giaTien}`)
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

const sanPhamLienQuan = document.querySelector(".noi-dung__sp-lien-quan .loaiSanPham .trungBay")
const api_sanPhamLienQuan = `api/sach/${maSach}/lien-quan`;
fetch(api_sanPhamLienQuan)
    .then(function (resp){
      return resp.json()
    })
      .then(function (sachs) {
        let dachSach = sachs.map(function (sach) {
          const giaTien = dinhDangTienTe(sach.giaTien)
          return `
          <div class="sach">
            <a href="#"><img src="${sach.hinhAnhSachs[0].duongDan}"></a>
            <div class="manMo">
                <p>
                    <a href="/thanh-toan?sach=${maSach}">Mua ngay</a>
                </p>
                <p>
                    <span style="color: white; cursor:pointer;" onclick="them(${sach.ma})">Thêm vào giỏ</span>
                </p>
            </div>
            <div class="thong-tin-sach">
                <div class="ten-sach--tran">${sach.ten}</div>
                <div class="gia-ban">${giaTien}</div>
            </div>
          </div>
          `
        })

        sanPhamLienQuan.innerHTML = dachSach.join('');
      })
      .catch(function (loi) {
        console.log(loi)
      })

fetch("api/sach/" + maSach + "/binh-luan")
    .then(function (resp){
        return resp.json()
    })
    .then(function (binhLuans) {
        mangBinhLuan.push(...binhLuans)
        taiBinhLuan()
    })
    .catch(function (loi) {
        console.log(loi)
    })

nutNhapBinhLuan.addEventListener("keypress", async function(event) {
    if (event.key === "Enter") {
        event.preventDefault()
        const noiDung = nutNhapBinhLuan.value
        const bl = new FormData()
        bl.append("maSach", maSach)
        bl.append("noiDung", noiDung)
        const resp = await fetch(`api/sach/${maSach}/binh-luan`,{
            method: 'POST',
            body: bl
        });
        const duLieuBl = await resp.json();
        if(resp.ok) {
            nutNhapBinhLuan.value = ""
            mangBinhLuan.push(duLieuBl)
            taiBinhLuan()
        }
        else {
            alert("Không thể bình luận.")
        }
    }
})

async function them(maSach) {
    const gioHang = new FormData();
    gioHang.append('maSach',maSach)
    gioHang.append('soLuong', 1)
    const resp = await fetch("api/gio-hang", {
        method: "POST",
        body: gioHang
    })
    if (resp.ok) {
        alert("Đã thêm vào giỏ hàng.")
        laySoLuong()
        return resp.json()
    }
    else {
        throw resp
    }
}

function dinhDangTienTe(soTien) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(soTien)
}

function dinDangNgay(thoiGianStr) {
    const thoiGian = new Date(thoiGianStr)
    return `${thoiGian.getHours()} : ${thoiGian.getMinutes()} - ${thoiGian.getDay()}/${thoiGian.getMonth()}/${thoiGian.getFullYear()}`
}

function taiBinhLuan () {
    let dachSach = mangBinhLuan.map(function (binhLuan) {
        const thoiGian = dinDangNgay(binhLuan.thoiGianTao)
        return `
            <div class="binhLuan">
                <div class="thongTinPhu">
                    <span class="tenNguoiBinhLuan"> ${binhLuan.nguoiDung.ten}: </span>
                    <span class="thoiGian">${thoiGian}</span>
                </div>
                <div class="noiDungChinh">
                    <span class="noiDung">${binhLuan.noiDung}</span>
                </div>
            </div>
            `
    })

    cacBinhLuan.innerHTML = dachSach.join('');
}