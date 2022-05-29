package com.nhom4web.filter;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "Danh-Muc-Filter")
@MultipartConfig
public class DanhMucFilter extends AbstractFilter {
    public DanhMucFilter() {
        this.luat.put("tenDanhMuc", KHONG_BO_TRONG);

        this.e.put("tenDanhMuc." + KHONG_BO_TRONG, "Không được để trống tên danh mục");
    }
}
