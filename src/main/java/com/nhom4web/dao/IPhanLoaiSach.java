package com.nhom4web.dao;

import com.nhom4web.model.Sach;

import java.util.List;

public interface IPhanLoaiSach {
    boolean them(int maSach, List<Integer> maDanhMucs);

    boolean timDanhMucSach(Sach sach);
}
