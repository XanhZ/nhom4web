package com.nhom4web.dao;

import com.nhom4web.model.HinhAnhSach;

import javax.servlet.http.Part;
import java.util.List;

public interface IHinhAnhSachDAO {
    boolean them(int maSach, List<Part> hinhAnhs);

    List<HinhAnhSach> layTatCa(int maSach);
}
