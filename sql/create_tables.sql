CREATE TABLE `nguoiDung`
(
    `ma`              INT PRIMARY KEY AUTO_INCREMENT,
    `sdt`             VARCHAR(10)                 DEFAULT NULL,
    `email`           VARCHAR(50) UNIQUE NOT NULL,
    `ten`             VARCHAR(50)        NOT NULL,
    `loaiNguoiDung`   ENUM ('0', '1') NOT NULL DEFAULT 1,
    `thoiGianTao`     TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `thoiGianCapNhat` TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP()
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `thongTinDangNhap`
(
    `ma`              INT PRIMARY KEY AUTO_INCREMENT,
    `maNguoiDung`     INTEGER UNIQUE     NOT NULL,
    `tenDangNhap`     VARCHAR(50) UNIQUE NOT NULL,
    `matKhau`         VARCHAR(128)       NOT NULL,
    `token`           varchar(300)                DEFAULT NULL,
    `thoiGianTao`     TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `thoiGianCapNhat` TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (`maNguoiDung`) REFERENCES `nguoiDung` (`ma`) ON DELETE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `sach`
(
    `ma`              INT PRIMARY KEY AUTO_INCREMENT,
    `tenSach`         VARCHAR(255) NOT NULL,
    `giaTien`         INT          NOT NULL CHECK (`giaTien` >= 0),
    `soLuongTrongKho` INT          NOT NULL CHECK (`soLuongTrongKho` >= 0),
    `thoiGianTao`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `thoiGianCapNhat` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP()
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `danhMuc`
(
    `ma`              INT PRIMARY KEY AUTO_INCREMENT,
    `tenDanhMuc`      VARCHAR(100) UNIQUE NOT NULL,
    `thoiGianTao`     TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `thoiGianCapNhat` TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP()
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `donHang`
(
    `ma`              INT PRIMARY KEY AUTO_INCREMENT,
    `maNguoiDung`     INT,
    `trangThai`       ENUM('dangCho', 'xacNhan', 'huy') DEFAULT 'dangCho',
    `thoiGianTao`     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `thoiGianCapNhat` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (`maNguoiDung`) REFERENCES `nguoiDung` (`ma`) ON DELETE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `dongDonHang`
(
    `maSach`    INT NOT NULL,
    `maDonHang` INT NOT NULL,
    `soLuong`   INT NOT NULL CHECK (`soLuong` > 0),
    `donGia`    INT NOT NULL CHECK (`donGia` >= 0),
    FOREIGN KEY (`maSach`) REFERENCES `sach` (`ma`) ON DELETE CASCADE,
    FOREIGN KEY (`maDonHang`) REFERENCES `donHang` (`ma`) ON DELETE CASCADE,
    CONSTRAINT PK_DongDonHang PRIMARY KEY (`maSach`, `maDonHang`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `hinhAnhSach`
(
    `maSach`   INT                 NOT NULL,
    `duongDan` VARCHAR(255) UNIQUE NOT NULL,
    FOREIGN KEY (`maSach`) REFERENCES `sach` (`ma`) ON DELETE CASCADE,
    CONSTRAINT PK_HinhAnhSach PRIMARY KEY (`maSach`, `duongDan`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `phanLoaiSach`
(
    `maSach`    INT NOT NULL,
    `maDanhMuc` INT NOT NULL,
    FOREIGN KEY (`maSach`) REFERENCES `sach` (`ma`) ON DELETE CASCADE,
    FOREIGN KEY (`maDanhMuc`) REFERENCES `danhMuc` (`ma`) ON DELETE CASCADE,
    CONSTRAINT PK_PhanLoaiSach PRIMARY KEY (`maSach`, `maDanhMuc`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `binhLuan`
(
    `maSach`          INT        NOT NULL,
    `maNguoiDung`     INT        NOT NULL,
    `noiDung`         MEDIUMTEXT NOT NULL,
    `thoiGianTao`     TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `thoiGianCapNhat` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (`maSach`) REFERENCES `sach` (`ma`) ON DELETE CASCADE,
    FOREIGN KEY (`maNguoiDung`) REFERENCES `nguoiDung` (`ma`) ON DELETE CASCADE,
    CONSTRAINT PK_BinhLuan PRIMARY KEY (`maSach`, `maNguoiDung`, `thoiGianTao`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;