const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)

const album = $$(".hinh-anh--don")
const hinhAnhHienThi = $(".hinh-anh-hien-thi img")

album.forEach(img => {
    img.addEventListener("click", () => {
        const url = img.firstElementChild.getAttribute("src")
        album.forEach(ele => ele.classList.remove("dang-hien-thi"))
        img.classList.add("dang-hien-thi")
        hinhAnhHienThi.setAttribute("src", url)
    })
})

const sachThongTin = $(".sach__thong-tin")

const chiMucMaSach = window.location.href.indexOf('=') + 1
const maSach = window.location.href.slice(chiMucMaSach)
const chiMucSoLuong = window.location.href.lastIndexOf('=') + 1
const soLuong = window.location.href.slice(chiMucSoLuong)



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
              <span class="tac-gia__ten">${soLuong}</span>
            </div>
        `
    })
    .catch(function (loi) {
        console.log(loi)
    })

