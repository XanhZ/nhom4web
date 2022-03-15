package com.nhom4web.dao;

import java.util.LinkedHashMap;
import java.util.List;

public interface IDAO<T> {
    boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma);

    List<T> layTatCa();

    T layTheoMa(int ma);

    int them(LinkedHashMap<String, Object> duLieu);

    boolean xoaTheoMa(int ma);

    boolean xoaTheoMa(int[] mas);
}
