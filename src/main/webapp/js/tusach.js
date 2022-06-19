const url = encodeURI(window.location.href).replaceAll("%20",'-')
const chiMuc = url.indexOf('=') + 1
const tenSach = url.slice(chiMuc).replaceAll("-", " ")
const sach = document.querySelector(".sach")


fetch("/api/sach?tenSach=" + tenSach)
    .then(function (resp){
        return resp.json()
    })
    .then(function (sachs) {
        console.log(sachs)
        let ds = sachs.map(function (sach) {
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
        sach.innerHTML = ds.join('');
    })
    .catch(function (loi) {
        console.log(loi)
    })


