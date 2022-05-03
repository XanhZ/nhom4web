package com.nhom4web.utils.request;

import com.nhom4web.utils.Request;

public class SachRequest extends Request {
    public SachRequest() {
        this.luat.put("tenSach", KHONG_BO_TRONG);
        this.luat.put("giaTien", SO_NGUYEN_DUONG);
        this.luat.put("soLuongTrongKho", SO_NGUYEN_DUONG);

        this.e.put("tenSach." + KHONG_BO_TRONG, "Tên sách không được bỏ trống");
        this.e.put("giaTien." + SO_NGUYEN_DUONG, "Giá tiền phải là số nguyên dương");
        this.e.put("soLuongTrongKho." + SO_NGUYEN_DUONG, "Số lương phải là số nguyên dương");
    }
}
