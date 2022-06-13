<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Shop</title>
    <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/trangchu.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" sizes="32x32">
</head>

<body>
<%@ include file="header.jsp"%>
<div id="thungQuangCao">
    <div class="quangCao">
    </div>
</div>
<div class="sanPham">
    <div class="loaiSanPham">
        <div class="tieuDe">
            <p>Tất cả sản phẩm</p>
        </div>
        <div class="locSach">
            <div class="phanLoc">
                <div class="luaChon">
                    <select >
                        <option>Phân loại</option>
                        <option>Bán chạy nhất</option>
                        <option>option 2 </option>
                        <option>option 3 </option>
                    </select>
                </div>
            </div>
            <div class="phanLoc">
                <div class="luaChon">
                    <select >
                        <option>Theo giá</option>
                        <option>Dưới 100.000đ</option>
                        <option>100.000đ - 150.000đ</option>
                        <option>150.000đ - 200.000đ</option>
                    </select>
                </div>
            </div>
            <div class="phanLoc">
                <div class="luaChon">
                    <select >
                        <option>Theo danh mục</option>
                        <option>Người trong muôn nghề</option>
                        <option>Hành trang ngành IT</option>
                        <option>Sách hướng nghiệp</option>
                        <option>Chủ nghĩa khắc kỷ</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="trungBay">
        </div>
    </div>
    <div class="loaiSanPham">
        <div class="tieuDe">
            <p>Sách hướng nghiệp</p>
        </div>
        <div class="trungBay">
        </div>
    </div>
    <div class="loaiSanPham">
        <div class="tieuDe">
            <p>Hành trang ngành it</p>
        </div>
        <div class="trungBay">
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
<script src="../js/header.js"></script>
<script type="module" src="../js/trangchu.js"></script>
<script type="text/javascript">
    const soLuong = document.querySelector(".gioHang #sl")
    async function them(maSach) {
        const giohang = new FormData();
        giohang.append('maSach',maSach)
        giohang.append('soLuong', 1)
        const resp = await fetch("api/gio-hang", {
            method: "POST",
            body: gioHang
        })
        if (resp.ok) {
            alert("Đã thêm vào giỏ hàng.")
            return resp.json()
        }
        else {
            throw resp
        }
    }
</script>
</html>