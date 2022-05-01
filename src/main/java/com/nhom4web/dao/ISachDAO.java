package com.nhom4web.dao;

import com.nhom4web.model.DanhMuc;
import com.nhom4web.model.HinhAnhSach;
import com.nhom4web.model.Sach;

import java.util.List;

public interface ISachDAO {
    List<HinhAnhSach> layHinhAnh(Sach sach);

    List<DanhMuc> layDanhMucSach(Sach sach);
}
