package com.nhom4web.service;

import com.nhom4web.model.DanhMuc;

import java.util.LinkedHashMap;
import java.util.List;

public interface IDanhMucService {
    List<DanhMuc> layTatCa();

    boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma);

    int them(LinkedHashMap<String, Object> duLieu);

    DanhMuc timTheoMa(int ma);

    boolean xoaTheoMa(int ma);
}
