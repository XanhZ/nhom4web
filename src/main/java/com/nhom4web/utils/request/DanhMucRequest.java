package com.nhom4web.utils.request;

import com.nhom4web.utils.Request;

public class DanhMucRequest extends Request {
    public DanhMucRequest() {
        this.luat.put("tenDanhMuc", KHONG_BO_TRONG);

        this.e.put("tenDanhMuc." + KHONG_BO_TRONG, "Không được để trống tên danh mục");
    }
}
