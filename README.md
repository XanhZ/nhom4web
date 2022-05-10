<h1>Cài đặt cơ sở dữ liệu</h1>
<p>Vào thư mục sql copy nội dung file create_tables.sql và thực thi trên Xampp</p>

<h1>API: <a></a></h1>

<h1>Filter</h1>
<ul>
    <li>Tất cả các Filter phải extends từ AbstractFilter và ghi đè kiemTraDelete(), kiemTraGet(), kiemTraPost(), kiemTraGet()</li>
    <li>Các Filter có thể xử lý lỗi dữ liệu nhờ lớp tương ứng từ utils/request</li>
</ul>

<h1>Request Validation Data</h1>
<h4>Ví dụ: Gửi dữ liệu để tạo sách</h4>
<p>Định nghĩa các luật của dữ liệu (Map)</p>
<ul>
    <li>tenSach: khong-bo-trong</li>
    <li>giaTien: so-nguyen-duong</li>
    <li>soLuongTrongKho: so-nguyen-duong</li>
</ul>
<p>Định nghĩa các message trả về tương ứng khi có lỗi (Map)</p>
<ul>
    <li>tenSach.khong-bo-trong: Không được bỏ trống</li>
    <li>giaTien.so-nguyen-duong: Là số nguyên dương</li>
    <li>soLuongTrongKho.so-nguyen-duong: Là số nguyên dương</li>
</ul>