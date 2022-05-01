package com.nhom4web.dao;

import com.nhom4web.model.DanhMuc;
import com.nhom4web.model.Sach;

import java.util.List;

public interface IDanhMucDAO {
    List<Sach> sachTrongDanhMuc(DanhMuc danhMuc);
}
