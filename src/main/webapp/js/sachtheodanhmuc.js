const url = decodeURIComponent(window.location.href)
const chiMuc = url.indexOf('=') + 1
const maDanhMuc = url.slice(chiMuc).replaceAll("-", " ")
const trungBaySach = document.querySelector(".trungBay")

const api = "/api/sach?danhMuc=" + maDanhMuc

fetch(api)
    .then(function (resp){
        return resp.json()
    })
    .then(function (sachs) {
            let dachSach = sachs.map(function (sach) {
                return `
            <div class="sach">
                <a href="/sach?ma=${sach.ma}"><img src="${sach.hinhAnhSachs[0].duongDan}"></a>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">${sach.ten}</div>
                    <div class="gia-ban">${sach.giaTien}₫</div>
                </div>
            </div>
            `
            })

        trungBaySach.innerHTML = dachSach.join('');

    })
    .catch(function (loi) {
        console.log(loi)
    })



