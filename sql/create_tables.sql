CREATE TABLE nguoidung
(
    ma              INT PRIMARY KEY AUTO_INCREMENT,
    ten             VARCHAR(100) NOT NULL,
    sdt             VARCHAR(10)  NOT NULL,
    email           VARCHAR(255) NOT NULL,
    loaiNguoiDung   INT          NOT NULL DEFAULT 0 CHECK (loaiNguoiDung IN (0, 1)),
    thoiGianTao     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    thoiGianCapNhat TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE thongtindangnhap
(
    ma              INT PRIMARY KEY AUTO_INCREMENT,
    maNguoiDung     INT          NOT NULL,
    tenDangNhap     VARCHAR(255) NOT NULL,
    matKhau         VARCHAR(255) NOT NULL,
    token           VARCHAR(255),
    thoiGianTao     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    thoiGianCapNhat TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    UNIQUE (maNguoiDung),
    FOREIGN KEY (maNguoiDung) REFERENCES nguoidung (ma) ON DELETE CASCADE
);

CREATE TABLE danhmuc
(
    ma              INT PRIMARY KEY AUTO_INCREMENT,
    tenDanhMuc      VARCHAR(255) NOT NULL,
    thoiGianTao     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    thoiGianCapNhat TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE sach
(
    ma              INT PRIMARY KEY AUTO_INCREMENT,
    tenSach         VARCHAR(255) NOT NULL,
    giaTien         INT          NOT NULL CHECK (giaTien >= 0),
    soLuongTrongKho INT          NOT NULL CHECK (soLuongTrongKho >= 0),
    thoiGianTao     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    thoiGianCapNhat TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE hinhanhsach
(
    maSach   INT          NOT NULL,
    duongDan VARCHAR(255) NOT NULL,
    FOREIGN KEY (maSach) REFERENCES sach (ma) ON DELETE CASCADE,
    UNIQUE (duongDan),
    CONSTRAINT PK_HinhAnhSach PRIMARY KEY (maSach, duongDan)
);

CREATE TABLE phanLoaiSach
(
    maSach    INT NOT NULL,
    maDanhMuc INT NOT NULL,
    FOREIGN KEY (maSach) REFERENCES sach (ma) ON DELETE CASCADE,
    FOREIGN KEY (maDanhMuc) REFERENCES danhmuc (ma) ON DELETE CASCADE,
    CONSTRAINT PK_PhanLoaiSach PRIMARY KEY (maSach, maDanhMuc)
);

CREATE TABLE binhluan
(
    maSach          INT        NOT NULL,
    maNguoiDung     INT        NOT NULL,
    noiDung         MEDIUMTEXT NOT NULL,
    thoiGianTao     TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    thoiGianCapNhat TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (maSach) REFERENCES sach (ma) ON DELETE CASCADE,
    FOREIGN KEY (maNguoiDung) REFERENCES nguoidung (ma) ON DELETE CASCADE,
    CONSTRAINT PK_BinhLuan PRIMARY KEY (maSach, maNguoiDung, thoiGianTao)
);

CREATE TABLE donhang
(
    ma              INT PRIMARY KEY AUTO_INCREMENT,
    maNguoiDung     INT,
    trangThai       VARCHAR(20)        DEFAULT 'dangCho' CHECK (trangThai IN ('dangCho', 'xacNhan', 'huy')),
    thoiGianTao     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    thoiGianCapNhat TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (maNguoiDung) REFERENCES nguoidung (ma) ON DELETE SET NULL
);

CREATE TABLE dongdonhang
(
    maSach    INT NOT NULL,
    maDonHang INT NOT NULL,
    soLuong   INT NOT NULL CHECK (soLuong > 0),
    donGia    INT NOT NULL CHECK (donGia >= 0),
    FOREIGN KEY (maSach) REFERENCES sach (ma) ON DELETE CASCADE,
    FOREIGN KEY (maDonHang) REFERENCES donhang (ma) ON DELETE CASCADE,
    CONSTRAINT PK_DongDonHang PRIMARY KEY (maSach, maDonHang)
);