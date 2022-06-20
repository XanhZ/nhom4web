# Yêu cầu
1. Tomcat 9.0.63+
2. JDK 15+

# Cài đặt cơ sở dữ liệu
1. Vào src/main/java/com/nhom4web/dao/impl/AbstractDAO.java thay đổi USERNAME, PASSWORD
2. Vào sql/create_tables.sql copy và thực thi trên MySQL với tên DB: nhom4_web

# API
1. Danh mục
- /api/danh-muc: POST, GET
- /api/danh-muc/{id}: GET, PUT, DELETE
----------------------------------------------
2. Đăng ký, đăng nhập
- /api/dang-ky: POST
- /api/dang-nhap: POST
- /api/dang-xuat: POST
----------------------------------------------
3. Sách
- /api/sach: POST, GET
- /api/sach: GET (with condition):
  + tenSach: tên sách cần
  + danhMuc: mã danh mục (có thể có nhiều danhMuc)
  + giaTu: giá tiền nhỏ nhất
  + giaDen: giá tiền lớn nhất
  + sapXepTheo: 1 trong các giá trị {giaGiamDan, giaTangDan, tenSach, ganDayNhat, banChayNhat}
  + gioiHan: số lượng sách tối đa lấy trong request
  + Eg: /api/sach?tenSach=a&danhMuc=2&danhMuc=3&giaTu=20000&sapXepTheo=tenSach
- /api/sach/{id}: GET, PUT, DELETE
----------------------------------------------
4. Hình ảnh sách
- /api/sach/{sach_id}/hinh-anh-sach: POST, GET
- /api/sach/{sach_id}/hinh-anh-sach/{id}: GET, PUT, DELETE
----------------------------------------------
4. Bình luận sách
- /api/sach/{sach_id}/binh-luan: POST, GET
- /api/sach/{sach_id}/binh-luan/{binh_luan_id}: PUT, DELETE
----------------------------------------------
5. Phân loại sách
- /api/sach/{sach_id}/phan-loai-sach: POST, GET
- /api/sach/{sach_id}/phan-loai-sach/{id}: GET, PUT, DELETE
----------------------------------------------
6. Đơn hàng
- Admin:
  - /api/don-hang?trangThai={trangThai}: GET
  - /api/don-hang/{id}: GET, PUT, DELETE
- User:
  - /api/nguoi-dung/don-hang: POST
  - /api/nguoi-dung/don-hang?trangThai={trangThai}: GET
  - /api/nguoi-dung/don-hang/{id}: GET, PUT