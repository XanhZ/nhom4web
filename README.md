# Yêu cầu
1. Tomcat 9.0.63+
2. JDK 15+
#Cài đặt cơ sở dữ liệu</h1>
1. Vào src/main/java/com/nhom4web/dao/impl/AbstractDAO.java thay đổi USERNAME, PASSWORD
2. Vào sql/create_tables.sql copy và thực thi trên MySQL với tên DB: nhom4_web
#API
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
- /api/sach/{id}: GET, PUT, DELETE
----------------------------------------------
4. Hình ảnh sách
- /api/sach/{sach_id}/hinh-anh-sach: POST, GET
- /api/sach/{sach_id}/hinh-anh-sach/{id}: GET, PUT, DELETE
----------------------------------------------
5. Phân loại sách
- /api/sach/{sach_id}/phan-loai-sach: POST, GET
- /api/sach/{sach_id}/phan-loai-sach/{id}: GET, PUT, DELETE
----------------------------------------------
6. Đơn hàng
- /api/don-hang: POST
- /api/don-hang?trangThai={trangThai}: GET
- /api/don-hang/{id}: GET, PUT, DELETE
