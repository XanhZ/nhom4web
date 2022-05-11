package com.nhom4web.service;

import com.nhom4web.model.NguoiDung;

import java.util.LinkedHashMap;
import java.util.List;

public interface INguoiDungService {
    List<NguoiDung> layTatCa();

    boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma);

    int them(LinkedHashMap<String, Object> duLieu);

    NguoiDung timTheoMa(int ma);

    boolean xoaTheoMa(int ma);
}
