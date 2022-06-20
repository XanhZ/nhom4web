const hinhAnhHienThi = document.querySelector(".hinh-anh-hien-thi img")
const hinhAnhSach = document.querySelector(".hinh-anh__album")

const sachThongTin = document.querySelector(".sach__thong-tin")
const chiMucMaSach = window.location.href.indexOf('=') + 1
const maSach = window.location.href.slice(chiMucMaSach)
const chiMucSoLuong = window.location.href.lastIndexOf('=') + 1
const soLuong = window.location.href.slice(chiMucSoLuong)

fetch('api/sach/' + maSach)
    .then(function (resp){
        return resp.json()
    })
    .then(function (sach) {
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
    })
    .catch(function (loi) {
        console.log(loi)
    })

fetch('api/sach/' + maSach)
    .then(function (resp){
        return resp.json()
    })
    .then(function (sach) {
        sachThongTin.innerHTML =
        `
           <h2 class="ten-sach">${sach.ten}</h2>
            <div class="gia-ban">
              <span class="thong-tin__tieu-de">Đơn giá:</span>
              <span class="gia-ban__thuc-te">${sach.giaTien}₫</span>
            </div>
            <div class="tac-gia">
              <span class="thong-tin__tieu-de">Tác giả:</span>
              <span class="tac-gia__ten">Nhóm tác giả Spiderum</span>
            </div>
            <div class="tac-gia">
              <span class="thong-tin__tieu-de">Số lượng: </span>
            </div>
        `
    })
    .catch(function (loi) {
        console.log(loi)
    })


const thayDoiSoLuong = document.querySelectorAll(".thong-tin-so-luong input")
const tangSoLuong = thayDoiSoLuong[2]
const giamSoLuong = thayDoiSoLuong[0]
const soLuongSach = thayDoiSoLuong[1]

tangSoLuong.onclick = function () {
    soLuongSach.value = parseInt(soLuongSach.value) + 1
}

giamSoLuong.onclick = function () {
    let sl = parseInt(soLuongSach.value)
    if(sl > 1) {
        soLuongSach.value = parseInt(soLuongSach.value) - 1
    }
}

const nutGui = document.querySelector(".nutGui")
nutGui.onclick = async function () {
    const diaChi = document.querySelector(".diaChi").value
    const donHang = new FormData()
    donHang.append("diaChi", diaChi)
    donHang.append("maSach", maSach)
    donHang.append("soLuong", soLuongSach.value)
    const resp = await fetch("/api/nguoi-dung/don-hang", {
        method: 'POST',
        body: donHang
    });
    if(resp.ok) {
        alert("Đơn hàng đã được tạo.")
        location.assign("/trang-chu")
    }
    else {
        console.log("Có lỗi!")
    }
}