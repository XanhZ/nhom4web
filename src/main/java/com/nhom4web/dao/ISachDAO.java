package com.nhom4web.dao;

import com.nhom4web.model.Sach;

import javax.servlet.http.Part;
import java.util.List;

public interface ISachDAO {
    boolean them(Sach sach, List<Part> hinhAnhs, List<Integer> maDanhMucs);
}
