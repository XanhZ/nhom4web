const url = decodeURIComponent(window.location.href)
const chiMuc = url.indexOf('=') + 1
const tenSach = url.slice(chiMuc).replaceAll("-", " ")
const sach = document.querySelector(".trungBay")


const duLieuTheoTen ="/api/sach?tenSach=" + tenSach
fetch(duLieuTheoTen)
    .then(function (resp){
        return resp.json()
    })
    .then(function (sachs) {
        let dachSach = sachs.map(function (sach) {
            return `
             <div class="sach">
                <a href="#"><img src="${sach.hinhAnhSachs[0].duongDan}"></a>
                <div class="manMo">
                    <p>
                        <a href="/thanh-toan?sach=${sach.ma}">Mua ngay</a>
                    </p>
                    <p>
                        <span style="color: white; cursor:pointer;" onclick="them(${sach.ma})">Thêm vào giỏ</span>
                    </p>
                </div>
                <div class="thong-tin-sach">
                    <div class="ten-sach--tran">${sach.ten}</div>
                    <div class="gia-ban">${sach.giaTien}₫</div>
                </div>
            </div>
          `
        })

        sach.innerHTML = dachSach.join('');
    })
    .catch(function (loi) {
        console.log(loi)
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


