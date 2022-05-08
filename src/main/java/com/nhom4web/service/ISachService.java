package com.nhom4web.service;

import com.nhom4web.model.Sach;

import java.util.LinkedHashMap;
import java.util.List;

public interface ISachService {
    List<Sach> layTatCa();

    boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma);

    int them(LinkedHashMap<String, Object> duLieu);

    Sach timTheoMa(int ma);

    boolean xoaTheoMa(int ma);

    boolean phanLoaiSach(int maSach, List<Integer> maDanhMucs);

    boolean themHinhAnhSach(int maSach, List<String> duongDans);
}
