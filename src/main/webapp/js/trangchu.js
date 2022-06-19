const $$ = document.querySelectorAll.bind(document)
const trungBay =  $$(".sanPham .loaiSanPham .trungBay")

const sanPhamNoiBat = trungBay[0]

fetch('/api/sach')
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

        sanPhamNoiBat.innerHTML = dachSach.join('');
    })
    .catch(function (loi) {
        console.log(loi)
    })


const sachHuongNghiep = trungBay[1]

fetch('/api/sach?danhMuc=1')
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

        sachHuongNghiep.innerHTML = dachSach.join('');
    })
    .catch(function (loi) {
        console.log(loi)
    })


const hanhTrangNganhIT = trungBay[2]

fetch('/api/sach?danhMuc=2')
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
        hanhTrangNganhIT.innerHTML = dachSach.join('');
    })
    .catch(function (loi) {
        console.log(loi)
    })
const soLuong = document.querySelector("#soLuong")