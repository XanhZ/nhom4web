const $ = document.querySelector.bind(document)
const span = document.querySelector(".gioHang #sl")

fetch("/api/gio-hang")
    .then(function (resp){
        return resp.json()
    })
    .then(function (sl) {
        span.innerText = sl.soLuong
    })
    .catch(function (loi) {
        console.log(loi)
    })

const danhMuc = $(".danhMuc")

fetch('/api/danh-muc')
    .then(function (resp){
        return resp.json()
    })
    .then(function (danhMucs) {
        let dm = danhMucs.map(function (danhMuc) {
            const url = danhMuc.ten.toLowerCase().normalize("NFD").replace(/\p{Diacritic}/gu, "").replaceAll(" ", "-")
            return `
             <li><a href="/${url}">${danhMuc.ten}</a></li>
            `
        })
        danhMuc.innerHTML = dm.join('');
    })
    .catch(function (loi) {
        console.log(loi)
    })

const search = $(".timKiem input")

search.addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        const url = "/tu-sach?ten=" + search.value.replaceAll(" ", "-")
        location.assign(url)
    }
})
