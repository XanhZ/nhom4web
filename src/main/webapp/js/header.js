const span = document.querySelector(".sl")
async function laySoLuong() {
    const resp = await fetch("https://localhost:8443/api/gio-hang", {
        method: "GET",
    })
    const duLieu = await resp.json();
    if (resp.ok) {
        span.innerHTML = duLieu.length
    }
    else {
        throw resp
    }
}

laySoLuong()
const danhMuc = document.querySelector(".danhMuc")


fetch('https://localhost:8443/api/danh-muc')
    .then(function (resp){
        return resp.json()
    })
    .then(function (danhMucs) {
        let dm = danhMucs.map(function (danhMuc) {
            const url = "/sach-theo-danh-muc?ma=" + danhMuc.ma
            return `
             <li><a href="${url}">${danhMuc.ten}</a></li>
            `
        })

        danhMuc.innerHTML = dm.join('');
    })
    .catch(function (loi) {
        console.log(loi)
    })

const search = document.querySelector(".timKiem input")

search.addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        const url = "/tu-sach?ten=" + search.value.replaceAll(" ", "-")
        location.assign(url)
    }
})

const nutDangXuat = document.querySelector("#dangXuat")
nutDangXuat.onclick = function () {
    fetch(`https://localhost:8443/api/dang-xuat`, {
        method: 'POST'
    })
        .then(function (resp) {
            if(resp.ok) {
                location.assign("/trang-chu")
            }
    })
}
